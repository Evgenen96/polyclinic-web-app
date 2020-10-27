package com.haulmont.testtask.dbservice.service.interfaces;

import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;

import java.util.List;
import java.util.Map;

public interface RecipeDao extends Dao<Recipe> {
    Map<String, Long> getCountOfRecipesByDoctor();

    List<Recipe> filterByDescription(String description);

    List<Recipe> filterByPatientName(Patient patient);

    List<Recipe> filterByPriority(RecipePriority priority);
}
