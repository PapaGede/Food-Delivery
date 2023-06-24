package com.carrotinstitutefooddelivery.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false)
    private UUID orderItemId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menuItemId")
    private MenuItem menuItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    private Integer quantity;
}
