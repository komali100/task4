package com.mindtree.ShoppingCartApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ShoppingCartApp.entities.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

}
