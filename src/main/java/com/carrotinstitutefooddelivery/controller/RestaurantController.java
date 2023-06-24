package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.RestaurantDto;
import com.carrotinstitutefooddelivery.request.RestaurantRequest;
import com.carrotinstitutefooddelivery.service.RestaurantService;
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
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public RestaurantDto saveRestaurant(@Valid @RequestBody RestaurantRequest request){
        return restaurantService.saveRestaurant(request);
    }

    @GetMapping
    public List<RestaurantDto> findAllRestaurants(){
        return restaurantService.findAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDto findRestaurantById(@PathVariable UUID restaurantId){
        return restaurantService.findRestaurantById(restaurantId);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantDto updateRestaurant(@Valid @RequestBody RestaurantRequest request, @PathVariable UUID restaurantId){
        return restaurantService.updateRestaurant(request, restaurantId);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable UUID restaurantId){
        restaurantService.deleteRestaurantById(restaurantId);
        return ResponseEntity.ok("Restaurant with ID "+ restaurantId + " has been deleted successfully");
    }
}
