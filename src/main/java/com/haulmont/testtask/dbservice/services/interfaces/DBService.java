package com.haulmont.testtask.dbservice.services.interfaces;


public interface DBService {

    void createDB();

    void populateDB();

    void runSqlScript(String scriptPath);
}
