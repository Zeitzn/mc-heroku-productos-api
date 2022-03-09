package com.mc.productos.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Ingresa el nombre")
	@Size(min = 3, max = 20, message = "El nombre debe contener de {min} a {max} letras")
	@Column(name="name", length = 20)
	private String name;
	
	@NotNull(message = "Selecciona la categoría")
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false, foreignKey = @ForeignKey(name="producto_categoria_id"))
	private Category category;
}
