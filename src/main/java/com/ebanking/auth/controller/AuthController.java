package com.ebanking.auth.controller;

import com.ebanking.auth.application.RegisterUserUseCase;
import com.ebanking.auth.domain.User;
import com.ebanking.auth.dto.RegisterUserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterUserRequest request
    ) {
        return registerUserUseCase.execute(request);
    }
}
