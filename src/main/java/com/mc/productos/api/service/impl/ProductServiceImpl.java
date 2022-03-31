package com.mc.productos.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.productos.api.commons.CRUDImpl;
import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.dao.IProductDAO;
import com.mc.productos.api.dto.CategoryDTO;
import com.mc.productos.api.dto.ProductConsolidationDTO;
import com.mc.productos.api.dto.ProductDTO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.entity.Product;
import com.mc.productos.api.service.ICategoryService;
import com.mc.productos.api.service.IProductService;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

	@Autowired
	private IProductDAO dao;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ModelMapper mapper;

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

	@Override
	public List<ProductConsolidationDTO> consolidation() {
		List<Category> categories = categoryService.findAll();
		List<ProductConsolidationDTO> consolidations = new ArrayList<>();
		if(!categories.isEmpty()) {
			consolidations = categories.stream().map(c -> {
				List<Product> products = dao.findByCategory(c);
				return ProductConsolidationDTO.builder().category(mapper.map(c, CategoryDTO.class))
						.products(products.stream().map(p -> mapper.map(p, ProductDTO.class)).collect(Collectors.toList()))
						.build();
			}).collect(Collectors.toList());
		}
		return consolidations;
	}

}
