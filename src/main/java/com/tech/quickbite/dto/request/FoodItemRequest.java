package com.tech.quickbite.dto.request;

public class FoodItemRequest {

    private String name;

    private String description;

    private Double price;

    private Long restaurantId;

    private Long categoryId;

    public FoodItemRequest() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}