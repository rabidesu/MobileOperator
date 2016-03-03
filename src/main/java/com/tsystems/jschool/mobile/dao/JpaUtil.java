package com.tsystems.jschool.mobile.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class JpaUtil {

    private static final EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("MobilePU");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

    public static EntityManager beginTransaction(){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    public static void commitTransaction(EntityManager entityManager){
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void rollbackTransaction(EntityManager entityManager){
        entityManager.getTransaction().rollback();
        entityManager.close();
    }




}
