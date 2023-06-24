package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.model.Order;
import com.carrotinstitutefooddelivery.request.AdminOrderRequest;
import com.carrotinstitutefooddelivery.request.OrderRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto saveOrder(List<OrderRequest> orderRequest, String token);
    List<OrderDto>findAllOrders(String token);

    OrderDto findOrderById(UUID orderId);

    OrderDto updateOrderStatus(AdminOrderRequest request);
}
