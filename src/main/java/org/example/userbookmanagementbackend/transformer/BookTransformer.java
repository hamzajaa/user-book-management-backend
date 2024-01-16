package org.example.userbookmanagementbackend.transformer;

import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.bean.Book;
import org.example.userbookmanagementbackend.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookTransformer extends AbstractTransformer<Book, BookDto> {

    private AuthorTransformer authorTransformer;
    private CategoryTransformer categoryTransformer;

    @Override
    public Book toEntity(BookDto dto) {
        if (dto == null) {
            return null;
        } else {
            Book entity = new Book();
            entity.setId(dto.id());
            entity.setTitle(dto.title());
            entity.setIsbn(dto.isbn());
            entity.setDescription(dto.description());
            entity.setNumberOfPages(dto.numberOfPages());
            entity.setOnLoan(dto.onLoan());
            entity.setAuthor(authorTransformer.toEntity(dto.authorDto()));
            entity.setCategory(categoryTransformer.toEntity(dto.categoryDto()));
            return entity;
        }
    }

    @Override
    public BookDto toDto(Book entity) {
        if (entity == null) {
            return null;
        } else {
            return new BookDto(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getIsbn(),
                    entity.getDescription(),
                    entity.getNumberOfPages(),
                    entity.isOnLoan(),
                    authorTransformer.toDto(entity.getAuthor()),
                    categoryTransformer.toDto(entity.getCategory())
            );
        }
    }
}
