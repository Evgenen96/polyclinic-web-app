package com.haulmont.testtask.dbService.dao;

import com.haulmont.testtask.dbService.dao.base.DoctorDao;
import com.haulmont.testtask.dbService.services.DBServiceImpl;
import com.haulmont.testtask.dbService.entities.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public void save(Doctor doctor) {
        String sqlQuery = "INSERT INTO doctors(first_name, surname, patronymic, specialization) VALUES(?,?,?,?)";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setString(1, doctor.getFirstName());
            preparedStmt.setString(2, doctor.getSurname());
            preparedStmt.setString(3, doctor.getPatronymic());
            preparedStmt.setString(4, doctor.getSpecialization());
            preparedStmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("Can't remove record: " + doctor +
                    " \nbecause something references this");
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }

    @Override
    public Doctor getById(Long id) {
        String sqlQuery = "SELECT * FROM doctors WHERE doctor_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (!rs.next()) return null;
            return getDoctor(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAll() {
        List<Doctor> list = new ArrayList<>();
        String sqlQuery = "SELECT * FROM doctors;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                list.add(getDoctor(rs));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Doctor doctor) {
        String sqlQuery =
                "UPDATE doctors " +
                        "SET first_name = ?, surname = ?, patronymic = ?, specialization = ? " +
                        "WHERE doctor_id = ?;";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setString(1, doctor.getFirstName());
            preparedStmt.setString(2, doctor.getSurname());
            preparedStmt.setString(3, doctor.getPatronymic());
            preparedStmt.setString(4, doctor.getSpecialization());
            preparedStmt.setLong(5, doctor.getId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Doctor doctor) {
        String sqlQuery = "DELETE FROM doctors " + "WHERE doctor_id = ?";
        try (Connection connection = DBServiceImpl.getHSQLConnection();
             PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery)) {
            preparedStmt.setLong(1, doctor.getId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Doctor getDoctor(ResultSet rs) throws SQLException {

        return new Doctor(
                rs.getLong("doctor_id"),
                rs.getString("first_name"),
                rs.getString("surname"),
                rs.getString("patronymic"),
                rs.getString("specialization"));
    }
}
