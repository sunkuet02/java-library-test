package com.sunkuet02.todoapplication.DAO.user;

import com.sunkuet02.todoapplication.Models.User;

/**
 * Created by sun on 2/28/17.
 */
public interface UserDao {
    void addUser(User user);

    User findUser(String username);
}
