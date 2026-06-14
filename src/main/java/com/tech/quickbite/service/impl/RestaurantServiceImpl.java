package com.tech.quickbite.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.RestaurantRequest;
import com.tech.quickbite.dto.response.RestaurantResponse;
import com.tech.quickbite.entity.Restaurant;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.enums.Role;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.exception.UnauthorizedException;
import com.tech.quickbite.repository.RestaurantRepository;
import com.tech.quickbite.service.RestaurantService;
import com.tech.quickbite.service.UserService;

@Service
public class RestaurantServiceImpl
        implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private UserService userService;

    public RestaurantServiceImpl(
            RestaurantRepository restaurantRepository,
            UserService userService) {

        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    @Override
    public RestaurantResponse createRestaurant(
            RestaurantRequest request) {

        User owner = userService.getCurrentUser();

        if (owner.getRole() != Role.RESTAURANT_OWNER) {

            throw new UnauthorizedException(
                    "Only Restaurant Owners can create restaurants");
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setCity(request.getCity());

        restaurant.setRating(0.0);

        restaurant.setOwner(owner);

        Restaurant savedRestaurant =
                restaurantRepository.save(restaurant);

        return mapToResponse(savedRestaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {

        List<Restaurant> restaurants =
                restaurantRepository.findAll();

        return restaurants.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse getRestaurantById(
            Long restaurantId) {

        Restaurant restaurant =
                restaurantRepository.findById(
                        restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found"));

        return mapToResponse(restaurant);
    }

    private RestaurantResponse mapToResponse(
            Restaurant restaurant) {

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getCity(),
                restaurant.getRating()
        );
    }
}