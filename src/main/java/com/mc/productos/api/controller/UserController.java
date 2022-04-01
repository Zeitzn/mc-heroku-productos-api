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

import com.mc.productos.api.dto.UserDTO;
import com.mc.productos.api.entity.User;
import com.mc.productos.api.exceptions.ResourceNotFoundException;
import com.mc.productos.api.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO user) {
		User result = service.register(mapper.map(user, User.class));
		return new ResponseEntity<>(mapper.map(result, UserDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) {
		User result = service.update(mapper.map(user, User.class));
		return new ResponseEntity<>(mapper.map(result, UserDTO.class), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Integer id) {
		User user = service.findById(id);
		UserDTO result = null;
		if (user != null) {
			result = mapper.map(user, UserDTO.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		return new ResponseEntity<>(list.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		User user = service.findById(id);
		if (user == null) {
			throw new ResourceNotFoundException("User", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("pagination")
	public ResponseEntity<Page<UserDTO>> findAll(Pageable page) {
		Page<User> result = service.findAll(page);
		List<UserDTO> listDto = result.getContent().stream().map(x -> mapper.map(x, UserDTO.class))
				.collect(Collectors.toList());
		Page<UserDTO> finalResult = new PageImpl<>(listDto, page, listDto.size());
		return new ResponseEntity<>(finalResult, HttpStatus.OK);
	}
}
