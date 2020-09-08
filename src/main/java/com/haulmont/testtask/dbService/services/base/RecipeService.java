package com.haulmont.testtask.dbService.services.base;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.dbService.entities.Recipe;
import com.haulmont.testtask.dbService.entities.RecipePriority;

import java.util.List;

public interface RecipeService extends Service<Recipe> {

    long getCountOfRecipesByDoctor(Doctor doctor);

    List<Recipe> filterByDescription(String description);

    List<Recipe> filterByPatient(Patient patient);

    List<Recipe> filterByPriority(RecipePriority priority);
}
