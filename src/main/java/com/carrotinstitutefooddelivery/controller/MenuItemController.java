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
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public List<MenuItemDto> findAllMenuItems(@RequestHeader("token") String authorization){

        System.out.println(authorization);
        return menuItemService.findAllMenuItem();
    }

    @GetMapping("/menuItem/{menuItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public MenuItemDto findMenuItemById(@PathVariable UUID menuItemId, @RequestHeader("token") String authorization){
        return menuItemService.findMenuItemById(menuItemId);
    }

    @PutMapping("/admin/menuItem/{menuItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public MenuItemDto updateMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest, @PathVariable UUID menuItemId, @RequestHeader("token") String authorization){
        return menuItemService.updateMenuItem(menuItemRequest, menuItemId);
    }

    @DeleteMapping("/admin/menuItem/{menuItemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public ResponseEntity<String>deleteMenuItem(@PathVariable UUID menuItemId, @RequestHeader("token") String authorization){
        menuItemService.deleteMenuItemById(menuItemId);
        return ResponseEntity.ok("Menu Item with ID "+ menuItemId + " has been deleted successfully");
    }
}
