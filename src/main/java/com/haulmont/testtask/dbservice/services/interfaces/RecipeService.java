package com.haulmont.testtask.dbservice.services.interfaces;

import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RecipeService extends Service<Recipe> {

    @Transactional
    Map<String,Long> getCountOfRecipesByDoctor();

    @Transactional
    List<Recipe> filterByDescription(String description);

    @Transactional
    List<Recipe> filterByPatient(Patient patient);

    @Transactional
    List<Recipe> filterByPriority(RecipePriority priority);
}
