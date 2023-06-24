package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.request.RestaurantRequest;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    RestaurantDto saveRestaurant(RestaurantRequest request);

    List<RestaurantDto> findAllRestaurants();

    RestaurantDto findRestaurantById(UUID restaurantId);
    RestaurantDto updateRestaurant(RestaurantRequest request, UUID restaurantId);

    void deleteRestaurantById(UUID restaurantId);
}
