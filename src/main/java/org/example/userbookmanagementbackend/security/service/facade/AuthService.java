package org.example.userbookmanagementbackend.security.service.facade;


import org.example.userbookmanagementbackend.security.dto.LoginDto;
import org.example.userbookmanagementbackend.security.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
