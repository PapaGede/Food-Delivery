package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.request.RestaurantRequest;
import com.carrotinstitutefooddelivery.service.RestaurantService;
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
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/admin/restaurants")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public RestaurantDto saveRestaurant(@Valid @RequestBody RestaurantRequest request, @RequestHeader("token") String authorization){
        return restaurantService.saveRestaurant(request);
    }

    @GetMapping("/restaurants")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public List<RestaurantDto> findAllRestaurants(@RequestHeader("token") String authorization){
        return restaurantService.findAllRestaurants();
    }

    @GetMapping("/restaurants/{restaurantId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public RestaurantDto findRestaurantById(@PathVariable UUID restaurantId, @RequestHeader("token") String authorization){
        return restaurantService.findRestaurantById(restaurantId);
    }

    @PutMapping("/admin/restaurants/{restaurantId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public RestaurantDto updateRestaurant(@Valid @RequestBody RestaurantRequest request, @PathVariable UUID restaurantId, @RequestHeader("token") String authorization){
        return restaurantService.updateRestaurant(request, restaurantId);
    }

    @DeleteMapping("/admin/restaurants/{restaurantId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public ResponseEntity<String> deleteRestaurantById(@PathVariable UUID restaurantId, @RequestHeader("token") String authorization){
        restaurantService.deleteRestaurantById(restaurantId);
        return ResponseEntity.ok("Restaurant with ID "+ restaurantId + " has been deleted successfully");
    }
}
