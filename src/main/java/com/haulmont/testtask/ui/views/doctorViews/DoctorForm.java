package com.haulmont.testtask.ui.views.doctorViews;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.ui.utils.Operations;
import com.haulmont.testtask.ui.views.AbstractUtilForm;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;


public class DoctorForm extends AbstractUtilForm<Doctor> implements View {
    private TextField firstNameText;
    private TextField surnameText;
    private TextField patronymicText;
    private TextField specializationText;

    private Binder<Doctor> binder = new Binder<>(Doctor.class);

    public DoctorForm(Operations operation, Doctor doctor) {

        super(operation, doctor);

        setMargin(true);
        setSpacing(true);

        surnameText = createTextField("Фамилия:", 255);
        firstNameText = createTextField("Имя:", 255);
        patronymicText = createTextField("Отчество:", 255);
        specializationText = createTextField("Специализация:", 255);

        binder.forField(firstNameText).bind(Doctor::getFirstName, Doctor::setFirstName);
        binder.forField(surnameText).bind(Doctor::getSurname, Doctor::setSurname);
        binder.forField(patronymicText).bind(Doctor::getPatronymic, Doctor::setPatronymic);
        binder.forField(specializationText).bind(Doctor::getSpecialization, Doctor::setSpecialization);

        addComponentAsFirst(specializationText);
        addComponentAsFirst(patronymicText);
        addComponentAsFirst(firstNameText);
        addComponentAsFirst(surnameText);

        setupBtListeners();

        if (operation == Operations.edit) {
            System.out.println(domainObject);
            binder.readBean(domainObject);
        }
    }

    @Override
    protected void setupBinders() {
        binder.forField(firstNameText)
                .withValidator(string -> string != null && !string.isEmpty(), "Введите имя")
                .asRequired()
                .bind(Doctor::getFirstName, Doctor::setFirstName);
        binder.forField(surnameText)
                .withValidator(string -> string != null && !string.isEmpty(), "Введите фамилию")
                .asRequired()
                .bind(Doctor::getSurname, Doctor::setSurname);
        binder.forField(patronymicText)
                .withValidator(string -> string != null && !string.isEmpty(), "Введите отчество")
                .asRequired()
                .bind(Doctor::getPatronymic, Doctor::setPatronymic);
        binder.forField(specializationText)
                .withValidator(string -> string != null && !string.isEmpty(), "Введите специализацию")
                .asRequired()
                .bind(Doctor::getSpecialization, Doctor::setSpecialization);
    }

    @Override
    protected void setupBtListeners() {
        saveBt.addClickListener(e -> {
            if (binder.validate().isOk()) {
                if (operation == Operations.edit) {
                    try {
                        binder.writeBean(domainObject);
                    } catch (ValidationException ex) {
                        ex.printStackTrace();
                    }
                    DoctorView.getDoctorService().update(domainObject);
                    findAncestor(Window.class).close();
                } else if (operation == Operations.add) {
                    try {
                        binder.writeBean(domainObject);
                    } catch (ValidationException ex) {
                        ex.printStackTrace();
                    }
                    DoctorView.getDoctorService().save(domainObject);
                    findAncestor(Window.class).close();
                }
            }
        });

        closeBt.addClickListener(e -> {
            findAncestor(Window.class).close();
        });
    }
}
