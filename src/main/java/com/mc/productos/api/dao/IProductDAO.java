package com.mc.productos.api.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.entity.Product;

@Repository
public interface IProductDAO extends IGenericDAO<Product, Integer> {

	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByExpirationDateBefore(Date nowDate);
	
	List<Product> findByCategory(Category category);
}
