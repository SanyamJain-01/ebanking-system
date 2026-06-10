package com.ebanking.auth.dto;

public record RegisterUserRequest(
        String customerId,
        String firstName,
        String lastName,
        String email,
        String mobileNumber,
        String password
) {
}
