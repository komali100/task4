package com.mindtree.ShoppingCartApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ShoppingCartApp.entities.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

}

