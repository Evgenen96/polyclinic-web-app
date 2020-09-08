package com.haulmont.testtask.dbService.dao.base;

import java.util.List;

public interface Dao<T> {

    void save(T object);

    T getById(Long id);

    List<T> getAll();

    void update(T object);

    void remove(T object);
}
