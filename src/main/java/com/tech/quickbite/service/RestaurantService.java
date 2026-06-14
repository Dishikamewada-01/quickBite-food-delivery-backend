package com.tech.quickbite.service;

import java.util.List;

import com.tech.quickbite.dto.request.RestaurantRequest;
import com.tech.quickbite.dto.response.RestaurantResponse;

public interface RestaurantService {

    RestaurantResponse createRestaurant(
            RestaurantRequest request);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(
            Long restaurantId);
}