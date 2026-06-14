package com.tech.quickbite.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.AddToCartRequest;
import com.tech.quickbite.dto.response.CartItemResponse;
import com.tech.quickbite.dto.response.CartResponse;
import com.tech.quickbite.entity.Cart;
import com.tech.quickbite.entity.CartItem;
import com.tech.quickbite.entity.FoodItem;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.repository.CartItemRepository;
import com.tech.quickbite.repository.CartRepository;
import com.tech.quickbite.repository.FoodItemRepository;
import com.tech.quickbite.service.CartService;
import com.tech.quickbite.service.UserService;

@Service
public class CartServiceImpl
        implements CartService {

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private FoodItemRepository foodItemRepository;

    private UserService userService;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            FoodItemRepository foodItemRepository,
            UserService userService) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.foodItemRepository = foodItemRepository;
        this.userService = userService;
    }

    @Override
    public CartResponse addToCart(
            AddToCartRequest request) {

        User user =
                userService.getCurrentUser();

        Cart cart =
                cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalAmount(0.0);
                    return cartRepository.save(newCart);
                });

        FoodItem foodItem =
                foodItemRepository.findById(
                        request.getFoodItemId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Food Item not found"));

        CartItem cartItem =
                new CartItem();

        cartItem.setCart(cart);

        cartItem.setFoodItem(foodItem);

        cartItem.setQuantity(
                request.getQuantity());

        cartItem.setSubtotal(
                foodItem.getPrice()
                * request.getQuantity());

        cartItemRepository.save(cartItem);

        updateCartTotal(cart);

        return viewCart();
    }

    @Override
    public CartResponse viewCart() {

        User user =
                userService.getCurrentUser();

        Cart cart =
                cartRepository.findByUser(user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart not found"));

        List<CartItemResponse> items =
                cartItemRepository.findByCart(cart)
                        .stream()
                        .map(item ->
                                new CartItemResponse(
                                        item.getId(),
                                        item.getFoodItem().getName(),
                                        item.getQuantity(),
                                        item.getFoodItem().getPrice(),
                                        item.getSubtotal()))
                        .collect(Collectors.toList());

        return new CartResponse(
                cart.getId(),
                cart.getTotalAmount(),
                items);
    }

    @Override
    public void removeItem(
            Long cartItemId) {

        CartItem item =
                cartItemRepository.findById(
                        cartItemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart Item not found"));

        Cart cart = item.getCart();

        cartItemRepository.delete(item);

        updateCartTotal(cart);
    }

    @Override
    public void clearCart() {

        User user =
                userService.getCurrentUser();

        Cart cart =
                cartRepository.findByUser(user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart not found"));

        List<CartItem> items =
                cartItemRepository.findByCart(cart);

        cartItemRepository.deleteAll(items);

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);
    }

    private void updateCartTotal(
            Cart cart) {

        List<CartItem> items =
                cartItemRepository.findByCart(cart);

        double total =
                items.stream()
                .mapToDouble(
                        CartItem::getSubtotal)
                .sum();

        cart.setTotalAmount(total);

        cartRepository.save(cart);
    }
}