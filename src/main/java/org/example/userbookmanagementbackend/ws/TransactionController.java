package org.example.userbookmanagementbackend.ws;

import org.example.userbookmanagementbackend.dto.TransactionDto;
import org.example.userbookmanagementbackend.service.facade.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.deleteById(id));
    }

    @PostMapping("/")
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.save(transactionDto), HttpStatus.CREATED);
    }

    @PostMapping("/list/")
    public ResponseEntity<List<TransactionDto>> save(@RequestBody List<TransactionDto> transactionDtos) {
        return new ResponseEntity<>(transactionService.save(transactionDtos), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.update(transactionDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<Integer> delete(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.delete(transactionDto));
    }

    @DeleteMapping("/list/")
    public void delete(@RequestBody List<TransactionDto> transactionDtos) {
        transactionService.delete(transactionDtos);
    }

    @PutMapping("/list/")
    public void update(@RequestBody List<TransactionDto> list) {
        transactionService.update(list);
    }
}
