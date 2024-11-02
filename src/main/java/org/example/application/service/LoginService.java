package org.example.application.service;

import org.example.adapter.persistence.UserJpaRepository;
import org.example.application.port.input.LoginUserUseCase;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements LoginUserUseCase {
    private final UserJpaRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(UserJpaRepository userJpaRepository , AuthenticationManager authenticationManager   ) {
        this.userJpaRepository = userJpaRepository;
        this.authenticationManager = authenticationManager;

    }


    @Override
    public User login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        return userJpaRepository.findByUsername(username)
                .orElseThrow();
    }
}
