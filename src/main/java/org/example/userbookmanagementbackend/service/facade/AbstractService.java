package org.example.userbookmanagementbackend.service.facade;

import java.util.List;


public interface AbstractService<D, I> {
    List<D> findAll();

    D findById(I id);

    int deleteById(I id);

    D save(D entity);

    List<D> save(List<D> list);

    D update(D D);

    int delete(D D);

    void delete(List<D> list);

    void update(List<D> list);

}
