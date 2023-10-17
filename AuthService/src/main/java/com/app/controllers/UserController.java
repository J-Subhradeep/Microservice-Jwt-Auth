package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.UserCredential;
import com.app.repository.UserCredentialRepository;

@RestController
@RequestMapping("/auth/users")
public class UserController {
	
	@Autowired
	private UserCredentialRepository repo;
	
	@GetMapping("/getall")
	public ResponseEntity<List<UserCredential>> allUsers(){
		List<UserCredential> list = repo.findAll();
		return new ResponseEntity<List<UserCredential>>(list,HttpStatus.OK);
	}
}
