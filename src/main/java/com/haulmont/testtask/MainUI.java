package com.haulmont.testtask;

import com.haulmont.testtask.ui.utils.Views;
import com.haulmont.testtask.ui.views.MainView;
import com.haulmont.testtask.ui.views.patientViews.PatientView;
import com.haulmont.testtask.ui.views.doctorViews.DoctorView;
import com.haulmont.testtask.ui.views.recipeViews.RecipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

@Theme(ValoTheme.THEME_NAME)
@PushStateNavigation
public class MainUI extends UI {
    private static Navigator navigator;
    private Button nav = new Button("Go");
    private Button bav = new Button("No");

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        getPage().setTitle("Поликлиника");

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);



        VerticalLayout sideMenu = new VerticalLayout();
        sideMenu.setWidth("200px");
        sideMenu.setHeightFull();

        Button patientButton =
                new Button("Пациенты", clickEvent -> getUI().getNavigator().navigateTo(Views.Patients.name()));
        patientButton.setWidth("100%");
        // patientButton.addStyleName(AppTheme.BORDERLESS);

        Button doctorButton =
                new Button("Доктора", clickEvent -> getUI().getNavigator().navigateTo(Views.Doctors.name()));
        doctorButton.setWidth("100%");
        // doctorButton.addStyleName(AppTheme.BORDERLESS);

        Button receiptButton = new Button("Рецепты", clickEvent -> getUI().getNavigator().navigateTo(Views.Recipes.name()));
        receiptButton.setWidthFull();
        // receiptButton.addStyleName(AppTheme.BORDERLESS);

        sideMenu.addComponents(patientButton, doctorButton, receiptButton);

        VerticalLayout viewsLayout = new VerticalLayout();
        viewsLayout.setSizeFull();
        viewsLayout.setMargin(false);
        viewsLayout.setSpacing(true);

        mainLayout.addComponents(sideMenu,viewsLayout);
        mainLayout.setExpandRatio(viewsLayout, 1f);

        ViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(viewsLayout);
        Navigator navigator = new Navigator(this, viewDisplay);
        navigator.addView("", new MainView());
        navigator.addView(Views.Doctors.name(), new DoctorView());
        navigator.addView(Views.Patients.name(), new PatientView());
        navigator.addView(Views.Recipes.name(), new RecipeView());

//        headerLayout.setStyleName(AppTheme.HEADER_LAYOUT);
//        mainLayout.setStyleName(AppTheme.VIEW_LAYOUT);
//        viewsLayout.setStyleName(AppTheme.VIEW_LAYOUT);

        setContent(mainLayout);


    }
}