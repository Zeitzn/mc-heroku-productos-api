package com.mc.productos.api.service;

import com.mc.productos.api.commons.ICrudService;
import com.mc.productos.api.entity.Role;

public interface IRoleService extends ICrudService<Role, Integer> {
	
	Role findByname(String name);
	
}
