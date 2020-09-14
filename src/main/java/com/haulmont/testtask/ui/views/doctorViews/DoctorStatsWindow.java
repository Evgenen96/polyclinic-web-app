package com.haulmont.testtask.ui.views.doctorViews;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.ui.utils.Operations;
import com.haulmont.testtask.ui.views.recipeViews.RecipeView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import javax.print.Doc;
import java.util.LinkedHashMap;
import java.util.Map;

public class DoctorStatsWindow extends Window {

    private final Grid<Map<Doctor, Long>> doctorStatsGrid = new Grid<>("Количество рецептов, выписанных докторами");

    public DoctorStatsWindow() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        setGrid();
        layout.addComponent(doctorStatsGrid);


        setWidth("800px");
        setHeight("400px");
        setModal(true);
        setContent(layout);
    }

    private void setGrid() {

    }


}
