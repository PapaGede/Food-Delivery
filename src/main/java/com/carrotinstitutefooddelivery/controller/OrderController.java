package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.request.OrderRequest;
import com.carrotinstitutefooddelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderDto saveOrder(@Valid @RequestBody List<OrderRequest> orderRequests){
        return orderService.saveOrder(orderRequests);
    }

    @GetMapping("/orders")
    public List<OrderDto>findAllOrders(){
        return orderService.findAllOrders();
    }

    @PostMapping("orders/{orderId}")
    public OrderDto findOrderById(@PathVariable UUID orderId){
        return orderService.findOrderById(orderId);
    }
}
