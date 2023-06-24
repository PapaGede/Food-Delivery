package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.Restaurant;
import com.carrotinstitutefooddelivery.repository.RestaurantRepository;
import com.carrotinstitutefooddelivery.request.RestaurantRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RestaurantServiceImplTest {

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Captor
    private ArgumentCaptor<Restaurant> restaurantCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = Restaurant.builder()
                .restaurantId(UUID.randomUUID())
                .address("Accra")
                .name("test")
                .build();
    }

    @Test
    void testSaveRestaurant() {
        var request = RestaurantRequest.builder()
                .address("Accra")
                .name("test")
                .build();
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        var response = restaurantService.saveRestaurant(request);
        var restaurantDto = RestaurantDto.builder()
                .restaurantId(response.getRestaurantId())
                .address("Accra")
                .name("test")
                .build();
        restaurant.setRestaurantId(null);
        verify(restaurantRepository, times(1)).save(restaurantCaptor.capture());
        assertThat(restaurant).usingRecursiveComparison().isEqualTo(restaurantCaptor.getValue());
        assertThat(response).usingRecursiveComparison().isEqualTo(restaurantDto);
    }

    @Test
    void testFindAllRestaurants() {
        var restaurants = List.of(restaurant);
        when(restaurantRepository.findAll()).thenReturn(restaurants);
        var response = restaurantService.findAllRestaurants();
        var restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .address("Accra")
                .name("test")
                .build();
        verify(restaurantRepository, times(1)).findAll();
        assertThat(response).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(List.of(restaurantDto));
    }

    @Test
    void testFindRestaurantById() {
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        restaurantService.findRestaurantById(restaurant.getRestaurantId());
        verify(restaurantRepository, times(1)).findById(uuidArgumentCaptor.capture());
        assertThat(restaurant.getRestaurantId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testFindRestaurantByIdThrowsException() {
        var request = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> {
            when(restaurantRepository.findById(request)).thenReturn(Optional.empty());
            restaurantService.findRestaurantById(request);
        });
    }

    @Test
    void TestUpdateRestaurant() {
        var requestBody = RestaurantRequest.builder()
                .address("Tema")
                .name("Milton")
                .build();
        var uuid = UUID.randomUUID();
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        restaurantService.updateRestaurant(requestBody, uuid);
        verify(restaurantRepository, times(1)).findById(uuidArgumentCaptor.capture());
        verify(restaurantRepository, times(1)).save(restaurantCaptor.capture());
        assertThat(uuid).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(restaurant).usingRecursiveComparison().isEqualTo(restaurantCaptor.getValue());
    }

    @Test
    void testDeleteRestaurantById() {
        var uuid = UUID.randomUUID();
        when(restaurantRepository.existsById(any())).thenReturn(true);
        restaurantService.deleteRestaurantById(uuid);
        verify(restaurantRepository, times(1)).existsById(uuidArgumentCaptor.capture());
        assertThat(uuid).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testDeleteRestaurantByIdThrowsException() {
        var uuid = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> {
            when(restaurantRepository.existsById(uuid)).thenReturn(false);
            restaurantService.deleteRestaurantById(uuid);
        });
    }
}