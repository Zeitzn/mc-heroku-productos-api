package com.mc.productos.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.productos.api.commons.CRUDImpl;
import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.dao.IProductDAO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.entity.Product;
import com.mc.productos.api.service.IProductService;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

	@Autowired
	private IProductDAO dao;

	@Override
	protected IGenericDAO<Product, Integer> dao() {
		return dao;
	}

	@Override
	public List<Product> search(String name) {		
		return dao.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<Product> findExpired() {
		return dao.findByExpirationDateBefore(new Date());
	}

	@Override
	public List<Product> findByCategory(Integer categoryId) {
		return dao.findByCategory(Category.builder().id(categoryId).build());
	}
	
}
