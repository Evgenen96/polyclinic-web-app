package com.haulmont.testtask.ui.views.recipeViews;


import com.haulmont.testtask.dbService.entities.Recipe;
import com.haulmont.testtask.ui.utils.Operations;
import com.vaadin.ui.Window;

public class RecipeFormWindow extends Window {
    private RecipeForm recipeForm;

    public RecipeFormWindow(Operations operation, Recipe recipe) {
        this.recipeForm = new RecipeForm(operation, recipe);
        setWidth("400px");
        setHeight("350px");
        setModal(true);
        setContent(recipeForm);
    }
}