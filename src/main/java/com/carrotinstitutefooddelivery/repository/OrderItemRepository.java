package com.carrotinstitutefooddelivery.repository;

import com.carrotinstitutefooddelivery.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
