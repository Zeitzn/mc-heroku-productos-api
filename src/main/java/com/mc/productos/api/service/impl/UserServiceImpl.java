package com.mc.productos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.productos.api.commons.CRUDImpl;
import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.dao.IUserDAO;
import com.mc.productos.api.entity.User;
import com.mc.productos.api.service.IUserService;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

	@Autowired
	private IUserDAO dao;

	@Override
	protected IGenericDAO<User, Integer> dao() {
		return dao;
	}

	@Override
	public boolean existsByUsername(String username) {
		return dao.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return dao.existsByEmail(email);
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		return dao.findByUsernameOrEmail(username, email);
	}

}
