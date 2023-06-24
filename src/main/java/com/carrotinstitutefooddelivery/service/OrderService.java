package com.carrotinstitutefooddelivery.service;

import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.model.Order;
import com.carrotinstitutefooddelivery.request.OrderRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto saveOrder(List<OrderRequest> orderRequest);
    List<OrderDto>findAllOrders();

    OrderDto findOrderById(UUID orderId);
}
