package com.mc.productos.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private Date expirationDate;
	
	private CategoryDTO category;
}
