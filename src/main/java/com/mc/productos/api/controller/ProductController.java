package com.mc.productos.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.productos.api.dto.ProductConsolidationDTO;
import com.mc.productos.api.dto.ProductDTO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.entity.Product;
import com.mc.productos.api.exceptions.ResourceNotFoundException;
import com.mc.productos.api.service.ICategoryService;
import com.mc.productos.api.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private IProductService service;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<ProductDTO> register(@Valid @RequestBody ProductDTO product) {
		Category category = categoryService.findById(product.getCategory().getId());
		if (category == null) {
			throw new ResourceNotFoundException("Category", "id", product.getCategory().getId());
		}
		Product result = service.register(mapper.map(product, Product.class));
		return new ResponseEntity<>(mapper.map(result, ProductDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO product) {
		Category category = categoryService.findById(product.getCategory().getId());
		if (category == null) {
			throw new ResourceNotFoundException("Category", "id", product.getCategory().getId());
		}
		Product result = service.update(mapper.map(product, Product.class));
		return new ResponseEntity<>(mapper.map(result, ProductDTO.class), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) {
		Product product = service.findById(id);
		ProductDTO result = null;
		if (product != null) {
			result = mapper.map(product, ProductDTO.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> list = service.findAll();
		return new ResponseEntity<>(
				list.stream().map(x -> mapper.map(x, ProductDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Product product = service.findById(id);
		if (product == null) {
			throw new ResourceNotFoundException("Product", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("pagination")
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable page) {
		Page<Product> result = service.findAll(page);
		List<ProductDTO> listDto = result.getContent().stream().map(x -> mapper.map(x, ProductDTO.class))
				.collect(Collectors.toList());
		Page<ProductDTO> finalResult = new PageImpl<>(listDto, page, listDto.size());
		return new ResponseEntity<>(finalResult, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> search(@RequestParam("name") String name) {
		List<ProductDTO> list = service.search(name).stream().map(x -> mapper.map(x, ProductDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/expired")
	public ResponseEntity<List<ProductDTO>> search() {
		List<ProductDTO> list = service.findExpired().stream().map(x -> mapper.map(x, ProductDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<ProductDTO> list = service.findByCategory(categoryId).stream().map(x -> mapper.map(x, ProductDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/consolidation")
	public ResponseEntity<List<ProductConsolidationDTO>> consolidation() {
		List<ProductConsolidationDTO> list = service.consolidation();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/hateoas/{id}")
	public EntityModel<ProductDTO> listHateoas(@PathVariable("id") Integer id) throws ResourceNotFoundException {

		Product obj = service.findById(id);
		if (obj == null) {
			throw new ResourceNotFoundException("Product", "id", id);
		}
		ProductDTO dto = mapper.map(obj, ProductDTO.class);

		EntityModel<ProductDTO> resource = EntityModel.of(dto);
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(CategoryController.class).findById(obj.getCategory().getId()));
		resource.add(link1.withRel("product-link"));
		resource.add(link2.withRel("category-link"));
		return resource;
	}
}
