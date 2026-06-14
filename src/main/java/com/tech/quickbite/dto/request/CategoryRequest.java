package com.tech.quickbite.dto.request;

public class CategoryRequest {

    private String name;

    private Long restaurantId;

    public CategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}