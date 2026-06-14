package com.tech.quickbite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.quickbite.entity.Restaurant;
import com.tech.quickbite.entity.User;

public interface RestaurantRepository
        extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByOwner(User owner);
}