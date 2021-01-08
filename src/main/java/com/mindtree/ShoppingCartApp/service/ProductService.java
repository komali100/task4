package com.mindtree.ShoppingCartApp.service;

import java.util.List;



import com.mindtree.ShoppingCartApp.entities.Product;
import com.mindtree.ShoppingCartApp.exception.ServiceException;



public interface ProductService {

	public void saveAllProducts() throws ServiceException;

	public List<Product> getAllProducts() throws ServiceException;

}
