package com.sunkuet02.spring.dao;

import com.sunkuet02.spring.models.User;

import java.util.List;

/**
 * Created by sun on 2/22/17.
 */
public interface UserDao {
    List<User> findAllUsers();

    User getUser(int id);

    int addUser(User user);

    void deleteUserById(int id);
}
