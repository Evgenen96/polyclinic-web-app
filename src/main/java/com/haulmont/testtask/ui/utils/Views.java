package com.haulmont.testtask.ui.utils;

public enum Views {
    Doctors, Patients, Recipes;

    public String translate() {
        switch (this) {
            case Doctors:
                return "Доктора";
            case Patients:
                return "Пациенты";
            case Recipes:
                return "Выданные рецепты";
            default:
                return null;

        }
    }
}
