package com.carrotinstitutefooddelivery.dto;

import com.carrotinstitutefooddelivery.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private UUID orderId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderCreateTime;
    private Double totalPrice;
    private List<OrderItemDto>orderItems;
    private UserDto userDto;
    private String orderStatus;
}
