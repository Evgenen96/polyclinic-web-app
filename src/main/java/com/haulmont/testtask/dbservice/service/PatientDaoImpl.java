package com.haulmont.testtask.dbservice.service;

import com.haulmont.testtask.dbservice.service.interfaces.PatientDao;
import com.haulmont.testtask.dbservice.entities.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {

    @Autowired
    private SessionFactory sessionFactory;

    public PatientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Patient thePatient) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(thePatient);
    }

    @Override
    public Patient getById(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Patient thePatient = currentSession.get(Patient.class, theId);
        return thePatient;
    }

    @Override
    public List<Patient> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Patient> theQuery =
                currentSession.createQuery("from Patient order by lastName", Patient.class);
        List<Patient> patients = theQuery.getResultList();
        return patients;
    }

    @Override
    public void remove(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Patient where patientId=:patientId");
        theQuery.setParameter("patientId", theId);
        theQuery.executeUpdate();
    }
}
