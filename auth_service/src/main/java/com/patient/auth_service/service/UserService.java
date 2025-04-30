package com.patient.auth_service.service;

import com.patient.auth_service.model.User;
import com.patient.auth_service.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User>
    findByEmail(@NotBlank(message = "Email is required")
                @Email(message = "Email should be a valid email address") String email) {
        return userRepository.findByEmail(email);
    }
}
