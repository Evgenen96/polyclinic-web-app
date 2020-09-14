package com.haulmont.testtask;

import com.haulmont.testtask.dbService.services.DBServiceImpl;
import com.haulmont.testtask.dbService.services.base.DBService;
import org.hsqldb.util.DatabaseManagerSwing;

/**
 * Run to create database and populate it
 */
public class Main {
    public static void main(String[] args) {
        DBService db = new DBServiceImpl();
        DBServiceImpl.getHSQLConnection();
        db.createDB();
        db.populateDB();

        DatabaseManagerSwing.main(new String[] {
                "--url", "jdbc:hsqldb:file:db/testdb", "--noexit"
        });
    }
}
