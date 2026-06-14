package com.tech.quickbite.dto.response;

public class CategoryResponse {

    private Long id;

    private String name;

    private Long restaurantId;

    public CategoryResponse() {
    }

    public CategoryResponse(
            Long id,
            String name,
            Long restaurantId) {

        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}