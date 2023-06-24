package com.carrotinstitutefooddelivery.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String contact;
}
