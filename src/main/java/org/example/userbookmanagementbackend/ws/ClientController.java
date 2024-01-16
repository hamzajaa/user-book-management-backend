package org.example.userbookmanagementbackend.ws;

import org.example.userbookmanagementbackend.dto.ClientDto;
import org.example.userbookmanagementbackend.service.facade.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.deleteById(id));
    }

    @PostMapping("/")
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.save(clientDto), HttpStatus.CREATED);
    }

    @PostMapping("/list/")
    public ResponseEntity<List<ClientDto>> save(@RequestBody List<ClientDto> clientDtos) {
        return new ResponseEntity<>(clientService.save(clientDtos), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.update(clientDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<Integer> delete(@RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.delete(clientDto));
    }

    @DeleteMapping("/list/")
    public void delete(@RequestBody List<ClientDto> clientDtos) {
        clientService.delete(clientDtos);
    }

    @PutMapping("/list/")
    public void update(@RequestBody List<ClientDto> list) {
        clientService.update(list);
    }
}
