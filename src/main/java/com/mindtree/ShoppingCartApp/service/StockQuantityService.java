package com.mindtree.ShoppingCartApp.service;

import java.util.List;

import com.mindtree.ShoppingCartApp.entities.StockQuantity;
import com.mindtree.ShoppingCartApp.exception.ServiceException;




public interface StockQuantityService {

	public String insertStockQuantity(int userId, int productId) throws ServiceException;

	public List<StockQuantity> getStockQuantityByCart(int userId) throws ServiceException;

	public String increaseStockQuantity(int userId, int productId) throws ServiceException;

	public String decreaseStockQuantity(int userId, int productId) throws ServiceException;

	public String deleteAllStockQuantity(int userId) throws ServiceException;

}

