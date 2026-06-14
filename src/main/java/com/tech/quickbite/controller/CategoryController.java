package com.tech.quickbite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.quickbite.dto.request.CategoryRequest;
import com.tech.quickbite.dto.response.CategoryResponse;
import com.tech.quickbite.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse>
    createCategory(
            @RequestBody CategoryRequest request) {

        return ResponseEntity.ok(
                categoryService.createCategory(
                        request));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>>
    getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<CategoryResponse>>
    getCategoriesByRestaurant(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                categoryService
                        .getCategoriesByRestaurant(
                                restaurantId));
    }
}