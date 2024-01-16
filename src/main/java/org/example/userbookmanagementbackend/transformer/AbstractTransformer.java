package org.example.userbookmanagementbackend.transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTransformer<E, D> {

    public abstract E toEntity(D dto);

    public abstract D toDto(E entity);

    public List<E> toEntity(List<D> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<E> entities = new ArrayList<>();
            for (D dto : dtos) {
                entities.add(toEntity(dto));
            }
            return entities;
        }
    }

    public List<D> toDto(List<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<D> dtos = new ArrayList<>();
            for (E entity : entities) {
                dtos.add(toDto(entity));
            }
            return dtos;
        }
    }

}
