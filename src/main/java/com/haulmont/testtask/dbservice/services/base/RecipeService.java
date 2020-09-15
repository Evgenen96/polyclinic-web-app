package com.haulmont.testtask.dbservice.services.base;

import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;

import java.util.List;
import java.util.Map;

public interface RecipeService extends Service<Recipe> {

    Map<Doctor,Long> getCountOfRecipesByDoctor();

    List<Recipe> filterByDescription(String description);

    List<Recipe> filterByPatient(Patient patient);

    List<Recipe> filterByPriority(RecipePriority priority);
}
