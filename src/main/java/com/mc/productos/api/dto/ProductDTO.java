package com.mc.productos.api.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductDTO {

	private Integer id;
	
	@NotEmpty(message = "Ingresa el nombre del producto")
	@Size(min = 3, max = 20, message = "El nombre debe contener de {min} a {max} letras")
	private String name;
	
	@NotEmpty(message = "Ingresa la descripción del producto")
	@Size(min = 20, max = 200, message = "La descripción debe contener de {min} a {max} letras")
	private String description;
	
	@NotNull(message="Ingresa la fecha de expiración")
	private Date expirationDate;
	
	@NotNull(message = "Selecciona la categoría del producto")
	private CategoryDTO category;
}
