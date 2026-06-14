package com.tech.quickbite.service;

import java.util.List;

import com.tech.quickbite.dto.request.CategoryRequest;
import com.tech.quickbite.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse createCategory(
            CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    List<CategoryResponse> getCategoriesByRestaurant(
            Long restaurantId);
}