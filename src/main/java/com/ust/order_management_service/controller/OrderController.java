package com.ust.order_management_service.controller;

import com.ust.order_management_service.convert.Convertor;
import com.ust.order_management_service.dto.OrderDTO;
import com.ust.order_management_service.dto.UserDTO;
import com.ust.order_management_service.exception.OrderNotFoundException;
import com.ust.order_management_service.exception.UserNotFoundException;
import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    Convertor convertor;

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderdto){
        var user = orderService.getUserById(orderdto.userId()).orElseThrow(() -> new UserNotFoundException("No user with id "+ orderdto.userId() +" exists inorder to place order"));
        orderService.createOrder(convertor.toEntity(orderdto));
        return new ResponseEntity<>(orderdto, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id){
//        return new ResponseEntity<>(.orElseThrow(() -> new OrderNotFoundException("No such order exists"))), HttpStatus.OK);
        var order = orderService.getOrderById(id);
        try{
            Order foundOrder = order.orElseThrow(() -> new OrderNotFoundException("No such order exists with id " + id));
            return new ResponseEntity<>(convertor.toDTO(foundOrder),HttpStatus.OK);
        }
        catch (OrderNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<Object> getAllOrdersByUserId(@PathVariable Long userId){
//        return new ResponseEntity<>(convertor.toList(orderService.getAllOrdersByUserId(userId)), HttpStatus.OK);
        var listOfOrders = orderService.getAllOrdersByUserId(userId);
        try{
            if(listOfOrders.isEmpty()){
                var user = orderService.getUserById(userId).orElseThrow(() -> new UserNotFoundException("No user with id "+ userId +" exists inorder to access order history"));
                throw new OrderNotFoundException("User has not placed any orders yet");
            }
            return new ResponseEntity<>(convertor.toList(listOfOrders),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto){
        orderService.createUser(convertor.toEntity(userdto));
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId){
//        return new ResponseEntity<>(convertor.toDTO(orderService.getUserById(userId).orElseThrow(() -> new UserNotFoundException("No such user exists"))), HttpStatus.OK);
        var user = orderService.getUserById(userId);
        try{
            var foundUser = user.orElseThrow(() -> new UserNotFoundException("No such user exists with id " + userId));
            return new ResponseEntity<>(convertor.toDTO(foundUser) , HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
