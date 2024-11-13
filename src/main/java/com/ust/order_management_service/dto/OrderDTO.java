package com.ust.order_management_service.dto;

import com.ust.order_management_service.model.OrderItem;
import com.ust.order_management_service.model.OrderStatus;

import java.util.List;

public record OrderDTO(
//        Long id,
        Long userId,
        OrderStatus status,
        Double totalAmount,
//        LocalDateTime createdAt,
        List<OrderItem> items
) {
}
