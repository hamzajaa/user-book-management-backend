package org.example.userbookmanagementbackend.service.impl;

import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.Transformer.AuthorTransformer;
import org.example.userbookmanagementbackend.Transformer.BookTransformer;
import org.example.userbookmanagementbackend.Transformer.CategoryTransformer;
import org.example.userbookmanagementbackend.bean.Author;
import org.example.userbookmanagementbackend.bean.Book;
import org.example.userbookmanagementbackend.bean.Category;
import org.example.userbookmanagementbackend.dao.BookDao;
import org.example.userbookmanagementbackend.dto.AuthorDto;
import org.example.userbookmanagementbackend.dto.BookDto;
import org.example.userbookmanagementbackend.dto.CategoryDto;
import org.example.userbookmanagementbackend.exception.ResourceNotFoundException;
import org.example.userbookmanagementbackend.service.facade.AuthorService;
import org.example.userbookmanagementbackend.service.facade.BookService;
import org.example.userbookmanagementbackend.service.facade.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {


    private BookDao bookDao;
    private BookTransformer bookTransformer;
    private AuthorService authorService;
    private CategoryService categoryService;
    private AuthorTransformer authorTransformer;
    private CategoryTransformer categoryTransformer;

    @Override
    public List<BookDto> findAll() {
        List<Book> categories = bookDao.findAll();
        return bookTransformer.toDto(categories);
    }

    @Override
    public BookDto findById(Long id) {
        Book foundedBook = bookDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book", "id", id)
        );
        return bookTransformer.toDto(foundedBook);
    }

    @Override
    public int deleteById(Long id) {
        findById(id);
        bookDao.deleteById(id);
        return 1;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = bookTransformer.toEntity(bookDto);
        prepareSave(book);
        Book savedBook = bookDao.save(book);
        return bookTransformer.toDto(savedBook);
    }

    private void prepareSave(Book book) {
        findAuthor(book);
        findCategory(book);
    }

    private void findCategory(Book book) {
        CategoryDto foundedCategoryDto = categoryService.findById(book.getCategory().getId());
        if (foundedCategoryDto != null) {
            Category category = categoryTransformer.toEntity(foundedCategoryDto);
            book.setCategory(category);
        }
    }

    private void findAuthor(Book book) {
        AuthorDto foundedAuthorDto = authorService.findById(book.getAuthor().getId());
        if (foundedAuthorDto != null) {
            Author author = authorTransformer.toEntity(foundedAuthorDto);
            book.setAuthor(author);
        }
    }

    @Override
    public List<BookDto> save(List<BookDto> bookDtos) {
        if (bookDtos != null && !bookDtos.isEmpty()) {
            return bookDtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public BookDto update(BookDto bookDto) {
        findById(bookDto.id());
        Book book = bookTransformer.toEntity(bookDto);
        Book updatedBook = bookDao.save(book);
        return bookTransformer.toDto(updatedBook);
    }

    @Override
    public int delete(BookDto bookDto) {
        BookDto foundedBookDto = findById(bookDto.id());
        Book book = bookTransformer.toEntity(foundedBookDto);
        bookDao.delete(book);
        return 1;
    }

    @Override
    public void delete(List<BookDto> bookDtos) {
        if (bookDtos != null && !bookDtos.isEmpty()) {
            bookDtos.forEach(this::delete);
        }
    }

    @Override
    public void update(List<BookDto> bookDtos) {
        if (bookDtos != null && !bookDtos.isEmpty()) {
            bookDtos.forEach(this::update);
        }
    }
}
