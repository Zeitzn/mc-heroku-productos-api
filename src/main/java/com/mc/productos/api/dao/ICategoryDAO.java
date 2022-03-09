package com.mc.productos.api.dao;

import org.springframework.stereotype.Repository;

import com.mc.productos.api.commons.IGenericDAO;
import com.mc.productos.api.entity.Category;

@Repository
public interface ICategoryDAO extends IGenericDAO<Category, Integer> {

}
