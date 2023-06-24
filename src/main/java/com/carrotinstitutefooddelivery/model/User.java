package com.carrotinstitutefooddelivery.model;

import com.carrotinstitutefooddelivery.constant.UserType;
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
@Table(name = "users")
public class User {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false)
    private UUID userId;

    private String userName;
    private String email;
    private String password;

    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Order>orders;

    private String token;
}
