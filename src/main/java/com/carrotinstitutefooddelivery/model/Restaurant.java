package com.carrotinstitutefooddelivery.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false)
    private UUID restaurantId;

    private String name;
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem>menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Order>orders;

}
