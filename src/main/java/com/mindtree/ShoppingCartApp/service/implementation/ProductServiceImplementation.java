package com.mindtree.ShoppingCartApp.service.implementation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.ShoppingCartApp.dao.ProductDao;
import com.mindtree.ShoppingCartApp.databasedata.DatabaseData;
import com.mindtree.ShoppingCartApp.entities.Apparel;
import com.mindtree.ShoppingCartApp.entities.Book;
import com.mindtree.ShoppingCartApp.entities.Product;
import com.mindtree.ShoppingCartApp.exception.ServiceException;
import com.mindtree.ShoppingCartApp.service.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImplementation implements ProductService{
	private DatabaseData data = new DatabaseData();

	@Autowired
	private ProductDao productRepository;

	/*
	 * calling the save function of product in service to save all books and
	 * apparels data in product table
	 */
	@Override
	@Transactional
	public void saveAllProducts() throws ServiceException {
		try {
			List<Book> books = data.getBookData();
			for (Book book : books) {
				productRepository.save(book);
			}
			List<Apparel> apparels = data.getApparalData();
			for (Apparel apparel : apparels) {
				productRepository.save(apparel);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * calling the get all data from product data function in service
	 */
	@Override
	@Transactional
	public List<Product> getAllProducts() throws ServiceException {
		List<Product> products = new ArrayList<>();
		try {
			products = productRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return products;
	}

}
