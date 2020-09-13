package com.haulmont.testtask.dbService.entities;

/**
 *  The RecipePriority enum stores possible types of recipes priority
 */
public enum RecipePriority {
    /**
     *  The normal priority
     */
    NORMAL,

    /**
     * The urgent priority
     */
    CITO,

    /**
     * The immediately priority
     */
    STATIM;

    @Override
    public String toString() {
        return this.name();
    }
}
