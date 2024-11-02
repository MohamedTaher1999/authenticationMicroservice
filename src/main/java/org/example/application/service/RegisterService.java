package org.example.application.service;

import org.example.adapter.persistence.UserJpaRepository;
import org.example.application.port.input.RegisterUserUseCase;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements RegisterUserUseCase {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserJpaRepository userJpaRepository,PasswordEncoder passwordEncoder ) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        // Check if username already exists
        if (userJpaRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

//        // Create and save the user

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userJpaRepository.save(user);
    }
}
