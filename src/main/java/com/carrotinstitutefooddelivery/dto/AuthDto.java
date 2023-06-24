package com.carrotinstitutefooddelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto {
    private UUID userId;
    private String userName;
    private String email;
    private String token;
}
