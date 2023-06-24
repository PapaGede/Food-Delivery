package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.MenuItemDto;
import com.carrotinstitutefooddelivery.request.MenuItemRequest;

import java.util.List;
import java.util.UUID;

public interface MenuItemService {
    MenuItemDto saveMenuItem(MenuItemRequest menuItemRequest);
    List<MenuItemDto>findAllMenuItem();
    MenuItemDto findMenuItemById(UUID menuItemId);
    MenuItemDto updateMenuItem(MenuItemRequest menuItemRequest, UUID menuItemId);
    void deleteMenuItemById(UUID menuItemId);
}
