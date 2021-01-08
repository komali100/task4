package com.mindtree.ShoppingCartApp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.ShoppingCartApp.dao.CartDao;
import com.mindtree.ShoppingCartApp.dao.ProductDao;
import com.mindtree.ShoppingCartApp.dao.StockQuantityDao;
import com.mindtree.ShoppingCartApp.entities.Cart;
import com.mindtree.ShoppingCartApp.entities.Product;
import com.mindtree.ShoppingCartApp.entities.StockQuantity;
import com.mindtree.ShoppingCartApp.exception.RecordNotFoundException;
import com.mindtree.ShoppingCartApp.exception.ServiceException;
import com.mindtree.ShoppingCartApp.service.StockQuantityService;

@Service
@Transactional
public class StockQuantityServiceImplementation  implements StockQuantityService {
	@Autowired
	private CartDao cartRepository;

	@Autowired
	private ProductDao productRepository;

	@Autowired
	private StockQuantityDao stockQuantityRepository;

	/*
	 * method to insert data to stockQuantity table
	 */
	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class, propagation = Propagation.REQUIRED)
	public String insertStockQuantity(int userId, int productId) throws ServiceException {
		String message = null;
		try {
			List<Cart> cartList = cartRepository.findAll();
			Cart cart = cartList.stream().filter(cart1 -> cart1.getUser().getUserId() == userId)
					.collect(Collectors.toList()).get(0);
			if (cart != null) {
				List<Product> productList = productRepository.findAll();
				Product product = productList.stream().filter(product1 -> product1.getProductId() == productId)
						.collect(Collectors.toList()).get(0);
				StockQuantity stock = new StockQuantity();
				List<StockQuantity> stockList = stockQuantityRepository.findAll();
				List<StockQuantity> stockListHavingCartId = stockList.stream()
						.filter(stock1 -> stock1.getCart().getCartId() == cart.getCartId())
						.collect(Collectors.toList());
				if (stockListHavingCartId.size() == 0) {
					stock.setCart(cart);
					stock.setProduct(product);
					stock.setProductQuantity(1);
					stock.setProductTotalPrice(product.getProductPrice());
					stockQuantityRepository.save(stock);
					message = "product added to the cart!!";
				} else {
					message = insertOnStockQuantity(productId, message, cart, product, stock, stockList);
				}
			} else {
				throw new RecordNotFoundException("cart does not exist!!");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return message;
	}

	/*
	 * sub-method to insert data to stockQuantity table
	 */
	private String insertOnStockQuantity(int productId, String message, Cart cart, Product product, StockQuantity stock,
			List<StockQuantity> stockList) throws ServiceException {
		List<StockQuantity> stockListHavingProductId = stockList.stream()
				.filter(stock1 -> stock1.getProduct().getProductId() == productId
						&& stock1.getCart().getCartId() == cart.getCartId())
				.collect(Collectors.toList());
		if (stockListHavingProductId.size() == 0) {
			stock.setCart(cart);
			stock.setProduct(product);
			stock.setProductQuantity(1);
			stock.setProductTotalPrice(product.getProductPrice());
			stockQuantityRepository.save(stock);
			message = "product added to the cart!!";

		} else {
			for (StockQuantity stock1 : stockList) {
				if (stock1.getProduct().getProductId() == productId
						&& stock1.getCart().getCartId() == cart.getCartId()) {
					int quantity = stock1.getProductQuantity() + 1;
					stock1.setProductQuantity(quantity);
					stock1.setProductTotalPrice(stock1.getProduct().getProductPrice() * quantity);
					stockQuantityRepository.save(stock1);
					message = "product added to the cart!!";
				}
			}
		}
		return message;
	}

	/*
	 * method to get all stocks from stockQuantity table for particular user
	 */
	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class, propagation = Propagation.REQUIRED)
	public List<StockQuantity> getStockQuantityByCart(int userId) throws ServiceException {
		List<StockQuantity> stocks = new ArrayList<StockQuantity>();
		try {
			List<Cart> cartList = cartRepository.findAll();
			Cart cart = cartList.stream().filter(cart1 -> cart1.getUser().getUserId() == userId)
					.collect(Collectors.toList()).get(0);
			List<StockQuantity> stockList = stockQuantityRepository.findAll();
			if (stockList != null) {
				stocks = stockList.stream().filter(stock -> stock.getCart().getCartId() == cart.getCartId())
						.collect(Collectors.toList());
			} else {
				throw new RecordNotFoundException("cart is empty!! Add product to the cart!!");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return stocks;

	}

	/*
	 * method to increase the quantity of stock in stockQuantity table of particular
	 * user
	 */
	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class, propagation = Propagation.REQUIRES_NEW)
	public String increaseStockQuantity(int userId, int productId) throws ServiceException {
		String message = null;
		try {
			List<Cart> cartList = cartRepository.findAll();
			Cart cart = cartList.stream().filter(cart1 -> cart1.getUser().getUserId() == userId)
					.collect(Collectors.toList()).get(0);
			List<StockQuantity> stockList = stockQuantityRepository.findAll();
			if (stockList != null) {
				List<StockQuantity> stockListHavingCartId = stockList.stream()
						.filter(stock -> stock.getCart().getCartId() == cart.getCartId()).collect(Collectors.toList());
				for (StockQuantity stock1 : stockListHavingCartId) {
					if (stock1.getProduct().getProductId() == productId) {
						int quantity = stock1.getProductQuantity() + 1;
						stock1.setProductQuantity(quantity);
						stock1.setProductTotalPrice(stock1.getProduct().getProductPrice() * quantity);
						//message = stockQuantityRepository.update(stock1);
						 stockQuantityRepository.save(stock1);
						 message = "product quantity is increased!!";
					}
				}
			} else {
				throw new RecordNotFoundException("cart is empty!! Add product to the cart!!");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return message;
	}

	/*
	 * method to decrease the quantity of stock in stockQuantity table of particular
	 * user
	 */
	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class, propagation = Propagation.REQUIRES_NEW)
	public String decreaseStockQuantity(int userId, int productId) throws ServiceException {
		String message = null;
		try {
			List<Cart> cartList = cartRepository.findAll();
			Cart cart = cartList.stream().filter(cart1 -> cart1.getUser().getUserId() == userId)
					.collect(Collectors.toList()).get(0);
			List<StockQuantity> stockList = stockQuantityRepository.findAll();
			if (stockList != null) {
				List<StockQuantity> stockListHavingCartId = stockList.stream()
						.filter(stock -> stock.getCart().getCartId() == cart.getCartId()).collect(Collectors.toList());
				for (StockQuantity stock1 : stockListHavingCartId) {
					if (stock1.getProduct().getProductId() == productId) {
						int quantity = stock1.getProductQuantity() - 1;
						stock1.setProductQuantity(quantity);
						stock1.setProductTotalPrice(stock1.getProduct().getProductPrice() * quantity);
					//	message = stockQuantityRepository.update(stock1);
						 stockQuantityRepository.save(stock1);
						
						if (stock1.getProductQuantity() == 0) {
							 stockQuantityRepository.delete(stock1);
						}
						 message = "product quantity is decreased!!";
					}
				}
			} else {
				throw new RecordNotFoundException("cart is empty!! Add Product to the cart!!");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return message;
	}

	/*
	 * method to delete all stocks from stockQuantity table of particular user
	 */
	@Override
	@Transactional(rollbackFor = RecordNotFoundException.class, propagation = Propagation.REQUIRED)
	public String deleteAllStockQuantity(int userId) throws ServiceException {
		String message = null;
		try {
			List<Cart> cartList = cartRepository.findAll();
			Cart cart = cartList.stream().filter(cart1 -> cart1.getUser().getUserId() == userId)
					.collect(Collectors.toList()).get(0);
			List<StockQuantity> stockList = stockQuantityRepository.findAll();
			if (stockList != null) {
				List<StockQuantity> stockListHavingCartId = stockList.stream()
						.filter(stock -> stock.getCart().getCartId() == cart.getCartId()).collect(Collectors.toList());
				for (StockQuantity stock1 : stockListHavingCartId) {
					if (stock1.getCart().getCartId() == cart.getCartId()) {
						 stockQuantityRepository.delete(stock1);
					}
				}
				 message = "cart is cleared!!";
			} else {
				throw new RecordNotFoundException("cart is empty!!");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return message;
	}

}
