package com.sunkuet02.dataes.service;

import com.sunkuet02.dataes.dao.UserRepository;
import com.sunkuet02.dataes.model.User;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 6/7/17.
 */

@Service
public class UserServiceImpls implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> searchByName(String name) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name.raw", name);
        return iterableToListUser(userRepository.search(queryBuilder));
    }

    @Override
    public List<User> findAll() {
        return iterableToListUser(userRepository.findAll());
    }

    private List<User> iterableToListUser(Iterable<User> users) {
        List<User> usersToReturn = new ArrayList<User>();
        for(User user : users) {
            usersToReturn.add(user);
        }

        return usersToReturn;
    }
}
