package com.sunkuet02.jpatest.dao;

import com.sunkuet02.jpatest.models.User;

import java.util.List;

/**
 * Created by sun on 3/13/17.
 */
public interface UserDao {

    List<User> findAllUser();

    User findUserByName(String username);

    void addUser(User user);
}
