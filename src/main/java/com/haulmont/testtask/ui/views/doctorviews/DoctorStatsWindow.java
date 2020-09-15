package com.haulmont.testtask.ui.views.doctorviews;

import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.ui.views.recipeviews.RecipeView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.Map;
import java.util.Map.Entry;

/**
 * DoctorStatsWindow class represents a grid with amount of recipes given by every doctor
 */
public class DoctorStatsWindow extends Window {

    private final Grid<Map.Entry<Doctor, Long>> doctorStatsGrid = new Grid<>("Количество рецептов, выписанных докторами");

    public DoctorStatsWindow() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        setGrid();
        layout.addComponent(doctorStatsGrid);


        setWidth("600px");
        setHeight("400px");
        setModal(true);
        setContent(layout);
    }

    private void setGrid() {
        doctorStatsGrid.setSizeFull();
        doctorStatsGrid.addColumn(Entry -> {
            return Entry.getKey().getSurname() + " "
                    + Entry.getKey().getFirstName() + " "
                    + Entry.getKey().getPatronymic();
        }).setCaption("Доктор");
        doctorStatsGrid.addColumn(Entry::getValue).setCaption("Количество рецептов");
        doctorStatsGrid.setItems(RecipeView.getRecipeService().getCountOfRecipesByDoctor().entrySet());
    }


}
