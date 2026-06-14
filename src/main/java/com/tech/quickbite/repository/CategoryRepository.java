package com.tech.quickbite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.quickbite.entity.Category;
import com.tech.quickbite.entity.Restaurant;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    List<Category> findByRestaurant(Restaurant restaurant);
}