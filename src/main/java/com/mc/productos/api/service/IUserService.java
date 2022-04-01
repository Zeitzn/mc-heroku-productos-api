package com.mc.productos.api.service;

import com.mc.productos.api.commons.ICRUDService;
import com.mc.productos.api.entity.User;

public interface IUserService extends ICRUDService<User, Integer> {

	User findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
