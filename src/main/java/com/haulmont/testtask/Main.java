package com.haulmont.testtask;

import com.haulmont.testtask.dbService.entities.Doctor;
import com.haulmont.testtask.dbService.entities.Patient;
import com.haulmont.testtask.dbService.entities.RecipePriority;
import com.haulmont.testtask.dbService.services.DBServiceImpl;
import com.haulmont.testtask.dbService.services.DoctorServiceImpl;
import com.haulmont.testtask.dbService.services.PatientServiceImpl;
import com.haulmont.testtask.dbService.services.RecipeServiceImpl;
import com.haulmont.testtask.dbService.services.base.*;
import org.hsqldb.util.DatabaseManagerSwing;


public class Main {
    public static void main(String[] args) {
        DBService db = new DBServiceImpl();
       // DBServiceImpl.getHSQLConnection();
       //db.createDB();
       // db.populateDB();

        PatientService ps = new PatientServiceImpl();
        DoctorService ds = new DoctorServiceImpl();
        RecipeService rs = new RecipeServiceImpl();
        System.out.println(rs.getCountOfRecipesByDoctor());
        //System.out.println(rs.getAll());
       // System.out.println(rs.filterByPriority(RecipePriority.NORMAL));
//        DatabaseManagerSwing.main(new String[] {
//                "--url", "jdbc:hsqldb:file:db/testdb", "--noexit"
//        });
    }
}
