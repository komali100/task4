package com.mindtree.ShoppingCartApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ShoppingCartApp.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
