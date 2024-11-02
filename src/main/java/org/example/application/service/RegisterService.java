package org.example.application.service;

import org.example.adapter.persistence.UserJpaRepository;
import org.example.application.port.input.RegisterUserUseCase;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements RegisterUserUseCase {
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public RegisterService(UserJpaRepository userJpaRepository ) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User registerUser(User user) {
        // Check if username already exists
        if (userJpaRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

//        // Create and save the user



        return userJpaRepository.save(user);
    }
}
