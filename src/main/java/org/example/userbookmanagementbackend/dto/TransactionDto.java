package org.example.userbookmanagementbackend.dto;

import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        BookDto bookDto,
        ClientDto clientDto,
        LocalDateTime checkoutDate,
        LocalDateTime returnDate
) {
}
