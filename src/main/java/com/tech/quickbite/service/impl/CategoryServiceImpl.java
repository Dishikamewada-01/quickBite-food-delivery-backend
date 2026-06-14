package com.tech.quickbite.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tech.quickbite.dto.request.CategoryRequest;
import com.tech.quickbite.dto.response.CategoryResponse;
import com.tech.quickbite.entity.Category;
import com.tech.quickbite.entity.Restaurant;
import com.tech.quickbite.entity.User;
import com.tech.quickbite.exception.ResourceNotFoundException;
import com.tech.quickbite.exception.UnauthorizedException;
import com.tech.quickbite.repository.CategoryRepository;
import com.tech.quickbite.repository.RestaurantRepository;
import com.tech.quickbite.service.CategoryService;
import com.tech.quickbite.service.UserService;

@Service
public class CategoryServiceImpl
        implements CategoryService {

    private CategoryRepository categoryRepository;

    private RestaurantRepository restaurantRepository;

    private UserService userService;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            RestaurantRepository restaurantRepository,
            UserService userService) {

        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    @Override
    public CategoryResponse createCategory(
            CategoryRequest request) {

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
                    "You can only manage your own restaurant");
        }

        Category category = new Category();

        category.setName(request.getName());

        category.setRestaurant(restaurant);

        Category savedCategory =
                categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse>
    getCategoriesByRestaurant(
            Long restaurantId) {

        Restaurant restaurant =
                restaurantRepository.findById(
                        restaurantId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurant not found"));

        return categoryRepository
                .findByRestaurant(restaurant)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CategoryResponse mapToResponse(
            Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getRestaurant().getId()
        );
    }
}