package com.haulmont.testtask.dbService.dao.base;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.dbService.entities.Recipe;
import com.haulmont.testtask.dbService.entities.RecipePriority;

import java.util.List;

public interface RecipeDao extends Dao<Recipe> {
    long getCountOfRecipesByDoctor(Doctor doctor);

    List<Recipe> filterByDescription(String description);

    List<Recipe> filterByPatientName(Patient patient);

    List<Recipe> filterByPriority(RecipePriority priority);
}
