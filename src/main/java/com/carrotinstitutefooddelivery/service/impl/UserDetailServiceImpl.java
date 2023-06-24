package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.constant.UserType;
import com.carrotinstitutefooddelivery.converter.UserConverter;
import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.User;
import com.carrotinstitutefooddelivery.repository.UserRepository;
import com.carrotinstitutefooddelivery.request.UserRequest;
import com.carrotinstitutefooddelivery.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.UserConverter.userEntityToDto;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDto saveUser(UserRequest userRequest) {
        var user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .userType(UserType.USER)
                .password(userRequest.getPassword())
                .build();
        var savedUser = userRepository.save(user);
        return userEntityToDto(savedUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserConverter::userEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Could not find user with id: "+ userId));
        return userEntityToDto(user);
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Could not find user with id: "+ userId));
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return userEntityToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, UUID userId, String role) {
        var user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Could not find user with id: "+ userId));
        user.setUserType(UserType.valueOf(role));
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        return userEntityToDto(userRepository.save(user));
    }
}
