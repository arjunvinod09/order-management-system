package com.ust.order_management_service;

import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.model.OrderItem;
import com.ust.order_management_service.model.OrderStatus;
import com.ust.order_management_service.model.User;
import com.ust.order_management_service.repository.OrderRepository;
import com.ust.order_management_service.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private Order order;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        ArrayList<OrderItem> items = new ArrayList<>();
        order = new Order(1L, 100L, OrderStatus.PROCESSING, 123.45, LocalDateTime.now() , items);
        items.add(new OrderItem(1L,100L,"Wireless Mouse",1,25.5, order));
        items.add(new OrderItem(2L,101L,"Mechanical Keyboard",1,125.25, order));
        orderRepository.save(order);
    }

    @Test
    @DisplayName("Create Order")
    public void givenOrderToSaveThenShouldReturnSavedOrder(){
       when(orderRepository.save(any(Order.class))).thenReturn(order);
       Order savedOrder = orderService.createOrder(order);
       assertNotNull(savedOrder);
       assertEquals(savedOrder.getId(), order.getId());
    }
    @Test
    @DisplayName("Get Order By Id")
    public void givenOrderIdThenShouldReturnRespectiveOrder(){
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        Optional<Order> foundOrder = orderService.getOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(foundOrder.get().getId(), order.getId());
    }

    @Test
    @DisplayName("Get All Orders By UserId")
    public void givenUserIdThenShouldReturnAllItsOrders(){
        when(orderRepository.findByUserId(order.getUserId())).thenReturn(List.of(order));
        List<Order> allOrders = orderService.getAllOrdersByUserId(order.getUserId());
        assertNotNull(allOrders);
        assertEquals(1, allOrders.size());
        assertEquals(allOrders.get(0).getUserId(), order.getUserId());
    }

}
