package com.sunkuet02.jpatest.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by sun on 3/13/17.
 */
public class DbConnection {

    private static DbConnection dbConnection = new DbConnection();
    private EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private DbConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static DbConnection getInstance() {
        return dbConnection;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
