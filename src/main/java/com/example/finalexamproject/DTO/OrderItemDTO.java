package com.example.finalexamproject.DTO;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class OrderItemDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Price is required")
    private Double price;
}

