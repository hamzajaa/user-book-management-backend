package org.example.userbookmanagementbackend.security.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String numTel;
}
