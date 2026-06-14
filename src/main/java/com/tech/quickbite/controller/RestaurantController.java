package com.tech.quickbite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.quickbite.dto.request.RestaurantRequest;
import com.tech.quickbite.dto.response.RestaurantResponse;
import com.tech.quickbite.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(
            RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse>
    createRestaurant(
            @RequestBody RestaurantRequest request) {

        return ResponseEntity.ok(
                restaurantService.createRestaurant(
                        request));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>>
    getAllRestaurants() {

        return ResponseEntity.ok(
                restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse>
    getRestaurantById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                restaurantService.getRestaurantById(id));
    }
}