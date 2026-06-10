package com.ebanking.auth.dto;

public record RegisterUserResponse(
        Long id,
        String customerId,
        String firstName,
        String lastName,
        String email,
        String mobileNumber
) {
}
