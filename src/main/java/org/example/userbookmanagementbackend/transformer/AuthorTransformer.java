package org.example.userbookmanagementbackend.transformer;

import org.example.userbookmanagementbackend.bean.Author;
import org.example.userbookmanagementbackend.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorTransformer extends AbstractTransformer<Author, AuthorDto> {
    @Override
    public Author toEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        } else {
            Author entity = new Author();
            entity.setId(dto.id());
            entity.setFirstName(dto.firstName());
            entity.setLastName(dto.lastName());
            entity.setEmail(dto.email());
            entity.setPhoneNumber(dto.phoneNumber());
            return entity;
        }
    }

    @Override
    public AuthorDto toDto(Author entity) {
        if (entity == null) {
            return null;
        } else {
            return new AuthorDto(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    entity.getPhoneNumber()
            );
        }
    }
}

