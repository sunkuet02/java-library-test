package com.sunkuet02.spring.service;

import com.sunkuet02.spring.models.User;

import java.util.List;

public interface UserService {

	User findUserById(int id);

	int addUser(User user);

	List<User> getAllUsers();

	void deleteUser(int id);
}