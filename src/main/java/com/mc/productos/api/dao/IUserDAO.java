package com.mc.productos.api.dao;

import org.springframework.stereotype.Repository;

import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.entity.User;

@Repository
public interface IUserDAO extends IGenericDAO<User, Integer> {

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User findByUsernameOrEmail(String username, String email);
}
