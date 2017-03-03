package com.sunkuet02.todoapplication.dao.user;

import com.sunkuet02.todoapplication.models.User;

/**
 * Created by sun on 2/28/17.
 */
public interface UserDao {
    int addUser(User user);

    User findUser(String username);
}
