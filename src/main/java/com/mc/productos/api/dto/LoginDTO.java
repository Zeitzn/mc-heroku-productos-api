package com.mc.productos.api.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private String usernameOrEmail;
	private String password;
}
