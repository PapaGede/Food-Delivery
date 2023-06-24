package com.carrotinstitutefooddelivery.converter;

import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.dto.OrderItemDto;
import com.carrotinstitutefooddelivery.dto.UserDto;
import com.carrotinstitutefooddelivery.model.Order;
import com.carrotinstitutefooddelivery.model.OrderItem;
import com.carrotinstitutefooddelivery.model.User;

import java.util.List;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.MenuItemConverter.menuItemEntityToDto;
import static com.carrotinstitutefooddelivery.converter.MenuItemConverter.menuItemsEntityToDtos;

public class OrderConverter {
    public static OrderDto orderEntityToDto(Order order){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderCreateTime(order.getOrderDateTime())
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItems().stream().map(OrderConverter::orderItemEntityToDto).collect(Collectors.toList()))
                .userDto(userEntityToDto(order.getUser()))
                .orderStatus(order.getOrderStatus().toString())
                .build();
    }

    private static OrderItemDto orderItemEntityToDto(OrderItem orderItem){
        return OrderItemDto.builder()
                .orderItemId(orderItem.getOrderItemId())
                .quantity(orderItem.getQuantity())
                .menuItemDto(menuItemEntityToDto(orderItem.getMenuItem()))
                .build();
    }

    private static UserDto userEntityToDto(User user){
        if (user == null){
            return null;
        }
        return UserDto.builder()
                .userName(user.getUserName())
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
    }
}
