package com.mc.productos.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.mc.productos.api.dto.RoleDTO;
import com.mc.productos.api.entity.Role;
import com.mc.productos.api.exceptions.ResourceNotFoundException;
import com.mc.productos.api.service.IRoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private IRoleService service;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<RoleDTO> register(@Valid @RequestBody RoleDTO role) {
		Role result = service.register(mapper.map(role, Role.class));
		return new ResponseEntity<>(mapper.map(result, RoleDTO.class), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO role) {
		Role result = service.update(mapper.map(role, Role.class));
		return new ResponseEntity<>(mapper.map(result, RoleDTO.class), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> findById(@PathVariable("id") Integer id) {
		Role role = service.findById(id);
		RoleDTO result = null;
		if (role != null) {
			result = mapper.map(role, RoleDTO.class);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RoleDTO>> findAll() {
		List<Role> list = service.findAll();
		return new ResponseEntity<>(list.stream().map(x -> mapper.map(x, RoleDTO.class)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoleDTO> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Role role = service.findById(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role", "id", id);
		}
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
