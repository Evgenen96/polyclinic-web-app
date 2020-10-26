package com.haulmont.testtask.dbservice.services.interfaces;

import java.util.List;

public interface PriorityService<T> {
    List<T> getAll();
}
