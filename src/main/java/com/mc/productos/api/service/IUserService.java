package com.mc.productos.api.service;

import com.mc.productos.api.commons.ICrudService;
import com.mc.productos.api.entity.User;

public interface IUserService extends ICrudService<User, Integer> {

	User findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
