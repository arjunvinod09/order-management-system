package com.ust.order_management_service;

import com.ust.order_management_service.controller.OrderController;
import com.ust.order_management_service.convert.Convertor;
import com.ust.order_management_service.dto.OrderDTO;
import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.model.OrderItem;
import com.ust.order_management_service.model.OrderStatus;
import com.ust.order_management_service.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Mock
    OrderServiceImpl orderService;

    @Mock
    Convertor convertor;

    @InjectMocks
    OrderController orderController;

    private Order order;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        ArrayList<OrderItem> items = new ArrayList<>();
        order = new Order(1L, 100L, OrderStatus.PROCESSING, 123.45, LocalDateTime.now() , items);
        items.add(new OrderItem(1L,100L,"Wireless Mouse",1,25.5, order));
        items.add(new OrderItem(2L,101L,"Mechanical Keyboard",1,125.25, order));
        orderService.createOrder(order);
    }

    @Test
    @DisplayName("Created Order")
    public void givenOrderToSaveThenShouldReturnOrder(){
        when(orderService.createOrder(any(Order.class))).thenReturn(order);
        ResponseEntity<OrderDTO> response = orderController.createOrder(convertor.toDTO(order));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), convertor.toDTO(order));

    }

    @Test
    @DisplayName("Get Order By Id")
    public void givenOrderIdThenShouldReturnRespectiveOrder(){
        when(orderService.getOrderById(order.getId())).thenReturn(Optional.of(order));
        ResponseEntity<Object> response = orderController.getOrderById(order.getId());
        System.out.println(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), convertor.toDTO(order));
    }

    @Test
    @DisplayName("Get All Orders By UserId")
    public void givenUserIdThenShouldReturnAllOrders(){
        when(orderService.getAllOrdersByUserId(order.getUserId())).thenReturn(List.of(order));
        ResponseEntity<Object> response = orderController.getAllOrdersByUserId(order.getUserId());
//        List<OrderDTO> body = (List<OrderDTO>) response.getBody();
//        assertEquals(1,List.of(convertor.toDTO(order)).size());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response.getBody());//List<OrderDto>
        assertEquals(response.getBody(), List.of(convertor.toDTO(order)));
    }
}
