package com.haulmont.testtask.ui.views;

import com.haulmont.testtask.dbService.services.DoctorServiceImpl;
import com.haulmont.testtask.dbService.services.PatientServiceImpl;
import com.haulmont.testtask.dbService.services.RecipeServiceImpl;
import com.haulmont.testtask.dbService.services.base.DoctorService;
import com.haulmont.testtask.dbService.services.base.PatientService;
import com.haulmont.testtask.dbService.services.base.RecipeService;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {

    private static DoctorService doctorService = new DoctorServiceImpl();
    private static PatientService patientService = new PatientServiceImpl();
    private static RecipeService recipeService = new RecipeServiceImpl();

    public MainView() {

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidthFull();
        headerLayout.setHeight("50px");
        headerLayout.setMargin(true);
        headerLayout.setSpacing(true);

        Label label = new Label("Добро пожаловать в поликлинику");
        headerLayout.addComponent(label);
        headerLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        addComponent(headerLayout);

    }

    public static DoctorService getDoctorService() {
        return doctorService;
    }

    public static PatientService getPatientService() {
        return patientService;
    }

    public static RecipeService getRecipeService() {
        return recipeService;
    }
}
