package com.haulmont.testtask.dbservice.entities;

import java.util.Date;
import java.util.Objects;

public class Recipe {

    private Long id;
    private String description;
    private Patient patient;
    private Doctor doctor;
    private Date creationDate;
    private Integer validity;
    private RecipePriority priority;

    public Recipe() {
        doctor = new Doctor();
        patient = new Patient();
        priority = RecipePriority.NORMAL;
        creationDate = new Date();
    }

    public Recipe(String description, Patient patient,
                  Doctor doctor, Date creationDate,
                  Integer validity, RecipePriority priority) {
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
        this.creationDate = creationDate;
        this.validity = validity;
        this.priority = priority;
    }

    public Recipe(Long id, String description,
                  Patient patient, Doctor doctor,
                  Date creationDate, Integer validity,
                  RecipePriority priority) {
        this.id = id;
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
        this.creationDate = creationDate;
        this.validity = validity;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public RecipePriority getPriority() {
        return priority;
    }

    public void setPriority(RecipePriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "\nRecipe #" + id + ": " + description +
                "\n\tTo: " + patient +
                "\n\tFrom: " + doctor + "\n" +
                creationDate + "; " +  validity + " days" +
                " Priority: " + priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return id.equals(recipe.id) &&
                description.equals(recipe.description) &&
                patient.equals(recipe.patient) &&
                doctor.equals(recipe.doctor) &&
                creationDate.equals(recipe.creationDate) &&
                validity.equals(recipe.validity) &&
                priority == recipe.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, patient, doctor, creationDate, validity, priority);
    }
}
