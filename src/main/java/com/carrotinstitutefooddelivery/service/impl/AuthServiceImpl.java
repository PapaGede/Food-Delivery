package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.constant.UserType;
import com.carrotinstitutefooddelivery.dto.AuthDto;
import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.exceptions.DuplicateEmailException;
import com.carrotinstitutefooddelivery.exceptions.UnauthorizedException;
import com.carrotinstitutefooddelivery.model.User;
import com.carrotinstitutefooddelivery.repository.UserRepository;
import com.carrotinstitutefooddelivery.request.LoginRequest;
import com.carrotinstitutefooddelivery.request.RegisterRequest;
import com.carrotinstitutefooddelivery.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthDto registerUser(RegisterRequest registerRequest) {
        var existingUser = userRepository.findUserByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()){
            throw new DuplicateEmailException("Email already exists");
        }

        var user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .userType(UserType.USER)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .token(UUID.randomUUID().toString())
                .build();
        var savedUser = userRepository.save(user);
        return entityToDto(savedUser);
    }

    @Override
    public AuthDto userLogin(LoginRequest loginRequest) {
        var user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UnauthorizedException("Cannot find this email"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong password");
        }
        user.setToken(UUID.randomUUID().toString());

        return entityToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(User user, String token) {
        return null;
    }

    private AuthDto entityToDto(User user){
        var authDto = new AuthDto();

        BeanUtils.copyProperties(user, authDto);

        return authDto;
    }
}
