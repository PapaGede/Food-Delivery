package com.carrotinstitutefooddelivery.model;

import com.carrotinstitutefooddelivery.constant.OrderStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false)
    private UUID orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem>orderItems;

    private LocalDateTime orderDateTime;
    private Double totalPrice;
    private OrderStatus orderStatus;
}
