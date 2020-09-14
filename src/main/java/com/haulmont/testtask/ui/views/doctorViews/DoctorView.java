package com.haulmont.testtask.ui.views.doctorViews;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.dbService.services.base.DoctorService;
import com.haulmont.testtask.ui.utils.Operations;
import com.haulmont.testtask.ui.views.MainView;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class DoctorView extends VerticalLayout implements View {

    private static DoctorService doctorService;
    private final Grid<Doctor> doctorGrid = new Grid<>("Доктора");
    private final Button editBt = new Button("Редактировать");
    private final Button addBt = new Button("Добавить");
    private final Button delBt = new Button("Удалить");
    private final Button statBt = new Button("Статистика");

    public DoctorView() {

        doctorService = MainView.getDoctorService();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);

        setGrid();
        updateGrid();
        layout.addComponent(doctorGrid);
        layout.addComponent(createCrudBtLayout());
        addComponent(layout);

        setSpacing(false);
    }

    private void setGrid() {
        doctorGrid.addColumn(Doctor::getSurname).setCaption("Фамилия");
        doctorGrid.addColumn(Doctor::getFirstName).setCaption("Имя");
        doctorGrid.addColumn(Doctor::getPatronymic).setCaption("Отчество");
        doctorGrid.addColumn(Doctor::getSpecialization).setCaption("Специализация");
        doctorGrid.setSizeFull();
        doctorGrid.addSelectionListener(valueChangeEvent -> {
            if (!doctorGrid.asSingleSelect().isEmpty()) {
                editBt.setEnabled(true);
                delBt.setEnabled(true);
            } else {
                editBt.setEnabled(false);
                delBt.setEnabled(false);
            }
        });
    }

    private void updateGrid() {
        doctorGrid.setItems(doctorService.getAll());
    }

    private HorizontalLayout createCrudBtLayout() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        editBt.setEnabled(false);
        delBt.setEnabled(false);
        buttonsLayout.addComponents(addBt, editBt, delBt, statBt);
        addBt.addClickListener(click -> {
            Window doctorWindow = new DoctorFormWindow(Operations.add, null);
            doctorWindow.addCloseListener(e -> {
                updateGrid();
            });
            getUI().addWindow(doctorWindow);
        });
        editBt.addClickListener(click -> {
            Doctor doctor = doctorGrid.asSingleSelect().getValue();
            Window doctorWindow = new DoctorFormWindow(Operations.edit, doctor);
            doctorWindow.addCloseListener(e -> {
                updateGrid();
            });
            getUI().addWindow(doctorWindow);
        });
        delBt.addClickListener(click -> {
            Doctor doctor = doctorGrid.asSingleSelect().getValue();
            doctorService.remove(doctor);
            updateGrid();
        });
        statBt.addClickListener(click ->{
            Window doctorWindow = new DoctorStatsWindow();
            getUI().addWindow(doctorWindow);
        });
       return buttonsLayout;
    }

    public static DoctorService getDoctorService() {
        return doctorService;
    }

    public static void setDoctorService(DoctorService doctorService) {
        DoctorView.doctorService = doctorService;
    }
}


