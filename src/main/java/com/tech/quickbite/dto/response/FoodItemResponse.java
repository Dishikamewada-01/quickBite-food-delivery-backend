package com.tech.quickbite.dto.response;

public class FoodItemResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean available;

    private Long restaurantId;

    private Long categoryId;

    public FoodItemResponse() {
    }

    public FoodItemResponse(
            Long id,
            String name,
            String description,
            Double price,
            Boolean available,
            Long restaurantId,
            Long categoryId) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
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

    public Boolean getAvailable() {
        return available;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}