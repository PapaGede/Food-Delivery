package com.carrotinstitutefooddelivery.service.impl;


import com.carrotinstitutefooddelivery.dto.MenuItemDto;
import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.MenuItem;
import com.carrotinstitutefooddelivery.model.Restaurant;
import com.carrotinstitutefooddelivery.repository.MenuItemsRepository;
import com.carrotinstitutefooddelivery.repository.RestaurantRepository;
import com.carrotinstitutefooddelivery.request.MenuItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
public class MenuItemServiceImplTest {

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    @Mock
    private MenuItemsRepository menuItemsRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Captor
    private ArgumentCaptor<MenuItem> menuItemArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    private Restaurant restaurant;

    private MenuItem menuItem;

    private RestaurantDto restaurantDto;

    @BeforeEach
    void setUp() {
        restaurant = Restaurant.builder()
                .restaurantId(UUID.randomUUID())
                .address("Accra")
                .name("test")
                .build();

        menuItem = MenuItem.builder()
                .menuId(UUID.randomUUID())
                .description("test description")
                .name("tester")
                .restaurant(restaurant)
                .price(22.5)
                .build();
        restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .address(restaurant.getAddress())
                .name(restaurant.getName())
                .build();
    }

    @Test
    void testSaveMenuItem() {
        var menuItemRequest = MenuItemRequest.builder()
                .restaurantId(restaurant.getRestaurantId())
                .description("test description")
                .name("tester")
                .price(22.5)
                .build();
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        when(menuItemsRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        var response = menuItemService.saveMenuItem(menuItemRequest);
        var menuItemDto = MenuItemDto.builder()
                .menuId(response.getMenuId())
                .description("test description")
                .name("tester")
                .restaurantDto(restaurantDto)
                .price(22.5)
                .build();
        menuItem.setMenuId(null);
        verify(restaurantRepository, times(1)).findById(uuidArgumentCaptor.capture());
        verify(menuItemsRepository, times(1)).save(menuItemArgumentCaptor.capture());
        assertThat(restaurantDto.getRestaurantId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(response).usingRecursiveComparison().isEqualTo(menuItemDto);
        assertThat(menuItem).usingRecursiveComparison().isEqualTo(menuItemArgumentCaptor.getValue());
    }

    @Test
    void testFindAllMenuItems() {
        var menuItems = List.of(menuItem);
        when(menuItemsRepository.findAll()).thenReturn(menuItems);
        var response = menuItemService.findAllMenuItem();
        var menuItemDto = MenuItemDto.builder()
                .menuId(menuItem.getMenuId())
                .name("tester")
                .description("test description")
                .restaurantDto(restaurantDto)
                .price(22.5)
                .build();
        assertThat(response).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(List.of(menuItemDto));
    }

    @Test
    void testFindMenuItemById() {
        when(menuItemsRepository.findById(any())).thenReturn(Optional.of(menuItem));
        menuItemService.findMenuItemById(menuItem.getMenuId());
        verify(menuItemsRepository, times(1)).findById(uuidArgumentCaptor.capture());
        assertThat(menuItem.getMenuId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testMenuItemByIdThrowsException() {
        var uuid = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> {
            when(menuItemsRepository.findById(uuid)).thenReturn(Optional.empty());
            menuItemService.findMenuItemById(uuid);
        });
    }

    @Test
    void updateMenuItem() {
        var menuItemRequest = MenuItemRequest.builder()
                .restaurantId(restaurant.getRestaurantId())
                .description("actual description")
                .name("actual")
                .price(21.0)
                .build();
        var uuid = UUID.randomUUID();

        when(menuItemsRepository.findById(any())).thenReturn(Optional.of(menuItem));
        when(menuItemsRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        menuItemService.updateMenuItem(menuItemRequest, uuid);
        verify(menuItemsRepository, times(1)).findById(uuidArgumentCaptor.capture());
        verify(menuItemsRepository, times(1)).save(menuItemArgumentCaptor.capture());
        verify(restaurantRepository, times(1)).findById(any());
        assertThat(uuid).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(menuItem).usingRecursiveComparison().isEqualTo(menuItemArgumentCaptor.getValue());
    }

    @Test
    void testDeleteMenuItemById() {
        var uuid = UUID.randomUUID();
        when(menuItemsRepository.existsById(any())).thenReturn(true);
        menuItemService.deleteMenuItemById(uuid);
        verify(menuItemsRepository, times(1)).existsById(uuidArgumentCaptor.capture());
        assertThat(uuid).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testDeleteRestaurantByIdThrowsException() {
        var uuid = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> {
            when(menuItemsRepository.existsById(uuid)).thenReturn(false);
            menuItemService.deleteMenuItemById(uuid);
        });
    }
}