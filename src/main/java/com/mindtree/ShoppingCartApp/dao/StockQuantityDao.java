package com.mindtree.ShoppingCartApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ShoppingCartApp.entities.StockQuantity;

@Repository
public interface StockQuantityDao extends JpaRepository<StockQuantity, Integer> {

}
