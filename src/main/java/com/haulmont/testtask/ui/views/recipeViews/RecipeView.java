package com.haulmont.testtask.ui.views.recipeViews;

import com.haulmont.testtask.dbService.entities.Recipe;
import com.haulmont.testtask.dbService.services.base.RecipeService;
import com.haulmont.testtask.ui.utils.Operations;
import com.haulmont.testtask.ui.views.MainView;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

public class RecipeView extends VerticalLayout implements View {

    private static RecipeService recipeService;
    private final Grid<Recipe> recipeGrid = new Grid<>("Выписанные рецепты");
    private final Button editBt = new Button("Редактировать");
    private final Button addBt = new Button("Добавить");
    private final Button delBt = new Button("Удалить");

    public RecipeView() {

        recipeService = MainView.getRecipeService();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);

        setGrid();
        updateGrid();
        layout.addComponent(recipeGrid);
        layout.addComponent(createButtonsLayout());
        addComponent(layout);

        setSpacing(false);
    }

    private void setGrid() {
        recipeGrid.addColumn(Recipe::getDescription).setCaption("Описание");
        recipeGrid.addColumn(Recipe -> {
            return Recipe.getPatient().getFirstName() + " " +
                    Recipe.getPatient().getSurname();
        }).setCaption("Пациент");
        recipeGrid.addColumn(Recipe -> {
            return Recipe.getDoctor().getFirstName() + " " +
                    Recipe.getDoctor().getSurname();
        }).setCaption("Доктор");
        recipeGrid.addColumn(Recipe::getCreationDate).setCaption("Дата выписки");
        recipeGrid.addColumn(Recipe -> {
            int validity = Recipe.getValidity();
            String days = null;
            if (validity > 10 && validity < 15) {
                days = " дней";
            } else if (validity % 10 > 1 && validity % 10 < 5) {
                days = " дня";
            } else if (validity % 10 == 1){
                days = " день";
            } else {
                days = " дней";
            }
            return Recipe.getValidity() + days;
        }).setCaption("Срок действия");
        recipeGrid.addColumn(Recipe::getPriority).setCaption("Приоритет");
        recipeGrid.setSizeFull();
        recipeGrid.addSelectionListener(valueChangeEvent -> {
            if (!recipeGrid.asSingleSelect().isEmpty()) {
                editBt.setEnabled(true);
                delBt.setEnabled(true);
            } else {
                editBt.setEnabled(false);
                delBt.setEnabled(false);
            }
        });
    }

    private void updateGrid() {
        recipeGrid.setItems(recipeService.getAll());
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        editBt.setEnabled(false);
        delBt.setEnabled(false);
        buttonsLayout.addComponents(addBt, editBt, delBt);
        addBt.addClickListener(click -> {
            Window recipeWindow = new RecipeFormWindow(Operations.add, new Recipe());
            recipeWindow.addCloseListener(e -> {
                updateGrid();
            });
            getUI().addWindow(recipeWindow);
        });
        editBt.addClickListener(click -> {
            Recipe recipe = recipeGrid.asSingleSelect().getValue();
            Window recipeWindow = new RecipeFormWindow(Operations.edit, recipe);
            recipeWindow.addCloseListener(e -> {
                updateGrid();
            });
            getUI().addWindow(recipeWindow);
        });
        delBt.addClickListener(click -> {
            Recipe recipe = recipeGrid.asSingleSelect().getValue();
            recipeService.remove(recipe);
            updateGrid();
        });
        return buttonsLayout;
    }

    public static RecipeService getRecipeService() {
        return recipeService;
    }

    public static void setRecipeService(RecipeService recipeService) {
        RecipeView.recipeService = recipeService;
    }

//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        Notification.show("Welcome to the Animal Farm");
//    }
}
