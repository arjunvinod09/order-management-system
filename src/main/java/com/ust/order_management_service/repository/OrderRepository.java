package com.ust.order_management_service.repository;

import com.ust.order_management_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {
    List<Order> findByUserId(Long id);
}
