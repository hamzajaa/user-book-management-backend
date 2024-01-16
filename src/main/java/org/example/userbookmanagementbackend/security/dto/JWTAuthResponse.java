package org.example.userbookmanagementbackend.security.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType="Bearer";
}
