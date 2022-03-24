package com.mc.productos.api.exceptions;

public class ModelNotFoundException extends Exception {
	
	private static final long serialVersionUID = 7327620365739075679L;

	public ModelNotFoundException(String msg) {
		super(msg);
	}
}