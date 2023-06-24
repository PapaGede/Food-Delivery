package com.carrotinstitutefooddelivery.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminOrderRequest {
    private UUID orderId;
    private String orderStatus;
}
