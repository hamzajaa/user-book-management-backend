package org.example.userbookmanagementbackend.Transformer;

import org.example.userbookmanagementbackend.bean.Client;
import org.example.userbookmanagementbackend.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer extends AbstractTransformer<Client, ClientDto> {
    @Override
    public Client toEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        } else {
            Client entity = new Client();
            entity.setId(dto.id());
            entity.setFirstName(dto.firstName());
            entity.setLastName(dto.lastName());
            entity.setEmail(dto.email());
            entity.setPhoneNumber(dto.phoneNumber());
            return entity;
        }
    }

    @Override
    public ClientDto toDto(Client entity) {
        if (entity == null) {
            return null;
        } else {
            return new ClientDto(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    entity.getPhoneNumber()
            );
        }
    }
}
