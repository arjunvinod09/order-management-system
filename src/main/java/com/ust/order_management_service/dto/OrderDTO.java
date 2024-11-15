package com.ust.order_management_service.dto;

import com.ust.order_management_service.model.OrderItem;
import com.ust.order_management_service.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderDTO(
//        Long id,
        @NotNull(message = "User ID must not be null")
        Long userId,

        @NotNull(message = "Order status must not be null")
        OrderStatus status,

        @NotNull(message = "Total amount must not be null")
        @Positive(message = "Total amount must be positive")
        Double totalAmount,
//        LocalDateTime createdAt,

        @NotNull(message = "Order items must not be null")
        List<OrderItem> items
) {
}
