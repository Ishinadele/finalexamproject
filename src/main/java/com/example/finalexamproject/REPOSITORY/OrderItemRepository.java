package com.example.finalexamproject.REPOSITORY;

import com.example.finalexamproject.MODEL.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
