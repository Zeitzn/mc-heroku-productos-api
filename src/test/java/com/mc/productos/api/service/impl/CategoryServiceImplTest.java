package com.mc.productos.api.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mc.productos.api.dao.ICategoryDAO;
import com.mc.productos.api.entity.Category;

class CategoryServiceImplTest {
		
	
	ICategoryDAO categoryDaoMock= Mockito.mock(ICategoryDAO.class);
	
//	ICategoryService categoryServiceMock= Mockito.mock(ICategoryService.class);

	@Test
	void save() {		
		Category mockCategory = Category.builder()
				.id(1)
				.name("Category 1")
				.build();		
		Mockito.when(categoryDaoMock.save(mockCategory)).thenReturn(mockCategory);		
	}
	

}
