package com.carrotinstitutefooddelivery.converter;

import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.model.Restaurant;

public class RestaurantConverter {
    public static RestaurantDto restaurantEntityToDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .contact(restaurant.getContact())
                .build();
    }
}
