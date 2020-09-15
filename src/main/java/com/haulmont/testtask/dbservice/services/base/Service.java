package com.haulmont.testtask.dbservice.services.base;

import java.util.List;

public interface Service<T> {
    void save(T object);

    T getById(Long id);

    List<T> getAll();

    void update(T object);

    void remove(T object);
}
