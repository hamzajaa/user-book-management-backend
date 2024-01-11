package org.example.userbookmanagementbackend.ws;

import org.example.userbookmanagementbackend.dto.AuthorDto;
import org.example.userbookmanagementbackend.service.facade.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.deleteById(id));
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.save(authorDto), HttpStatus.CREATED);
    }

    @PostMapping("/list/")
    public ResponseEntity<List<AuthorDto>> save(@RequestBody List<AuthorDto> authorDtos) {
        return new ResponseEntity<>(authorService.save(authorDtos), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.update(authorDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<Integer> delete(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.delete(authorDto));
    }

    @DeleteMapping("/list/")
    public void delete(@RequestBody List<AuthorDto> authorDtos) {
        authorService.delete(authorDtos);
    }

    @PutMapping("/list/")
    public void update(@RequestBody List<AuthorDto> list) {
        authorService.update(list);
    }
}
