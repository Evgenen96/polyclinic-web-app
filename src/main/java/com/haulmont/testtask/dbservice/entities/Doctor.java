package com.haulmont.testtask.dbservice.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "doctor")
    private List<Recipe> recipes;


    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String patronymic, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public Doctor(Long doctorId, String firstName, String lastName, String patronymic, String specialization) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Doctor #" + doctorId + " - " + firstName + " " + lastName + " " + patronymic + " - " + specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return doctorId.equals(doctor.doctorId) &&
                firstName.equals(doctor.firstName) &&
                lastName.equals(doctor.lastName) &&
                patronymic.equals(doctor.patronymic) &&
                specialization.equals(doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, firstName, lastName, patronymic, specialization);
    }
}
