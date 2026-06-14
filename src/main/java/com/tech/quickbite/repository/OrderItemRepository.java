package com.tech.quickbite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.quickbite.entity.Order;
import com.tech.quickbite.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);
}