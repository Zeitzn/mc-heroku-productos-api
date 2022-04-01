package com.mc.productos.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RoleDTO {
	
	private Integer id;

	@NotEmpty(message = "Ingresa el nombre del rol")
	private String name;
}
