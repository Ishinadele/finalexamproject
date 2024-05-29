package com.example.finalexamproject.DTO;


import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Date;

@Data
public class OrderDTO {
    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemDTO> orderItems;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Order date is required")
    private Date orderDate;
}

