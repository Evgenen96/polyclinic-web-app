package com.haulmont.testtask.dbService.entities;

import java.util.Objects;

public class Doctor {

    private Long id;
    private String firstName;
    private String surname;
    private String patronymic;
    private String specialization;

    public Doctor() {
    }

    public Doctor(String firstName, String surname, String patronymic, String specialization) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public Doctor(Long id, String firstName, String surname, String patronymic, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor #" + id + " - " + firstName + " " + surname + " " + patronymic + " - " + specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id) &&
                firstName.equals(doctor.firstName) &&
                surname.equals(doctor.surname) &&
                patronymic.equals(doctor.patronymic) &&
                specialization.equals(doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, patronymic, specialization);
    }
}
