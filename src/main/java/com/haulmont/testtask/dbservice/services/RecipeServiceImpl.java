package com.haulmont.testtask.dbservice.services;

import com.haulmont.testtask.dbservice.dao.RecipeDaoImpl;
import com.haulmont.testtask.dbservice.dao.base.RecipeDao;
import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import com.haulmont.testtask.dbservice.services.base.RecipeService;

import java.util.List;
import java.util.Map;

public class RecipeServiceImpl implements RecipeService {

    private static RecipeDao jdbcRecipeDao = new RecipeDaoImpl();

    @Override
    public void save(Recipe recipe) {
        jdbcRecipeDao.save(recipe);
    }

    @Override
    public Recipe getById(Long id) {
        return jdbcRecipeDao.getById(id);
    }

    @Override
    public List<Recipe> getAll() {
        return jdbcRecipeDao.getAll();
    }

    @Override
    public void update(Recipe recipe) {
        jdbcRecipeDao.update(recipe);
    }

    @Override
    public void remove(Recipe recipe) {
        jdbcRecipeDao.remove(recipe);
    }

    @Override
    public Map<Doctor,Long> getCountOfRecipesByDoctor() {
        return jdbcRecipeDao.getCountOfRecipesByDoctor();
    }

    @Override
    public List<Recipe> filterByDescription(String description) {
        return jdbcRecipeDao.filterByDescription(description);
    }

    @Override
    public List<Recipe> filterByPatient(Patient patient) {
        return jdbcRecipeDao.filterByPatientName(patient);
    }

    @Override
    public List<Recipe> filterByPriority(RecipePriority priority) {
        return jdbcRecipeDao.filterByPriority(priority);
    }
}