package com.tech.quickbite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.quickbite.entity.Order;
import com.tech.quickbite.entity.User;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(User customer);
}