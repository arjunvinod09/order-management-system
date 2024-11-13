package com.ust.order_management_service.convert;

import com.ust.order_management_service.dto.OrderDTO;
import com.ust.order_management_service.dto.UserDTO;
import com.ust.order_management_service.model.Order;
import com.ust.order_management_service.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convertor {
    public Order toEntity(OrderDTO orderDTO){
        return Order.builder()
                .userId(orderDTO.userId())
                .status(orderDTO.status())
                .totalAmount(orderDTO.totalAmount())
                .items(orderDTO.items())
                .build();
    }

    public OrderDTO toDTO(Order order){
        return new OrderDTO(order.getUserId(),order.getStatus(), order.getTotalAmount(), order.getItems());
    }

    public List<OrderDTO> toList(List<Order> orders){
        ArrayList<OrderDTO> orderlist= new ArrayList<>();
        for(Order order: orders){
            orderlist.add(toDTO(order));
        }
        return orderlist;
    }

    public User toEntity(UserDTO userDTO){
        return User.builder()
                .userName(userDTO.userName())
                .email(userDTO.email())
                .phone(userDTO.phone())
                .address(userDTO.address())
                .build();
    }

    public UserDTO toDTO(User user){
        return new UserDTO(user.getUserName(), user.getEmail(), user.getAddress(), user.getPhone());
    }
}
