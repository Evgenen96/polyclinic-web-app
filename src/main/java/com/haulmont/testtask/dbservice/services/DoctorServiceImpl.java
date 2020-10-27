package com.haulmont.testtask.dbservice.services;


import com.haulmont.testtask.dbservice.service.interfaces.DoctorDao;
import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.services.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao hibernateDoctorDao;

    @Override
    @Transactional
    public void save(Doctor doctor) {
        hibernateDoctorDao.save(doctor);
    }

    @Override
    @Transactional
    public Doctor getById(Long id) {
        return hibernateDoctorDao.getById(id);
    }

    @Override
    @Transactional
    public List<Doctor> getAll() {
        return hibernateDoctorDao.getAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        hibernateDoctorDao.remove(id);
    }
}
