package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.request.UserRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface UserDetailsService {
    UserDto saveUser (UserRequest userRequest);
    List<UserDto> findAllUsers();
    UserDto findUserById(UUID userId);

    UserDto updateUser(UserRequest userRequest, UUID userId);

    UserDto updateUser(UserRequest userRequest, UUID userId, String userRole);
}
