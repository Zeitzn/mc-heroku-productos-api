package com.mc.productos.api.dao;

import org.springframework.stereotype.Repository;

import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.entity.Role;

@Repository
public interface IRoleDAO extends IGenericDAO<Role, Integer> {
	Role findByName(String name);
}
