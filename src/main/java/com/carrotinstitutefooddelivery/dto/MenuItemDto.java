package com.carrotinstitutefooddelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemDto {
    private UUID menuId;
    private String name;
    private String description;
    private Double price;
    private RestaurantDto restaurantDto;
}
