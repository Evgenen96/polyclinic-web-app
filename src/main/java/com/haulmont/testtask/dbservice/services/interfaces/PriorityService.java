package com.haulmont.testtask.dbservice.services.interfaces;

import com.haulmont.testtask.dbservice.entities.RecipePriority;

import java.util.List;

public interface PriorityService {
    List<RecipePriority> getAll();
}
