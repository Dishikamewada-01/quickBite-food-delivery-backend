package com.tech.quickbite.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.PlaceOrderRequest;
import com.tech.quickbite.dto.response.OrderItemResponse;
import com.tech.quickbite.dto.response.OrderResponse;
import com.tech.quickbite.entity.Cart;
import com.tech.quickbite.entity.CartItem;
import com.tech.quickbite.entity.Order;
import com.tech.quickbite.entity.OrderItem;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.enums.OrderStatus;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.repository.CartItemRepository;
import com.tech.quickbite.repository.CartRepository;
import com.tech.quickbite.repository.OrderItemRepository;
import com.tech.quickbite.repository.OrderRepository;
import com.tech.quickbite.service.OrderService;
import com.tech.quickbite.service.UserService;

@Service
public class OrderServiceImpl
        implements OrderService {

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private UserService userService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserService userService) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }

    @Override
    public OrderResponse placeOrder(
            PlaceOrderRequest request) {

        User customer =
                userService.getCurrentUser();

        Cart cart =
                cartRepository.findByUser(customer)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart not found"));

        List<CartItem> cartItems =
                cartItemRepository.findByCart(cart);

        if (cartItems.isEmpty()) {

            throw new ResourceNotFoundException(
                    "Cart is empty");
        }

        Order order = new Order();

        order.setCustomer(customer);

        order.setOrderTime(
                LocalDateTime.now());

        order.setStatus(
                OrderStatus.PENDING);

        order.setDeliveryAddress(
                request.getDeliveryAddress());

        order.setTotalAmount(
                cart.getTotalAmount());

        Order savedOrder =
                orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(
                    savedOrder);

            orderItem.setFoodItem(
                    cartItem.getFoodItem());

            orderItem.setQuantity(
                    cartItem.getQuantity());

            orderItem.setSubtotal(
                    cartItem.getSubtotal());

            orderItemRepository.save(
                    orderItem);
        }

        cartItemRepository.deleteAll(
                cartItems);

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);

        return mapToResponse(
                savedOrder);
    }

    @Override
    public List<OrderResponse>
    getMyOrders() {

        User customer =
                userService.getCurrentUser();

        return orderRepository
                .findByCustomer(customer)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(
            Long orderId) {

        Order order =
                orderRepository.findById(
                        orderId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Order not found"));

        return mapToResponse(order);
    }

    @Override
    public OrderResponse updateOrderStatus(
            Long orderId,
            String status) {

        Order order =
                orderRepository.findById(
                        orderId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Order not found"));

        order.setStatus(
                OrderStatus.valueOf(status));

        Order updatedOrder =
                orderRepository.save(order);

        return mapToResponse(
                updatedOrder);
    }

    private OrderResponse mapToResponse(
            Order order) {

        List<OrderItemResponse> items =
                orderItemRepository
                        .findByOrder(order)
                        .stream()
                        .map(item ->
                                new OrderItemResponse(
                                        item.getFoodItem()
                                                .getName(),
                                        item.getQuantity(),
                                        item.getSubtotal()))
                        .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getOrderTime(),
                items);
    }
}