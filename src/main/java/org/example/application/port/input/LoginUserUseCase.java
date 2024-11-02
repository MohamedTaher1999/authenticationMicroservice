package org.example.application.port.input;

import org.example.domain.User;

import java.util.Optional;

public interface LoginUserUseCase {

    public Optional<User> login(String username, String password);

}
