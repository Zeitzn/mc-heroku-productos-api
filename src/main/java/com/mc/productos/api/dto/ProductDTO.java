package com.mc.productos.api.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private Integer id;
	
	private String name;
	
	private CategoryDTO category;
}
