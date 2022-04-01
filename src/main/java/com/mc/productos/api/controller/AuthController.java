package com.mc.productos.api.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.productos.api.dto.JWTAuthResponse;
import com.mc.productos.api.dto.LoginDTO;
import com.mc.productos.api.dto.SignUpDTO;
import com.mc.productos.api.entity.Role;
import com.mc.productos.api.entity.User;
import com.mc.productos.api.security.JwtTokenProvider;
import com.mc.productos.api.service.IRoleService;
import com.mc.productos.api.service.IUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService service;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDTO loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// get token form tokenProvider
		String token = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTAuthResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignUpDTO request) {
		if (service.existsByUsername(request.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		if (service.existsByEmail(request.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		User user = mapper.map(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		Set<Role> roles = user.getRoles().stream().map(x -> roleService.findByname(x.getName()))
				.collect(Collectors.toSet());
		user.setRoles(roles);

		service.register(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}
}
