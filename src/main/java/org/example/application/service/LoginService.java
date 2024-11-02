package org.example.application.service;

import org.example.adapter.persistence.UserJpaRepository;
import org.example.application.port.input.LoginUserUseCase;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements LoginUserUseCase {
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public LoginService(UserJpaRepository userJpaRepository ) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public Optional<User> login(String username, String password) {
        Optional<User> userOptional = userJpaRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verify the password using a password hashing library like BCrypt
            if (password.equals(user.getPassword())) {
                return userOptional;
            }
        }

        return Optional.empty();
    }
}
