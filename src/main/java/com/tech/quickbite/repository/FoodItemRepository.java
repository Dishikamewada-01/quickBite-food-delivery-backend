package com.tech.quickbite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.quickbite.entity.FoodItem;
import com.tech.quickbite.entity.Restaurant;
import com.tech.quickbite.entity.Category;

public interface FoodItemRepository
        extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByRestaurant(
            Restaurant restaurant);

    List<FoodItem> findByCategory(
            Category category);
}