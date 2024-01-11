package org.example.userbookmanagementbackend.dto;

public record ClientDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
