package com.haulmont.testtask.dbservice.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipePriority")
public class RecipePriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priorityId")
    private Integer priorityId;

    @Column(name = "priorityName")
    private String name;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE}, mappedBy = "recipePriority")
    private List<Recipe> recipes;

    public RecipePriority() {
    }

    public RecipePriority(String name) {
        this.name = name;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return name;
    }

}
