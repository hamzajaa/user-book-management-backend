package org.example.userbookmanagementbackend.service.impl;

import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.transformer.ClientTransformer;
import org.example.userbookmanagementbackend.bean.Client;
import org.example.userbookmanagementbackend.dao.ClientDao;
import org.example.userbookmanagementbackend.dto.ClientDto;
import org.example.userbookmanagementbackend.exception.ResourceNotFoundException;
import org.example.userbookmanagementbackend.service.facade.ClientService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private ClientTransformer clientTransformer;

    @Override
    public List<ClientDto> findAll() {
        List<Client> categories = clientDao.findAll();
        return clientTransformer.toDto(categories);
    }

    @Override
    public ClientDto findById(Long id) {
        Client foundedClient = clientDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client", "id", id)
        );
        return clientTransformer.toDto(foundedClient);
    }

    @Override
    public int deleteById(Long id) {
        findById(id);
        clientDao.deleteById(id);
        return 1;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = clientTransformer.toEntity(clientDto);
        Client savedClient = clientDao.save(client);
        return clientTransformer.toDto(savedClient);
    }

    @Override
    public List<ClientDto> save(List<ClientDto> clientDtos) {
        if (clientDtos != null && !clientDtos.isEmpty()) {
            return clientDtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        findById(clientDto.id());
        Client client = clientTransformer.toEntity(clientDto);
        Client updatedClient = clientDao.save(client);
        return clientTransformer.toDto(updatedClient);
    }

    @Override
    public int delete(ClientDto clientDto) {
        ClientDto foundedClientDto = findById(clientDto.id());
        Client client = clientTransformer.toEntity(foundedClientDto);
        clientDao.delete(client);
        return 1;
    }

    @Override
    public void delete(List<ClientDto> clientDtos) {
        if (clientDtos != null && !clientDtos.isEmpty()) {
            clientDtos.forEach(this::delete);
        }
    }

    @Override
    public void update(List<ClientDto> clientDtos) {
        if (clientDtos != null && !clientDtos.isEmpty()) {
            clientDtos.forEach(this::update);
        }
    }
}
