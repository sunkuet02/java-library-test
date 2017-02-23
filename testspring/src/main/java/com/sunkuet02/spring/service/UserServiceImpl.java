package com.sunkuet02.spring.service;

import com.sunkuet02.spring.dao.UserDao;
import com.sunkuet02.spring.models.User;
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
	public User findUserById(int id) {
		return userDao.getUser(id);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUserById(id);
	}
}