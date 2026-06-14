package com.tech.quickbite.dto.response;

public class CartItemResponse {

    private Long cartItemId;

    private String foodItemName;

    private Integer quantity;

    private Double price;

    private Double subtotal;

    public CartItemResponse() {
    }

    public CartItemResponse(
            Long cartItemId,
            String foodItemName,
            Integer quantity,
            Double price,
            Double subtotal) {

        this.cartItemId = cartItemId;
        this.foodItemName = foodItemName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}