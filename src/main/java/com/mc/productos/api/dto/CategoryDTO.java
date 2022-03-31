package com.mc.productos.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDTO {

	private Integer id;
	
	@NotEmpty(message = "Ingresa el nombre")
	@Size(min = 3, max = 20, message = "El nombre debe contener de {min} a {max} letras")
	private String name;
}
