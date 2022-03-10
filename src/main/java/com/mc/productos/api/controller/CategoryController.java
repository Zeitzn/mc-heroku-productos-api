package com.mc.productos.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.productos.api.dto.CategoryDTO;
import com.mc.productos.api.entity.Category;
import com.mc.productos.api.exceptions.ModelNotFoundException;
import com.mc.productos.api.service.ICategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private ICategoryService service;
	
	@Autowired
	private ModelMapper mapper;
		
	@PostMapping
	public ResponseEntity<CategoryDTO> register(@Valid @RequestBody CategoryDTO categoria){
		Category result = service.register(mapper.map(categoria, Category.class));
		return new ResponseEntity<CategoryDTO>(mapper.map(result, CategoryDTO.class), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO categoria){
		Category result = service.update(mapper.map(categoria, Category.class));
		return new ResponseEntity<CategoryDTO>(mapper.map(result, CategoryDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id){
		return new ResponseEntity<CategoryDTO>(mapper.map(service.findById(id), CategoryDTO.class), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<Category> list = service.findAll();
		return new ResponseEntity<List<CategoryDTO>>(list.stream().map(x->mapper.map(x, CategoryDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Integer id) throws ModelNotFoundException{
		Category category = service.findById(id);
		if(category==null) {
			throw new ModelNotFoundException("No se encontró categoría");
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("pagination")
	public ResponseEntity<Page<CategoryDTO>> findAll(Pageable page){
		Page<Category> result = service.findAll(page);
		List<CategoryDTO> listDto=result.getContent().stream().map(x->mapper.map(x, CategoryDTO.class)).collect(Collectors.toList());
		Page<CategoryDTO> finalResult = new PageImpl<>(listDto,page, listDto.size()); 
		return new ResponseEntity<Page<CategoryDTO>>(finalResult, HttpStatus.OK);
	}
}
