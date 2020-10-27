package com.haulmont.testtask.dbservice.services;

import com.haulmont.testtask.dbservice.dao.interfaces.PriorityDao;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import com.haulmont.testtask.dbservice.services.interfaces.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityDao priorityDao;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<RecipePriority> getAll() {
        return priorityDao.getAll();
    }
}
