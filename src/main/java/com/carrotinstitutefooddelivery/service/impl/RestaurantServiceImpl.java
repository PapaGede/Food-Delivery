package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.converter.RestaurantConverter;
import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.Restaurant;
import com.carrotinstitutefooddelivery.repository.RestaurantRepository;
import com.carrotinstitutefooddelivery.request.RestaurantRequest;
import com.carrotinstitutefooddelivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.RestaurantConverter.restaurantEntityToDto;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantDto saveRestaurant(RestaurantRequest request) {
        var restaurantEntity = Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .contact(request.getContact())
                .build();
        var savedRestaurant = restaurantRepository.save(restaurantEntity);

        return restaurantEntityToDto(savedRestaurant);
    }

    @Override
    public List<RestaurantDto> findAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantConverter::restaurantEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDto findRestaurantById(UUID restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new ResourceNotFoundException("Cannot find restaurant with id: "+ restaurantId));
        return restaurantEntityToDto(restaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantRequest request, UUID restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new ResourceNotFoundException("Cannot find restaurant with id: "+ restaurantId));
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setContact(request.getContact());
        return restaurantEntityToDto(restaurantRepository.save(restaurant));
    }

    @Override
    public void deleteRestaurantById(UUID restaurantId) {
        if(!restaurantRepository.existsById(restaurantId)){
            throw new ResourceNotFoundException("Cannot find restaurant with id: "+ restaurantId);
        }
        restaurantRepository.deleteById(restaurantId);
    }
}
