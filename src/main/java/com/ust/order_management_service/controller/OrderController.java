package com.ust.order_management_service.controller;

import com.ust.order_management_service.convert.Convertor;
import com.ust.order_management_service.dto.OrderDTO;
import com.ust.order_management_service.dto.UserDTO;
import com.ust.order_management_service.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    Convertor convertor;

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderdto){
        orderService.createOrder(convertor.toEntity(orderdto));
        return new ResponseEntity<>(orderdto, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(convertor.toDTO(orderService.getOrderById(id).get()), HttpStatus.OK);
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(convertor.toList(orderService.getAllOrdersByUserId(userId)), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto){
        orderService.createUser(convertor.toEntity(userdto));
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(convertor.toDTO(orderService.getUserById(userId).get()), HttpStatus.OK);
    }
}
