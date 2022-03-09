package com.mc.productos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.productos.api.commons.CRUDImpl;
import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.dao.ICategoryDAO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.service.ICategoryService;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {
	
	@Autowired
	private ICategoryDAO dao;

	@Override
	protected IGenericDAO<Category, Integer> dao() {
		return dao;
	}

}
