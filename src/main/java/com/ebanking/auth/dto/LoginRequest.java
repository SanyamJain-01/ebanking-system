package com.ebanking.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
