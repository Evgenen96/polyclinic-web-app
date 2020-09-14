package com.haulmont.testtask.ui.views.patientViews;

import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.dbService.services.base.PatientService;
import com.haulmont.testtask.ui.utils.Operations;
import com.haulmont.testtask.ui.views.MainView;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class PatientView extends VerticalLayout implements View {

    private static PatientService patientService;
    private final Grid<Patient> patientGrid = new Grid<>("Пациенты");
    private final Button editBt = new Button("Редактировать");
    private final Button addBt = new Button("Добавить");
    private final Button delBt = new Button("Удалить");

    public PatientView() {

        patientService = MainView.getPatientService();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);

        setGrid();
        updateGrid();
        layout.addComponent(patientGrid);
        layout.addComponent(createCrudBtLayout());
        addComponent(layout);

        setSpacing(false);
    }

    private void setGrid() {
        patientGrid.addColumn(Patient::getSurname).setCaption("Фамилия");
        patientGrid.addColumn(Patient::getFirstName).setCaption("Имя");
        patientGrid.addColumn(Patient::getPatronymic).setCaption("Отчество");
        patientGrid.addColumn(Patient::getPhoneNumber).setCaption("Телефон");
        patientGrid.setSizeFull();
        patientGrid.addSelectionListener(valueChangeEvent -> {
            if (!patientGrid.asSingleSelect().isEmpty()) {
                editBt.setEnabled(true);
                delBt.setEnabled(true);
            } else {
                editBt.setEnabled(false);
                delBt.setEnabled(false);
            }
        });
    }

    private void updateGrid() {
        patientGrid.setItems(patientService.getAll());
    }

    private HorizontalLayout createCrudBtLayout() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        editBt.setEnabled(false);
        delBt.setEnabled(false);
        buttonsLayout.addComponents(addBt, editBt, delBt);
        addBt.addClickListener(click -> {
            Window patientWindow = new PatientFormWindow(Operations.add, new Patient());
            patientWindow.addCloseListener(closeEvent -> {
                updateGrid();
            });
            getUI().addWindow(patientWindow);
        });
        editBt.addClickListener(click -> {
            Patient patient = patientGrid.asSingleSelect().getValue();
            Window patientWindow = new PatientFormWindow(Operations.edit, patient);
            patientWindow.addCloseListener(closeEvent -> {
                updateGrid();
            });
            getUI().addWindow(patientWindow);
        });
        delBt.addClickListener(click -> {
            Patient patient = patientGrid.asSingleSelect().getValue();
            patientService.remove(patient);
            updateGrid();
        });
        return buttonsLayout;
    }

    public static PatientService getPatientService() {
        return patientService;
    }

    public static void setPatientService(PatientService patientService) {
        PatientView.patientService = patientService;
    }

//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        Notification.show("Welcome to the Animal Farm");
//    }
}
