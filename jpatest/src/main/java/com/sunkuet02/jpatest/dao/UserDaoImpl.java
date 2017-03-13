package com.sunkuet02.jpatest.dao;

import com.sunkuet02.jpatest.models.User;
import com.sunkuet02.jpatest.utils.DbConnection;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sun on 3/13/17.
 */

public class UserDaoImpl implements UserDao {

    public static EntityManager entityManager;

    public UserDaoImpl() {
        entityManager = DbConnection.getEntityManager();
    }

    public List<User> findAllUser() {
        String sqlQuery = "SELECT u FROM User u";
        return entityManager.createQuery(sqlQuery, User.class).getResultList();
    }

    public User findUserByName(String username) {
        String sql = "SELECT u FROM User u WHERE u.username=:username";
        Query query = entityManager.createQuery(sql, User.class);
        query.setParameter("username", username);

        List<User> users = query.getResultList();

        return users.size() == 0 ? null: users.get(0);
    }

    public void addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
