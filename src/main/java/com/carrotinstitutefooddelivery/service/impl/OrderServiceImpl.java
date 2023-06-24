package com.carrotinstitutefooddelivery.service.impl;

import com.carrotinstitutefooddelivery.constant.OrderStatus;
import com.carrotinstitutefooddelivery.constant.UserType;
import com.carrotinstitutefooddelivery.converter.OrderConverter;
import com.carrotinstitutefooddelivery.dto.OrderDto;
import com.carrotinstitutefooddelivery.exceptions.ResourceNotFoundException;
import com.carrotinstitutefooddelivery.model.MenuItem;
import com.carrotinstitutefooddelivery.model.Order;
import com.carrotinstitutefooddelivery.model.OrderItem;
import com.carrotinstitutefooddelivery.repository.MenuItemsRepository;
import com.carrotinstitutefooddelivery.repository.OrderItemRepository;
import com.carrotinstitutefooddelivery.repository.OrderRepository;
import com.carrotinstitutefooddelivery.repository.UserRepository;
import com.carrotinstitutefooddelivery.request.AdminOrderRequest;
import com.carrotinstitutefooddelivery.request.OrderRequest;
import com.carrotinstitutefooddelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.carrotinstitutefooddelivery.converter.OrderConverter.orderEntityToDto;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemsRepository menuItemsRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto saveOrder(List<OrderRequest> orderRequest, String token) {
        var menuItems = menuItemsRepository.findMenuItemByMenuIdIn(orderRequest.stream().map(OrderRequest::getMenuItemId).collect(Collectors.toList()));
        var map = menuItems.stream().collect(Collectors.toMap(MenuItem::getMenuId, value -> value));

        var user = userRepository.findUserByToken(token).get();

        var totalPrice = orderRequest.stream()
                .map(item -> map.get(item.getMenuItemId()).getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);

        var order = Order.builder()
                .totalPrice(totalPrice)
                .orderDateTime(LocalDateTime.now())
                .user(user)
                .orderStatus(OrderStatus.PENDING)
                .build();
        var savedOrder = orderRepository.save(order);

        var orderItem = orderRequest.stream()
                .map(item -> OrderItem.builder().quantity(item.getQuantity())
                        .menuItem(map.get(item.getMenuItemId())).order(order).build())
                .collect(Collectors.toList());

        savedOrder.setOrderItems(orderItemRepository.saveAll(orderItem));
        return orderEntityToDto(savedOrder);
    }

    @Override
    public List<OrderDto> findAllOrders(String token) {
        var user = userRepository.findUserByToken(token).get();
        if (user.getUserType().equals(UserType.ADMIN)) {
            return orderRepository.findAll().stream().map(OrderConverter::orderEntityToDto).collect(Collectors.toList());
        }
        return orderRepository.findAll().stream().map(OrderConverter::orderEntityToDto)
                .filter(item-> item.getUserDto().getUserName().equals(user.getUserName()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(UUID orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Cannot find restaurant with id: " + orderId));
        return orderEntityToDto(order);
    }

    @Override
    public OrderDto updateOrderStatus(AdminOrderRequest request) {
        var order = orderRepository.findById(request.getOrderId()).orElseThrow(()->new ResourceNotFoundException("Cannot find restaurant with id: " + request.getOrderId()));
        order.setOrderStatus(OrderStatus.valueOf(request.getOrderStatus()));
        return orderEntityToDto(orderRepository.save(order));
    }
}
