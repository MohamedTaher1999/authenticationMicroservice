package org.example.adapter.web;

import org.example.application.service.JwtService;
import org.example.domain.LoginResponse;
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
    private final JwtService jwtService;

    public AuthController(RegisterService registerService, LoginService loginService,JwtService jwtService) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.jwtService = jwtService;

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(registerService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
        User authenticatedUser = loginService.login(username,password);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);

 //       return ResponseEntity.ok(loginService.login(username, password));
    }
}
