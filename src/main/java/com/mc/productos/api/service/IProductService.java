package com.mc.productos.api.service;

import java.util.List;

import com.mc.productos.api.commons.ICrudService;
import com.mc.productos.api.dto.ProductConsolidationDTO;
import com.mc.productos.api.entity.Product;

public interface IProductService extends ICrudService<Product, Integer> {

	List<Product> search(String name);
	
	List<Product> findExpired();
	
	List<Product> findByCategory(Integer categoryId);
	
	List<ProductConsolidationDTO> consolidation();
}
