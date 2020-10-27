package com.haulmont.testtask.dbservice.service;

import com.haulmont.testtask.dbservice.service.interfaces.PriorityDao;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriorityDaoImpl implements PriorityDao<RecipePriority> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<RecipePriority> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<RecipePriority> theQuery =
                currentSession.createQuery("from RecipePriority", RecipePriority.class);
        List<RecipePriority> recipePriorities = theQuery.getResultList();
        return recipePriorities;
    }
}
