package com.haulmont.testtask.dbService.entities;

import java.util.Objects;

public class Patient {

    private Long id ;
    private String firstName;
    private String surname;
    private String patronymic;
    private String phoneNumber;

    public Patient(String firstName, String surname, String patronymic, String phoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    public Patient(Long id, String firstName, String surname, String patronymic, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient #" + id + " - " + firstName + " " + surname + " " + patronymic + ". Phone: " + phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id) &&
                firstName.equals(patient.firstName) &&
                surname.equals(patient.surname) &&
                patronymic.equals(patient.patronymic) &&
                phoneNumber.equals(patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, patronymic, phoneNumber);
    }
}
