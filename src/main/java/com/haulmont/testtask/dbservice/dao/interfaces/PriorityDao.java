package com.haulmont.testtask.dbservice.dao.interfaces;

import java.util.List;

public interface PriorityDao<T> {
    List<T> getAll();
}
