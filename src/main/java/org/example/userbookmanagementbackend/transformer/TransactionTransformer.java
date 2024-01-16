package org.example.userbookmanagementbackend.transformer;

import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.bean.Transaction;
import org.example.userbookmanagementbackend.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionTransformer extends AbstractTransformer<Transaction, TransactionDto> {

    private BookTransformer bookTransformer;
    private ClientTransformer clientTransformer;

    @Override
    public Transaction toEntity(TransactionDto dto) {
        if (dto == null) {
            return null;
        } else {
            Transaction entity = new Transaction();
            entity.setId(dto.id());
            entity.setBook(bookTransformer.toEntity(dto.bookDto()));
            entity.setClient(clientTransformer.toEntity(dto.clientDto()));
            entity.setCheckoutDate(dto.checkoutDate());
            entity.setReturnDate(dto.returnDate());
            return entity;
        }
    }

    @Override
    public TransactionDto toDto(Transaction entity) {
        if (entity == null) {
            return null;
        } else {
            return new TransactionDto(
                    entity.getId(),
                    bookTransformer.toDto(entity.getBook()),
                    clientTransformer.toDto(entity.getClient()),
                    entity.getCheckoutDate(),
                    entity.getReturnDate()
            );
        }
    }
}
