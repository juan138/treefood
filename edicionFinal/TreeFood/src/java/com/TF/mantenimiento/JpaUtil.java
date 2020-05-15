/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juan.serranoUSAM
 */
public class JpaUtil {

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("TreeFoodPU");
        } catch (Exception e) {
            System.out.println("oye sucedio un error " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

}
