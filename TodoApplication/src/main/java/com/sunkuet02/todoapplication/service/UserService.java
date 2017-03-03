package com.sunkuet02.todoapplication.service;

import com.sunkuet02.todoapplication.models.User;

import java.util.List;

public interface UserService {

	User findUser(String username);

	int addUser(User user);

	List<User> getAllUsers();

	void deleteUser(int id);
}