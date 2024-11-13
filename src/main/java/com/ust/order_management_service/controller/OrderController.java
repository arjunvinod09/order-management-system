package com.ust.order_management_service.controller;

import com.ust.order_management_service.convert.Convertor;
import com.ust.order_management_service.dto.OrderDTO;
import com.ust.order_management_service.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    Convertor convertor;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderdto){
        orderService.createOrder(convertor.toEntity(orderdto));
        return new ResponseEntity<>(orderdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(convertor.toDTO(orderService.getOrderById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(convertor.toList(orderService.getAllOrdersByUserId(userId)), HttpStatus.OK);
    }
}
