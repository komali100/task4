package com.mindtree.ShoppingCartApp.controller;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.ShoppingCartApp.entities.Product;
import com.mindtree.ShoppingCartApp.entities.StockQuantity;
import com.mindtree.ShoppingCartApp.entities.User;
import com.mindtree.ShoppingCartApp.exception.ServiceException;
import com.mindtree.ShoppingCartApp.exception.ShoppingCartException;
import com.mindtree.ShoppingCartApp.service.ProductService;
import com.mindtree.ShoppingCartApp.service.StockQuantityService;
import com.mindtree.ShoppingCartApp.service.UserCartService;
import com.mindtree.ShoppingCartApp.service.UserService;



@CrossOrigin
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserCartService userCartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private StockQuantityService stockQuantityService;

	@Autowired
	private UserService userService;

	@PostConstruct
	public void init() throws ServiceException {
		productService.saveAllProducts();
		userCartService.saveUsersCarts();
	}

	/*
	 * calling the user service function to get user by userId
	 */
	@CrossOrigin
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public User checkUserById(@PathVariable int userId) {
		User user = new User();
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			e.getMessage();
		}
		logger.info("Executed /shoppingCart/getUser/" + userId + " endpoint");
		return user;
	}

	/*
	 * calling the userCart service function to add user and cart
	 */
	@CrossOrigin
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUserCart(@RequestBody User user) {
		String message = new String();
		try {
			message = userCartService.saveUserCart(user);
		} catch (ServiceException e) {
			message ="You are not registered!!";
		}
		logger.info("Executed /ShoppingCart/saveUser endpoint");
		return message;
	}

	/*
	 * calling the product service function to get all products from database
	 */
	@CrossOrigin
	@GetMapping("/getAllProducts")
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		logger.debug("Entered into debug mode!!");
		try {
			products = productService.getAllProducts();
		} catch (ServiceException e) {
			logger.error("unable to retrieve data from product table in database!!");
			e.getMessage();
		}
		logger.debug("Exit from debug mode!!");
		logger.info("Executed /ShoppingCart/getAllProducts endpoint");
		return products;
	}

	/*
	 * calling the stockQuantity service function to insert data to stockQuantity
	 * table
	 */
	@CrossOrigin
	@PostMapping("/insert/stockQuantity/{userId}/{productId}")
	public String saveStockQuantity(@PathVariable int userId, @PathVariable int productId)
			throws ShoppingCartException {
		String message = new String();
		try {
			message = stockQuantityService.insertStockQuantity(userId, productId);
		} catch (ServiceException e) {
			message = "Oops!! Product is not added to the cart!! Try Again!!";
		}
		logger.info("Executed /shoppingCart/insert/stockQuantity/" + userId + "/" + productId + "endpoint");
		return message;
	}

	/*
	 * calling the stockQuantity service function to get all stock of particular
	 * user in cart from stockQuantity table
	 */
	@CrossOrigin
	@GetMapping("/get/StockQuantity/{userId}")
	public List<StockQuantity> getStockQuantityInCart(@PathVariable int userId) {
		logger.info("Executing /shoppingCart/get/stockQuantity/" + userId + "endpoint");
		List<StockQuantity> stocks = new ArrayList<StockQuantity>();
		try {
			stocks = stockQuantityService.getStockQuantityByCart(userId);
			if (stocks.size() == 0) {
				logger.warn("received zero result from stockQuantity table for user" + userId);
			}
		} catch (ServiceException e) {
			logger.error("unable to retrive data from stockQuantity tables");
			e.getMessage();
		}
		return stocks;
	}

	/*
	 * calling the stockQuantity service function to insert data to increase the
	 * quantity of stock for particular user in cart in stockQuantity table
	 */
	@CrossOrigin
	@PutMapping("/increase/stockQuantity/{userId}/{productId}")
	public String increaseStockQuantity(@PathVariable int userId, @PathVariable int productId) {
		logger.info("Execute /shoppingCart/increase/stockQuantity/" + userId + "/" + productId + "endpoint");
		String message = null;
		try {
			message = stockQuantityService.increaseStockQuantity(userId, productId);
		} catch (ServiceException e) {
			logger.error("unable to increase quantity of product-" + productId + " for user-" + userId
					+ " in cart from stockQuantity table in database");
			message = "Oops!! Product quantity is not increased!! Try Again!! ";
		}
		return message;
	}

	/*
	 * calling the stockQuantity service function to insert data to decrease the
	 * quantity of stock for particular user in cart in stockQuantity table
	 */
	@CrossOrigin
	@DeleteMapping("/decrease/stockQuantity/{userId}/{productId}")
	public String decreaseStockQuantity(@PathVariable int userId, @PathVariable int productId)
			throws ShoppingCartException {
		logger.info("Execute /shoppingCart/decrease/stockQuantity/" + userId + "/" + productId + "endpoint");
		String message = null;
		try {
			message = stockQuantityService.decreaseStockQuantity(userId, productId);
		} catch (ServiceException e) {
			logger.error("unable to decrease quantity of product-" + productId + "  for user-" + userId
					+ " in cart from stockQuantity table in database");
			message = "Oops!! Product quantity is not decreased!! Try Again!!";
		}
		return message;
	}

	/*
	 * calling the stockQuantity service function to delete all stock for particular
	 * user in cart from stockQuantity table
	 */
	@CrossOrigin
	@DeleteMapping("/delete/stockQuantity/{userId}")
	public String deleteAllStockQuantity(@PathVariable int userId) {
		logger.info("Execute /shoppingCart/delete/stockQuantity/" + userId + "endpoint");
		String message = null;
		try {
			message = stockQuantityService.deleteAllStockQuantity(userId);
		} catch (ServiceException e) {
			logger.error(
					"unable to delete products for user-" + userId + " in cart from stockQuantity table in database");
			message = "Oops!! Products are not removed from the cart!! Try Again!!";
		}
		return message;
	}

}
