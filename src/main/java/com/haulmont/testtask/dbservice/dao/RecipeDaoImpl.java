package com.haulmont.testtask.dbservice.dao;

import com.haulmont.testtask.dbservice.dao.base.RecipeDao;
import com.haulmont.testtask.dbservice.services.DBServiceImpl;
import com.haulmont.testtask.dbservice.entities.Doctor;
import com.haulmont.testtask.dbservice.entities.Patient;
import com.haulmont.testtask.dbservice.entities.Recipe;
import com.haulmont.testtask.dbservice.entities.RecipePriority;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RecipeDaoImpl implements RecipeDao {

    @Override
    public void save(Recipe recipe) {
        String sqlQuery = "" +
                "INSERT INTO recipes(description, patient_id, doctor_id, creation_date, validity, priority_id) " +
                "VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            //casting util.Date to sql.Date
            //todo check later if they are castable directly
            Date sqlCreationDate = null;
            if (recipe.getCreationDate() != null) {
                sqlCreationDate = new Date(recipe.getCreationDate().getTime());
            }
            preparedStmt.setString(1, recipe.getDescription());
            preparedStmt.setLong(2, recipe.getPatient().getId());
            preparedStmt.setLong(3, recipe.getDoctor().getId());
            preparedStmt.setDate(4, sqlCreationDate);
            preparedStmt.setInt(5, recipe.getValidity());
            preparedStmt.setInt(6, recipe.getPriority().ordinal());
            preparedStmt.executeUpdate();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }

    @Override
    public Recipe getById(Long id) {
        String sqlQuery = "SELECT recipe_id, description, creation_date, validity, priority_id, " +
                "patient_id, p.first_name as p_first_name, " +
                "p.surname as p_surname, p.patronymic as p_patronymic, p.phone_number, " +
                "doctor_id, d.first_name as d_first_name," +
                "d.surname as d_surname, d.patronymic as d_patronymic, d.specialization " +
                "FROM recipes r" +
                "INNER JOIN patients as p USING (patient_id) " +
                "INNER JOIN doctors as d USING (doctor_id) " +
                "WHERE recipe_id = ? ";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (!rs.next()) return null;
            return getRecipe(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> list = new ArrayList<>();
        String sqlQuery = "SELECT recipe_id, description, creation_date, validity, priority_id , " +
                "p.patient_id, p.first_name as p_first_name, " +
                "p.surname as p_surname, p.patronymic as p_patronymic, p.phone_number, " +
                "d.doctor_id, d.first_name as d_first_name," +
                "d.surname as d_surname, d.patronymic as d_patronymic, d.specialization " +
                "FROM recipes r" +
                "INNER JOIN patients p USING (patient_id) " +
                "INNER JOIN doctors d USING (doctor_id) ";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                list.add(getRecipe(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void update(Recipe recipe) {
        String sqlQuery =
                "UPDATE recipes " +
                        "SET description = ?, patient_id = ?, doctor_id = ?, " +
                        "creation_date = ?, validity = ?, priority_id = ? " +
                        "WHERE recipe_id = ?;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            Date sqlCreationDate = null;
            if (recipe.getCreationDate() != null) {
                sqlCreationDate = new Date(recipe.getCreationDate().getTime());
            }
            preparedStmt.setString(1, recipe.getDescription());
            preparedStmt.setLong(2, recipe.getPatient().getId());
            preparedStmt.setLong(3, recipe.getDoctor().getId());
            preparedStmt.setDate(4, sqlCreationDate);
            preparedStmt.setInt(5, recipe.getValidity());
            preparedStmt.setInt(6, recipe.getPriority().ordinal());
            preparedStmt.setLong(7, recipe.getId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Recipe recipe) {
        String sqlQuery = "DELETE FROM recipes WHERE recipe_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, recipe.getId());
            preparedStmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("Can't remove record: " + recipe +
                    " \nbecause something references this");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Recipe getRecipe(ResultSet rs) throws SQLException {
        return new Recipe(
                rs.getLong("recipe_id"),
                rs.getString("description"),
                new Patient(
                        rs.getLong("patient_id"),
                        rs.getString("p_first_name"),
                        rs.getString("p_surname"),
                        rs.getString("p_patronymic"),
                        rs.getString("phone_number")
                ),
                new Doctor(
                        rs.getLong("doctor_id"),
                        rs.getString("d_first_name"),
                        rs.getString("d_surname"),
                        rs.getString("d_patronymic"),
                        rs.getString("specialization")
                ),
                rs.getDate("creation_date"),
                rs.getInt("validity"),
                RecipePriority.values()[rs.getInt("priority_id")]);
    }

    @Override
    public Map<Doctor, Long> getCountOfRecipesByDoctor() {
        String sqlQuery = "SELECT * " +
                "FROM (SELECT doctor_id, count(*) AS amount FROM recipes GROUP BY doctor_id)" +
                "INNER JOIN doctors USING(doctor_id)";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = preparedStmt.executeQuery();
            Map<Doctor, Long> items = new LinkedHashMap<>();
            while (rs.next()) {
                Doctor doctor = DoctorDaoImpl.getDoctor(rs);
                Long amount = rs.getLong("amount");
                items.put(doctor, amount);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Recipe> filterByDescription(String description) {
        List<Recipe> list = new ArrayList<>();
        String sqlQuery = "SELECT recipe_id, description, creation_date, validity, priority_id , " +
                "patient_id, p.first_name as p_first_name, " +
                "p.surname as p_surname, p.patronymic as p_patronymic, p.phone_number, " +
                "doctor_id, d.first_name as d_first_name," +
                "d.surname as d_surname, d.patronymic as d_patronymic, d.specialization " +
                "FROM recipes r " +
                "INNER JOIN patients p USING (patient_id) " +
                "INNER JOIN doctors d USING (doctor_id) " +
                "WHERE LOWER(description) LIKE LOWER('%' + ? + '%') ";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setString(1, description);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                list.add(getRecipe(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Recipe> filterByPatientName(Patient patient) {
        List<Recipe> list = new ArrayList<>();
        String sqlQuery = "SELECT recipe_id, description, creation_date, validity, priority_id , " +
                "p.patient_id, p.first_name as p_first_name, " +
                "p.surname as p_surname, p.patronymic as p_patronymic, p.phone_number, " +
                "d.doctor_id, d.first_name as d_first_name," +
                "d.surname as d_surname, d.patronymic as d_patronymic, d.specialization " +
                "FROM recipes r " +
                "INNER JOIN patients p USING (patient_id) " +
                "INNER JOIN doctors d USING (doctor_id) " +
                "WHERE r.patient_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, patient.getId());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                list.add(getRecipe(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Recipe> filterByPriority(RecipePriority priority) {
        List<Recipe> list = new ArrayList<>();
        String sqlQuery = "SELECT recipe_id, description, creation_date, validity, priority_id , " +
                "p.patient_id, p.first_name as p_first_name, " +
                "p.surname as p_surname, p.patronymic as p_patronymic, p.phone_number, " +
                "d.doctor_id, d.first_name as d_first_name," +
                "d.surname as d_surname, d.patronymic as d_patronymic, d.specialization " +
                "FROM recipes r " +
                "INNER JOIN patients p USING (patient_id) " +
                "INNER JOIN doctors d USING (doctor_id) " +
                "WHERE priority_id = ?;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setInt(1, priority.ordinal());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                list.add(getRecipe(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }
}
