package com.mindtree.ShoppingCartApp.service;

import com.mindtree.ShoppingCartApp.entities.User;
import com.mindtree.ShoppingCartApp.exception.ServiceException;

public interface UserService {

	
	public User getUserById(int userId) throws ServiceException;
}
