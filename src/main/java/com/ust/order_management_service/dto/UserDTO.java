package com.ust.order_management_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "Username must not be empty")
        String userName,

        @NotEmpty(message = "Email must not be empty")
        @Email(message = "Email should be valid")
        String email,

        @NotEmpty(message = "Address must not be empty")
        String address,

        @NotEmpty(message = "Phone number must not be empty")
        @Size(min = 10, max = 15, message = "Phone number must be 10 and 15 DIGITS")
        String phone
) {
}
