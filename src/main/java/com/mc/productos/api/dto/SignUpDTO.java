package com.mc.productos.api.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignUpDTO {
	@NotEmpty(message = "Ingresa el nombre del usuario")
	private String firstName;

	@NotEmpty(message = "Ingresa los apellidos del usuario")
	private String lastName;

	@NotEmpty(message = "Ingresa el username")
	private String username;

	@NotEmpty(message = "Ingresa el correo electrónico")
	private String email;

	@NotEmpty(message = "Ingresa la contraseña")
	private String password;

	private Set<RoleDTO> roles;
}
