package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.AuthDto;
import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.model.User;
import com.carrotinstitutefooddelivery.request.LoginRequest;
import com.carrotinstitutefooddelivery.request.RegisterRequest;

public interface AuthService {
    AuthDto registerUser(RegisterRequest registerRequest);
    AuthDto userLogin(LoginRequest loginRequest);

    UserDto updateUser(User user, String token);
}
