package com.tech.quickbite.dto.response;

public class OrderItemResponse {

    private String foodItemName;

    private Integer quantity;

    private Double subtotal;

    public OrderItemResponse() {
    }

    public OrderItemResponse(
            String foodItemName,
            Integer quantity,
            Double subtotal) {

        this.foodItemName = foodItemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setFoodItemName(
            String foodItemName) {

        this.foodItemName = foodItemName;
    }

    public void setQuantity(
            Integer quantity) {

        this.quantity = quantity;
    }

    public void setSubtotal(
            Double subtotal) {

        this.subtotal = subtotal;
    }
}