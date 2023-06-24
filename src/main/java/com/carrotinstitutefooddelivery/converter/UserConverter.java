package com.carrotinstitutefooddelivery.converter;

import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.model.User;

public class UserConverter {
    public static UserDto userEntityToDto(User user){
        return UserDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }
}
