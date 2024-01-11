package org.example.userbookmanagementbackend.dto;

public record AuthorDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
