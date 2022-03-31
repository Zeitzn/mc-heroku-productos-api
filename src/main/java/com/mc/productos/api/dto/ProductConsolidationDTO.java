package com.mc.productos.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductConsolidationDTO {
	private CategoryDTO category;
	private List<ProductDTO> products;
}
