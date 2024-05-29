package com.example.finalexamproject.REPOSITORY;

import com.example.finalexamproject.MODEL.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);

    List<Order> findByUserEmail(String email);
}
