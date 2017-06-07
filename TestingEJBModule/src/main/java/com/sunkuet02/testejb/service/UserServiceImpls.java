package com.sunkuet02.testejb.service;

import com.sunkuet02.testejb.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sun on 5/8/17.
 */

@Stateless
public class UserServiceImpls implements UserService{
    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public List<User> getAll() {
        Query query = entityManager.createQuery("select u from User u");
        return query.getResultList();
    }
}
