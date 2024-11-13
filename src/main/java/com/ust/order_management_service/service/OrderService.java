package com.ust.order_management_service.service;

import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
    User createUser(User user);
    Optional<User> getUserById(Long id);
}
