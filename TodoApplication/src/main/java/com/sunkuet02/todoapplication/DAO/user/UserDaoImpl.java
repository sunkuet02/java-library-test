package com.sunkuet02.todoapplication.DAO.user;

import com.sunkuet02.todoapplication.Models.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by sun on 2/28/17.
 */
public class UserDaoImpl implements UserDao {
    final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB");
    final static
    @Override
    public void addUser(User user) {

    }

    @Override
    public User findUser(String username) {
        return null;
    }
}
