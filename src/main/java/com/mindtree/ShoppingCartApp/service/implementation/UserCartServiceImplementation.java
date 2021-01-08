package com.mindtree.ShoppingCartApp.service.implementation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.ShoppingCartApp.dao.CartDao;
import com.mindtree.ShoppingCartApp.dao.UserDao;
import com.mindtree.ShoppingCartApp.databasedata.DatabaseData;
import com.mindtree.ShoppingCartApp.entities.Cart;
import com.mindtree.ShoppingCartApp.entities.User;
import com.mindtree.ShoppingCartApp.exception.ServiceException;
import com.mindtree.ShoppingCartApp.service.UserCartService;

@Service
@Transactional
public class UserCartServiceImplementation implements UserCartService {

	private DatabaseData data = new DatabaseData();

	@Autowired
	private CartDao cartRepository;

	@Autowired
	private UserDao userRepository;

	/*
	 * calling the save function of userCart to service to save data in user and
	 * cart table
	 */
	@Override
	@Transactional
	public void saveUsersCarts() throws ServiceException {
		try {
			List<User> users = data.getUserData();
			for (User user : users) {
				userRepository.save(user);
			}
			List<Cart> carts = data.getCartData();
			for (Cart cart : carts) {
				cartRepository.save(cart);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/*
	 * method to add user and cart and save to database in user and cart table
	 */
	@Override
	@Transactional
	public String saveUserCart(User user) throws ServiceException {
		String message = null;
		try {
			 userRepository.save(user);
			Cart cart = new Cart();
			cart.setCartId(user.getUserId());
			cart.setUser(user);
			cartRepository.save(cart);
			message="cart is added!!";
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return message;
	}

}
