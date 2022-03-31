package com.mc.productos.api.service;

import java.util.List;

import com.mc.productos.api.commons.ICRUDService;
import com.mc.productos.api.entity.Product;

public interface IProductService extends ICRUDService<Product, Integer> {

	List<Product> search(String name);
	
	List<Product> findExpired();
	
	List<Product> findByCategory(Integer categoryId);
}
