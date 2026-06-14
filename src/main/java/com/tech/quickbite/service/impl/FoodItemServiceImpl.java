package com.tech.quickbite.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.FoodItemRequest;
import com.tech.quickbite.dto.response.FoodItemResponse;
import com.tech.quickbite.entity.Category;
import com.tech.quickbite.entity.FoodItem;
import com.tech.quickbite.entity.Restaurant;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.exception.UnauthorizedException;
import com.tech.quickbite.repository.CategoryRepository;
import com.tech.quickbite.repository.FoodItemRepository;
import com.tech.quickbite.repository.RestaurantRepository;
import com.tech.quickbite.service.FoodItemService;
import com.tech.quickbite.service.UserService;

@Service
public class FoodItemServiceImpl
        implements FoodItemService {

    private FoodItemRepository foodItemRepository;

    private RestaurantRepository restaurantRepository;

    private CategoryRepository categoryRepository;

    private UserService userService;

    public FoodItemServiceImpl(
            FoodItemRepository foodItemRepository,
            RestaurantRepository restaurantRepository,
            CategoryRepository categoryRepository,
            UserService userService) {

        this.foodItemRepository = foodItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public FoodItemResponse createFoodItem(
            FoodItemRequest request) {

        User currentUser =
                userService.getCurrentUser();

        Restaurant restaurant =
                restaurantRepository.findById(
                        request.getRestaurantId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurant not found"));

        if (!restaurant.getOwner()
                .getId()
                .equals(currentUser.getId())) {

            throw new UnauthorizedException(
                    "You can manage only your own restaurant");
        }

        Category category =
                categoryRepository.findById(
                        request.getCategoryId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Category not found"));

        FoodItem foodItem =
                new FoodItem();

        foodItem.setName(request.getName());
        foodItem.setDescription(
                request.getDescription());
        foodItem.setPrice(
                request.getPrice());

        foodItem.setAvailable(true);

        foodItem.setRestaurant(
                restaurant);

        foodItem.setCategory(
                category);

        FoodItem savedFoodItem =
                foodItemRepository.save(foodItem);

        return mapToResponse(savedFoodItem);
    }

    @Override
    public List<FoodItemResponse>
    getAllFoodItems() {

        return foodItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodItemResponse>
    getRestaurantMenu(
            Long restaurantId) {

        Restaurant restaurant =
                restaurantRepository.findById(
                        restaurantId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurant not found"));

        return foodItemRepository
                .findByRestaurant(restaurant)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFoodItem(
            Long foodItemId) {

        FoodItem foodItem =
                foodItemRepository.findById(
                        foodItemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Food item not found"));

        User currentUser =
                userService.getCurrentUser();

        if (!foodItem.getRestaurant()
                .getOwner()
                .getId()
                .equals(currentUser.getId())) {

            throw new UnauthorizedException(
                    "Unauthorized");
        }

        foodItemRepository.delete(foodItem);
    }

    private FoodItemResponse mapToResponse(
            FoodItem foodItem) {

        return new FoodItemResponse(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDescription(),
                foodItem.getPrice(),
                foodItem.getAvailable(),
                foodItem.getRestaurant().getId(),
                foodItem.getCategory().getId()
        );
    }
}