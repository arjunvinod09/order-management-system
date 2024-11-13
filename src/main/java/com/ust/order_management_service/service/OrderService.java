package com.ust.order_management_service.service;

import com.ust.order_management_service.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
}
