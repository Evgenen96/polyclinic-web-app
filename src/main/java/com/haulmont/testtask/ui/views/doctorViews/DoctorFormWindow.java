package com.haulmont.testtask.ui.views.doctorViews;


import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.ui.utils.Operations;
import com.vaadin.ui.Window;

public class DoctorFormWindow extends Window {
    private DoctorForm doctorForm;

    public DoctorFormWindow(Operations operation, Doctor doctor) {
        this.doctorForm = new DoctorForm(operation, doctor);
        setWidth("400px");
        setHeight("300px");
        setModal(true);
        setContent(doctorForm);
    }
}
