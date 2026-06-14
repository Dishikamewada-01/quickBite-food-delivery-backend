package com.tech.quickbite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.quickbite.dto.request.PlaceOrderRequest;
import com.tech.quickbite.dto.response.OrderResponse;
import com.tech.quickbite.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse>
    placeOrder(
            @RequestBody
            PlaceOrderRequest request) {

        return ResponseEntity.ok(
                orderService.placeOrder(
                        request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>>
    getMyOrders() {

        return ResponseEntity.ok(
                orderService.getMyOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse>
    getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderById(
                        id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse>
    updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                orderService.updateOrderStatus(
                        id,
                        status));
    }
}