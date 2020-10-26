package com.haulmont.testtask.dbservice.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "validity")
    private Integer validity;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="doctorId")
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="patientId")
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="priorityId")
    private RecipePriority recipePriority;


    public Recipe() {
        creationDate = new Date();
    }

    public Recipe(String description, Date creationDate, Integer validity) {
        this.description = description;
        this.creationDate = creationDate;
        this.validity = validity;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public RecipePriority getRecipePriority() {
        return recipePriority;
    }

    public void setRecipePriority(RecipePriority recipePriority) {
        this.recipePriority = recipePriority;
    }

    @Override
    public String toString() {
        return "\nRecipe #" + id + ": " + description +
                "\n\tTo: " + patient +
                "\n\tFrom: " + doctor + "\n" +
                creationDate + "; " +  validity + " days" +
                " Priority: " + recipePriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(creationDate, recipe.creationDate) &&
                Objects.equals(validity, recipe.validity) &&
                Objects.equals(doctor, recipe.doctor) &&
                Objects.equals(patient, recipe.patient) &&
                Objects.equals(recipePriority, recipe.recipePriority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, creationDate, validity, doctor, patient, recipePriority);
    }
}
