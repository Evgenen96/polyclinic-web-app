package com.haulmont.testtask.dbservice.services;


import com.haulmont.testtask.dbservice.dao.DoctorDaoImpl;
import com.haulmont.testtask.dbservice.dao.base.DoctorDao;
import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.services.base.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private static DoctorDao jdbcDoctorDao = new DoctorDaoImpl();

    @Override
    public void save(Doctor doctor) {
        jdbcDoctorDao.save(doctor);
    }

    @Override
    public Doctor getById(Long id) {
        return jdbcDoctorDao.getById(id);
    }

    @Override
    public List<Doctor> getAll() {
        return jdbcDoctorDao.getAll();
    }

    @Override
    public void update(Doctor doctor) {
        jdbcDoctorDao.update(doctor);
    }

    @Override
    public void remove(Doctor doctor) {
        jdbcDoctorDao.remove(doctor);
    }
}
