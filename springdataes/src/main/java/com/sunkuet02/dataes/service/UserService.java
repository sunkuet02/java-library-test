package com.sunkuet02.dataes.service;

import com.sunkuet02.dataes.model.User;

import java.util.List;

/**
 * Created by sun on 6/7/17.
 */
public interface UserService {

    User save(User user);

    void delete(User user);

    User findOne(String id);

    List<User> searchByName(String name);

    List<User> findAll();
}
