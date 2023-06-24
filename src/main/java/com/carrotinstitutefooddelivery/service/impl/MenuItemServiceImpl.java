package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.converter.MenuItemConverter;
import com.carrotinstitutefooddelivery.dto.MenuItemDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.MenuItem;
import com.carrotinstitutefooddelivery.repository.MenuItemsRepository;
import com.carrotinstitutefooddelivery.repository.RestaurantRepository;
import com.carrotinstitutefooddelivery.request.MenuItemRequest;
import com.carrotinstitutefooddelivery.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.MenuItemConverter.menuItemEntityToDto;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemsRepository menuItemsRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public MenuItemDto saveMenuItem(MenuItemRequest menuItemRequest) {
        var restaurant = restaurantRepository.findById(menuItemRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find restaurant with ID: " + menuItemRequest.getRestaurantId()));
        var menu = MenuItem.builder()
                .name(menuItemRequest.getName())
                .description(menuItemRequest.getDescription())
                .restaurant(restaurant)
                .price(menuItemRequest.getPrice())
                .build();
        var savedMenu = menuItemsRepository.save(menu);
        return menuItemEntityToDto(savedMenu);
    }

    @Override
    public List<MenuItemDto> findAllMenuItem() {
        return menuItemsRepository.findAll()
                .stream()
                .map(MenuItemConverter::menuItemEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDto findMenuItemById(UUID menuItemId) {
        var menuItem = menuItemsRepository.findById(menuItemId)
                .orElseThrow(()->new ResourceNotFoundException("Could not find menu item with Id: "+ menuItemId));
        return menuItemEntityToDto(menuItem);
    }

    @Override
    public MenuItemDto updateMenuItem(MenuItemRequest menuItemRequest, UUID menuItemId) {
        var menuItem = menuItemsRepository.findById(menuItemId)
                .orElseThrow(()->new ResourceNotFoundException("Could not find menu item with Id: "+ menuItemId));
        menuItem.setName(menuItemRequest.getName());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setRestaurant(restaurantRepository.findById(menuItemRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find restaurant with ID: " + menuItemRequest.getRestaurantId())));
        return menuItemEntityToDto(menuItemsRepository.save(menuItem));
    }

    @Override
    public void deleteMenuItemById(UUID menuItemId) {
        if(!menuItemsRepository.existsById(menuItemId)){
            throw new ResourceNotFoundException("Cannot find restaurant with id: "+ menuItemId);
        }
        menuItemsRepository.deleteById(menuItemId);
    }
}
