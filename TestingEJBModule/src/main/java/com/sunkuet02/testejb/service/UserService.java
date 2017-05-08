package com.sunkuet02.testejb.service;

import com.sunkuet02.testejb.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by sun on 5/8/17.
 */
@Local
public interface UserService {
    void addUser(User user);

    List<User> getAll();
}
