package com.tech.quickbite.service;

import java.util.List;

import com.tech.quickbite.dto.request.PlaceOrderRequest;
import com.tech.quickbite.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(
            PlaceOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getOrderById(
            Long orderId);

    OrderResponse updateOrderStatus(
            Long orderId,
            String status);
}