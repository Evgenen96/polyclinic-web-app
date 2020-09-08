package com.haulmont.testtask.dbService.services;

import com.haulmont.testtask.dbService.dao.PatientDaoImpl;
import com.haulmont.testtask.dbService.dao.base.PatientDao;
import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.dbService.services.base.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private static PatientDao jdbcPatientDao = new PatientDaoImpl();

    @Override
    public void save(Patient patient) {
        jdbcPatientDao.save(patient);
    }

    @Override
    public Patient getById(Long id) {
        return jdbcPatientDao.getById(id);
    }

    @Override
    public List<Patient> getAll() {
        return jdbcPatientDao.getAll();
    }

    @Override
    public void update(Patient patient) {
        jdbcPatientDao.update(patient);
    }

    @Override
    public void remove(Patient patient) {
        jdbcPatientDao.remove(patient);
    }
}
