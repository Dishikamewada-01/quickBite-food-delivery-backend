package com.tech.quickbite.service;

import java.util.List;

import com.tech.quickbite.dto.request.FoodItemRequest;
import com.tech.quickbite.dto.response.FoodItemResponse;

public interface FoodItemService {

    FoodItemResponse createFoodItem(
            FoodItemRequest request);

    List<FoodItemResponse> getAllFoodItems();

    List<FoodItemResponse> getRestaurantMenu(
            Long restaurantId);

    void deleteFoodItem(
            Long foodItemId);
}