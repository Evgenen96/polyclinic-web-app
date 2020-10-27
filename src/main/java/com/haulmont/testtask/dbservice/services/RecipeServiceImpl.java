package com.haulmont.testtask.dbservice.services;

import com.haulmont.testtask.dbservice.service.interfaces.RecipeDao;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import com.haulmont.testtask.dbservice.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao hibernateRecipeDao;

    @Override
    @Transactional
    public void save(Recipe recipe) {
        hibernateRecipeDao.save(recipe);
    }

    @Override
    @Transactional
    public Recipe getById(Long id) {
        return hibernateRecipeDao.getById(id);
    }

    @Override
    @Transactional
    public List<Recipe> getAll() {
        return hibernateRecipeDao.getAll();
    }


    @Override
    @Transactional
    public void remove(Long id) {
        hibernateRecipeDao.remove(id);
    }

    @Override
    public Map<String,Long> getCountOfRecipesByDoctor() {
        return hibernateRecipeDao.getCountOfRecipesByDoctor();
    }

    @Override
    public List<Recipe> filterByDescription(String description) {
        return hibernateRecipeDao.filterByDescription(description);
    }

    @Override
    public List<Recipe> filterByPatient(Patient patient) {
        return hibernateRecipeDao.filterByPatientName(patient);
    }

    @Override
    public List<Recipe> filterByPriority(RecipePriority priority) {
        return hibernateRecipeDao.filterByPriority(priority);
    }
}
