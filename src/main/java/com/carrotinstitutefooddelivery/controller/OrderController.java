package com.carrotinstitutefooddelivery.controller;

import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.request.AdminOrderRequest;
import com.carrotinstitutefooddelivery.request.OrderRequest;
import com.carrotinstitutefooddelivery.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public OrderDto saveOrder(@Valid @RequestBody List<OrderRequest> orderRequests, @RequestHeader("token") String authorization){
        return orderService.saveOrder(orderRequests, authorization);
    }

    @GetMapping("/orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public List<OrderDto>findAllOrders(@RequestHeader("token") String authorization){
        return orderService.findAllOrders(authorization);
    }

    @PostMapping("orders/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public OrderDto findOrderById(@PathVariable UUID orderId, @RequestHeader("token") String authorization){
        return orderService.findOrderById(orderId);
    }

    @PutMapping("/admin/updateOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "User's token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    public OrderDto updateOrderStatus(@Valid @RequestBody AdminOrderRequest request, String authorization){
        return orderService.updateOrderStatus(request);
    }
}
