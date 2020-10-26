package com.haulmont.testtask;

import com.haulmont.testtask.config.AppConfig;
import com.haulmont.testtask.dbservice.services.DBServiceImpl;
import com.haulmont.testtask.dbservice.services.DoctorServiceImpl;
import com.haulmont.testtask.dbservice.services.interfaces.DBService;
import com.haulmont.testtask.dbservice.services.interfaces.DoctorService;
import com.haulmont.testtask.dbservice.services.interfaces.PatientService;
import com.haulmont.testtask.dbservice.services.interfaces.RecipeService;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Run to create database and populate it
 */
public class Main {
    public static void main(String[] args) {
//            DBService db = new DBServiceImpl();
//            DBServiceImpl.getHSQLConnection();
//            db.createDB();
//            db.populateDB();
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        DoctorService d = context.getBean("doctorServiceImpl", DoctorServiceImpl.class);
//        System.out.println(d.getById(0L));
//        System.out.println(d.getAll().get(0));
//
//        PatientService ps = context.getBean("patientServiceImpl", PatientService.class);
//        System.out.println(ps.getById(5L));
//
//
//        RecipeService rs = context.getBean("recipeServiceImpl", RecipeService.class);
//        System.out.println(rs.getById(3L));
//        System.out.println(rs.getAll().get(3));
//
        DatabaseManagerSwing.main(new String[] {
                "--url", "jdbc:hsqldb:file:db/testdb", "--noexit"
        });
    }
}
