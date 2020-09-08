package com.haulmont.testtask.dbService.services.base;


public interface DBService {

    void createDB();

    void populateDB();

    void runSqlScript(String scriptPath);
}
