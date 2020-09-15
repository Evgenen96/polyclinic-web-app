package com.haulmont.testtask.dbservice.services.base;


public interface DBService {

    void createDB();

    void populateDB();

    void runSqlScript(String scriptPath);
}
