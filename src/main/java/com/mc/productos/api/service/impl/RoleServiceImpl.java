package com.mc.productos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.productos.api.commons.CRUDImpl;
import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.dao.IRoleDAO;
import com.mc.productos.api.entity.Role;
import com.mc.productos.api.service.IRoleService;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

	@Autowired
	private IRoleDAO dao;

	@Override
	protected IGenericDAO<Role, Integer> dao() {
		return dao;
	}

	@Override
	public Role findByname(String name) {
		return dao.findByName(name);
	}

}
