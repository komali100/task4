package com.mindtree.ShoppingCartApp.service;

import com.mindtree.ShoppingCartApp.entities.User;
import com.mindtree.ShoppingCartApp.exception.ServiceException;

public interface UserCartService {

	public void saveUsersCarts() throws ServiceException;

	public String saveUserCart(User user) throws ServiceException;

}
