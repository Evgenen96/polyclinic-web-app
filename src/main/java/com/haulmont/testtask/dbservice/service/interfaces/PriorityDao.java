package com.haulmont.testtask.dbservice.service.interfaces;

import java.util.List;

public interface PriorityDao<T> {
    List<T> getAll();
}
