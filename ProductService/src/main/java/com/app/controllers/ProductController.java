package com.app.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Product;

import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/products")
public class ProductController {

	@GetMapping("/")
	public ResponseEntity<Product> getProduct(){
		Product p = new Product();
		p.setId(1);
		p.setName("Mobile");
		p.setPrice(10000);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
}
