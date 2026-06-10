package com.ebanking.auth.application;

import com.ebanking.auth.domain.Role;
import com.ebanking.auth.domain.User;
import com.ebanking.auth.domain.UserStatus;
import com.ebanking.auth.dto.RegisterUserRequest;
import com.ebanking.auth.infrastructure.UserRepository;
import com.ebanking.shared.exception.DuplicateResourceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(RegisterUserRequest request) {

   	 if (userRepository.findByEmail(request.email()).isPresent()) {
        	throw new DuplicateResourceException(
                	"Email already exists"
       			 );
    		}

   	 if (userRepository.findByMobileNumber(request.mobileNumber()).isPresent()) {
        	throw new DuplicateResourceException(
                	"Mobile number already exists"
       			 );
   		 }

  	  if (userRepository.findByCustomerId(request.customerId()).isPresent()) {
       		 throw new DuplicateResourceException(
               		 "Customer ID already exists"
       			 );
	    }
  	User user = new User();

        user.setCustomerId(request.customerId());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setMobileNumber(request.mobileNumber());

        user.setPassword(
                passwordEncoder.encode(request.password())
        );

        user.setRole(Role.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }
}
