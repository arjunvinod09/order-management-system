package com.ust.order_management_service;

import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.model.OrderItem;
import com.ust.order_management_service.model.OrderStatus;
import com.ust.order_management_service.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    private Order order;

    @BeforeEach
    void setUp(){
        ArrayList<OrderItem> items = new ArrayList<>();
        order = new Order(1L, 100L, OrderStatus.PROCESSING, 123.45, LocalDateTime.now() , items);
        items.add(new OrderItem(1L,100L,"Wireless Mouse",1,25.5, order));
        items.add(new OrderItem(2L,101L,"Mechanical Keyboard",1,125.25, order));
        orderRepository.save(order);
//        System.out.println("Order saved successfully " + order);
    }

    @Test
    @DisplayName("Order history retreival")
    public void givesAllOrderByUserId() {
        List<Order> orders = (List<Order>) orderRepository.findByUserId(order.getUserId());
        assertEquals(orders.getFirst().getId(), order.getId());
        assertEquals(orders.getFirst().getUserId(), order.getUserId());
        assertEquals(orders.getFirst().getStatus(), order.getStatus());
        assertEquals(orders.getFirst().getTotalAmount(), order.getTotalAmount());
    }
}

