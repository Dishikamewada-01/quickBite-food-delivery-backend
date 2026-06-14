package com.tech.quickbite.dto.request;

public class AddToCartRequest {

    private Long foodItemId;

    private Integer quantity;

    public AddToCartRequest() {
    }

    public Long getFoodItemId() {
        return foodItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}