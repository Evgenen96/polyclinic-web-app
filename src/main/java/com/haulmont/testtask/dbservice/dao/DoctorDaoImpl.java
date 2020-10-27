package com.haulmont.testtask.dbservice.dao;

import com.haulmont.testtask.dbservice.dao.interfaces.DoctorDao;
import com.haulmont.testtask.dbservice.entities.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDaoImpl implements DoctorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public DoctorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Doctor theDoctor) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theDoctor);
    }

    @Override
    public Doctor getById(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Doctor theDoctor = currentSession.get(Doctor.class, theId);
        return theDoctor;
    }

    @Override
    public List<Doctor> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Doctor> theQuery =
                currentSession.createQuery("from Doctor order by lastName", Doctor.class);
        List<Doctor> doctors = theQuery.getResultList();
        return doctors;
    }

    @Override
    public void remove(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Doctor where doctorId=:doctorId");
        theQuery.setParameter("doctorId", theId);
        theQuery.executeUpdate();
    }
}
