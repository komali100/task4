package com.mindtree.ShoppingCartApp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.ShoppingCartApp.dao.UserDao;
import com.mindtree.ShoppingCartApp.entities.User;
import com.mindtree.ShoppingCartApp.exception.ServiceException;
import com.mindtree.ShoppingCartApp.service.UserService;
@Service
@Transactional(readOnly = true)
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserDao userRepository;

	/*
	 * method to get user by userId
	 */
	@Override
	@Transactional
	public User getUserById(int userId) throws ServiceException {
		List<User> userList = new ArrayList<>();
		User user = new User();
		try {
			userList = userRepository.findAll();
			user = userList.stream().filter(user1 -> user1.getUserId() == userId).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			throw new ServiceException("user does not exist", e);
		}
		return user;
	}
}
