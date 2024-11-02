package org.example.adapter.web;

import org.example.domain.User;
import org.example.application.service.LoginService;
import org.example.application.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;

    public AuthController(RegisterService registerService, LoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(registerService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(loginService.login(username, password));
    }
}
