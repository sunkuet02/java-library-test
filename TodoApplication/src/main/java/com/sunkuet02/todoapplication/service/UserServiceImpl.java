package com.sunkuet02.todoapplication.service;

import com.sunkuet02.todoapplication.dao.user.UserDao;
import com.sunkuet02.todoapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	public void deleteUser(int id) {

	}

	@Override
	public User findUser(String username) {
		return userDao.findUser(username);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}
}