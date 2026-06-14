package com.tech.quickbite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.quickbite.dto.request.FoodItemRequest;
import com.tech.quickbite.dto.response.FoodItemResponse;
import com.tech.quickbite.service.FoodItemService;

@RestController
@RequestMapping("/api/food-items")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(
            FoodItemService foodItemService) {

        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemResponse>
    createFoodItem(
            @RequestBody FoodItemRequest request) {

        return ResponseEntity.ok(
                foodItemService.createFoodItem(
                        request));
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponse>>
    getAllFoodItems() {

        return ResponseEntity.ok(
                foodItemService.getAllFoodItems());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItemResponse>>
    getRestaurantMenu(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                foodItemService.getRestaurantMenu(
                        restaurantId));
    }

    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<String>
    deleteFoodItem(
            @PathVariable Long foodItemId) {

        foodItemService.deleteFoodItem(
                foodItemId);

        return ResponseEntity.ok(
                "Food Item Deleted Successfully");
    }
}