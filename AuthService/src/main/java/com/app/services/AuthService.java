package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.UserCredential;
import com.app.repository.UserCredentialRepository;

import io.jsonwebtoken.Claims;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public String saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "User added to the system";
		
	}
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	
	public String validateToken(String token) {
		return jwtService.validateToken(token);
		
	}
}
