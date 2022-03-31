package com.mc.productos.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length = 20)
	private String name;
	
	@Column(name="description",length = 200, nullable = false)
	private String description;
	
	@Column(name="expiration_date", nullable = false)
	private Date expirationDate;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false, foreignKey = @ForeignKey(name="producto_categoria_id"))
	private Category category;
}
