package com.mc.productos.api.dao;

import org.springframework.stereotype.Repository;

import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.entity.Product;

@Repository
public interface IProductDAO extends IGenericDAO<Product, Integer> {

}
