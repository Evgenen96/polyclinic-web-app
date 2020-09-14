package com.haulmont.testtask.ui.views.patientViews;


import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.ui.utils.Operations;
import com.vaadin.ui.Window;

/**
 * Window for PatientForm class
 */
public class PatientFormWindow extends Window {
    private PatientForm patientForm;

    public PatientFormWindow(Operations operation, Patient patient) {
        this.patientForm = new PatientForm(operation, patient);
        setWidth("400px");
        setHeight("300px");
        setModal(true);
        setContent(patientForm);
    }
}
