package com.carrotinstitutefooddelivery.converter;

import com.carrotinstitutefooddelivery.dto.MenuItemDto;
import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.model.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.RestaurantConverter.restaurantEntityToDto;

public class MenuItemConverter {
    public static MenuItemDto menuItemEntityToDto(MenuItem menuItem){
        return MenuItemDto.builder()
                .name(menuItem.getName())
                .menuId(menuItem.getMenuId())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .restaurantDto(restaurantEntityToDto(menuItem.getRestaurant()))
                .build();
    }

    public static List<MenuItemDto>menuItemsEntityToDtos(List<MenuItem> menuItems){
        return menuItems.stream()
                .map(MenuItemConverter::menuItemEntityToDto)
                .collect(Collectors.toList());
    }
}
