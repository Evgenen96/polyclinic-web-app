package com.haulmont.testtask.dbService.dao;

import com.haulmont.testtask.dbService.dao.base.PatientDao;
import com.haulmont.testtask.dbService.services.DBServiceImpl;
import com.haulmont.testtask.dbService.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public void save(Patient object) {
        String sqlQuery = "INSERT INTO patients(first_name, surname, patronymic, phone_number) VALUES(?,?,?,?)";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setString(1, object.getFirstName());
            preparedStmt.setString(2, object.getSurname());
            preparedStmt.setString(3, object.getPatronymic());
            preparedStmt.setString(4, object.getPhoneNumber());
            preparedStmt.executeUpdate();
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }

    @Override
    public Patient getById(Long id) {
        String sqlQuery = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (!rs.next()) return null;
            return getPatient(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Patient> getAll() {
        List<Patient> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM patients;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                list.add(getPatient(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void update(Patient patient) {
        String sqlQuery =
                "UPDATE patients " +
                        "SET first_name = ?, surname = ?, patronymic = ?, phone_number = ? " +
                        "WHERE patient_id = ?;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setString(1, patient.getFirstName());
            preparedStmt.setString(2, patient.getSurname());
            preparedStmt.setString(3, patient.getPatronymic());
            preparedStmt.setString(4, patient.getPhoneNumber());
            preparedStmt.setLong(5, patient.getId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Patient patient) {
        String sqlQuery = "DELETE FROM patients WHERE patient_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, patient.getId());
            preparedStmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("Can't remove record: " + patient +
                    " \nbecause something references this");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Patient getPatient(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getLong("patient_id"),
                rs.getString("first_name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getString("phone_number"));
    }
}
