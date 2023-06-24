package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.AuthDto;
import com.carrotinstitutefooddelivery.request.LoginRequest;
import com.carrotinstitutefooddelivery.request.RegisterRequest;
import com.carrotinstitutefooddelivery.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthDto registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public AuthDto userLogin(@Valid @RequestBody LoginRequest loginRequest){
        return authService.userLogin(loginRequest);
    }
}
