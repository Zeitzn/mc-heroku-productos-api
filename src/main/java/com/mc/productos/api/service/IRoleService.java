package com.mc.productos.api.service;

import com.mc.productos.api.commons.ICRUDService;
import com.mc.productos.api.entity.Role;

public interface IRoleService extends ICRUDService<Role, Integer> {
	
	Role findByname(String name);
	
}
