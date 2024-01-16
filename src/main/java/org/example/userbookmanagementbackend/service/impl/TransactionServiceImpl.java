package org.example.userbookmanagementbackend.service.impl;

import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.transformer.BookTransformer;
import org.example.userbookmanagementbackend.transformer.TransactionTransformer;
import org.example.userbookmanagementbackend.transformer.ClientTransformer;
import org.example.userbookmanagementbackend.bean.Book;
import org.example.userbookmanagementbackend.bean.Client;
import org.example.userbookmanagementbackend.bean.Transaction;
import org.example.userbookmanagementbackend.dao.TransactionDao;
import org.example.userbookmanagementbackend.dto.BookDto;
import org.example.userbookmanagementbackend.dto.TransactionDto;
import org.example.userbookmanagementbackend.dto.ClientDto;
import org.example.userbookmanagementbackend.exception.ResourceNotFoundException;
import org.example.userbookmanagementbackend.service.facade.BookService;
import org.example.userbookmanagementbackend.service.facade.TransactionService;
import org.example.userbookmanagementbackend.service.facade.ClientService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private TransactionDao transactionDao;
    private TransactionTransformer transactionTransformer;
    private BookService bookService;
    private BookTransformer bookTransformer;
    private ClientService clientService;
    private ClientTransformer clientTransformer;

    @Override
    public List<TransactionDto> findAll() {
        List<Transaction> categories = transactionDao.findAll();
        return transactionTransformer.toDto(categories);
    }

    @Override
    public TransactionDto findById(Long id) {
        Transaction foundedTransaction = transactionDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction", "id", id)
        );
        return transactionTransformer.toDto(foundedTransaction);
    }

    @Override
    public int deleteById(Long id) {
        findById(id);
        transactionDao.deleteById(id);
        return 1;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction = transactionTransformer.toEntity(transactionDto);
        prepareSave(transaction);
        Transaction savedTransaction = transactionDao.save(transaction);
        return transactionTransformer.toDto(savedTransaction);
    }

    private void prepareSave(Transaction transaction) {
        findClient(transaction);
        findBook(transaction);
    }

    private void findBook(Transaction transaction) {
        BookDto foundedBookDto = bookService.findById(transaction.getBook().getId());
        if (foundedBookDto != null) {
            Book book = bookTransformer.toEntity(foundedBookDto);
            transaction.setBook(book);
        }
    }

    private void findClient(Transaction transaction) {
        ClientDto foundedClientDto = clientService.findById(transaction.getClient().getId());
        if (foundedClientDto != null) {
            Client client = clientTransformer.toEntity(foundedClientDto);
            transaction.setClient(client);
        }
    }

    @Override
    public List<TransactionDto> save(List<TransactionDto> transactionDtos) {
        if (transactionDtos != null && !transactionDtos.isEmpty()) {
            return transactionDtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        findById(transactionDto.id());
        Transaction transaction = transactionTransformer.toEntity(transactionDto);
        Transaction updatedTransaction = transactionDao.save(transaction);
        return transactionTransformer.toDto(updatedTransaction);
    }

    @Override
    public int delete(TransactionDto transactionDto) {
        TransactionDto foundedTransactionDto = findById(transactionDto.id());
        Transaction transaction = transactionTransformer.toEntity(foundedTransactionDto);
        transactionDao.delete(transaction);
        return 1;
    }

    @Override
    public void delete(List<TransactionDto> transactionDtos) {
        if (transactionDtos != null && !transactionDtos.isEmpty()) {
            transactionDtos.forEach(this::delete);
        }
    }

    @Override
    public void update(List<TransactionDto> transactionDtos) {
        if (transactionDtos != null && !transactionDtos.isEmpty()) {
            transactionDtos.forEach(this::update);
        }
    }
}
