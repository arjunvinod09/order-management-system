package com.ust.order_management_service.dto;

public record UserDTO(
        String userName,
        String email,
        String address,
        String phone
) {
}
