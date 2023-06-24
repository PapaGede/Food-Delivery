package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.MenuItemDto;
import com.carrotinstitutefooddelivery.request.MenuItemRequest;
import com.carrotinstitutefooddelivery.service.MenuItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @PostMapping("/admin/menuItem")
    public MenuItemDto saveMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.saveMenuItem(menuItemRequest);
    }

    @GetMapping("/menuItem")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", required = true, dataTypeClass = String.class, paramType = "header"),
            @ApiImplicitParam(name = "token", value = "User's email", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public List<MenuItemDto> findAllMenuItems(@RequestHeader("token") String authorization){

        System.out.println(authorization);
        return menuItemService.findAllMenuItem();
    }

    @GetMapping("/menuItem/{menuItemId}")
    public MenuItemDto findMenuItemById(@PathVariable UUID menuItemId){
        return menuItemService.findMenuItemById(menuItemId);
    }

    @PutMapping("/admin/menuItem/{menuItemId}")
    public MenuItemDto updateMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest, @PathVariable UUID menuItemId){
        return menuItemService.updateMenuItem(menuItemRequest, menuItemId);
    }

    @DeleteMapping("/admin/menuItem/{menuItemId}")
    public ResponseEntity<String>deleteMenuItem(@PathVariable UUID menuItemId){
        menuItemService.deleteMenuItemById(menuItemId);
        return ResponseEntity.ok("Menu Item with ID "+ menuItemId + " has been deleted successfully");
    }
}
