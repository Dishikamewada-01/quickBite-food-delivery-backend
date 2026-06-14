package com.tech.quickbite.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Long orderId;

    private Double totalAmount;

    private String status;

    private LocalDateTime orderTime;

    private List<OrderItemResponse> items;

    public OrderResponse() {
    }

    public OrderResponse(
            Long orderId,
            Double totalAmount,
            String status,
            LocalDateTime orderTime,
            List<OrderItemResponse> items) {

        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderTime = orderTime;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderTime(
            LocalDateTime orderTime) {

        this.orderTime = orderTime;
    }

    public void setItems(
            List<OrderItemResponse> items) {

        this.items = items;
    }
}