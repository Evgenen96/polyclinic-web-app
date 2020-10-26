package com.haulmont.testtask.dbservice.dao;

import com.haulmont.testtask.dbservice.dao.interfaces.RecipeDao;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public RecipeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Recipe theRecipe) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theRecipe);
    }

    @Override
    public Recipe getById(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Recipe theRecipe = currentSession.get(Recipe.class, theId);
        return theRecipe;
    }

    @Override
    public List<Recipe> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Recipe> theQuery =
                currentSession.createQuery("from Recipe", Recipe.class);
        return theQuery.getResultList();
    }

    @Override
    public void remove(Long theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Recipe where recipeId=:recipeId");
        theQuery.setParameter("recipeId", theId);
        theQuery.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Long> getCountOfRecipesByDoctor() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<List<String>> theQuery =
                currentSession.createQuery(
                        "select concat(Doctor.lastName, ' ', Doctor.firstName), count(*) as recipeAmount " +
                                "from Recipe right join Doctor " +
                                "group by doctorId");

        // collect result to map
        return theQuery.getResultStream()
                .collect(Collectors.toMap(
                        strings -> strings.get(0),
                        strings -> Long.valueOf(strings.get(1))));
    }


    @Override
    public List<Recipe> filterByDescription(String description) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Recipe> theQuery =
                currentSession.createQuery(
                        "from Recipe where description like concat('%', :description, '%')", Recipe.class);
        theQuery.setParameter("description", description);
        return theQuery.getResultList();
    }

    @Override
    public List<Recipe> filterByPatientName(Patient patient) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Recipe> theQuery =
                currentSession.createQuery("from Recipe where patientId = :patientId", Recipe.class);
        theQuery.setParameter("patientId", patient.getId());
        return theQuery.getResultList();
    }

    @Override
    public List<Recipe> filterByPriority(RecipePriority thePriority) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Recipe> theQuery =
                currentSession.createQuery("from Recipe where priorityId = :priorityId", Recipe.class);
        theQuery.setParameter("priorityId", thePriority.getId());
        return theQuery.getResultList();
    }
}
