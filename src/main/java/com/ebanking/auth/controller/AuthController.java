package com.ebanking.auth.controller;

import com.ebanking.auth.application.RegisterUserUseCase;
import com.ebanking.auth.domain.User;
import com.ebanking.auth.dto.LoginRequest;
import com.ebanking.auth.dto.RegisterUserRequest;
import com.ebanking.auth.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterUserRequest request
    ) {
        return registerUserUseCase.execute(request);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean validPassword =
                passwordEncoder.matches(
                        request.password(),
                        user.getPassword()
                );

        if (!validPassword) {
            throw new RuntimeException("Invalid password");
        }

        return "Login Successful";
    }
}
