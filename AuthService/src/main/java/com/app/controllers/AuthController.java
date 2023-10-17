package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.entities.UserCredential;
import com.app.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@Autowired
	private AuthService authService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/register")
	public String addUser(@RequestBody UserCredential user) {
		return authService.saveUser(user);
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest userCredential) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(),userCredential.getPassword()));
		if(authenticate.isAuthenticated())
		return authService.generateToken(userCredential.getUsername());
		return "Invalid Credentials";
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		String validateTokenn = authService.validateToken(token);
		return validateTokenn;
	}
}
