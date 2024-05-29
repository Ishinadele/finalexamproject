package com.example.finalexamproject.SERVICE;

import com.example.finalexamproject.DTO.OrderDTO;
import com.example.finalexamproject.MODEL.Order;
import com.example.finalexamproject.MODEL.OrderItem;
import com.example.finalexamproject.MODEL.Product;
import com.example.finalexamproject.REPOSITORY.OrderRepository;
import com.example.finalexamproject.REPOSITORY.ProductRepository;
import com.example.finalexamproject.REPOSITORY.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .totalAmount(orderDTO.getTotalAmount())
                .orderDate(orderDTO.getOrderDate())
                .user(userRepository.findById(Math.toIntExact(orderDTO.getUserId())).orElseThrow(() -> new RuntimeException("User not found")))
                .orderItems(orderDTO.getOrderItems().stream().map(itemDTO -> {
                    Product product = productRepository.findById(Math.toIntExact(itemDTO.getProductId())).orElseThrow(() -> new RuntimeException("Product not found"));
                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDTO.getQuantity())
                            .price(itemDTO.getPrice())
                            .build();
                }).collect(Collectors.toList()))
                .build();

        return orderRepository.save(order);
    }

    public List<Order> findOrdersByUserEmail(String email) {
        return orderRepository.findByUserEmail(email);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
