package com.cybertek.controller;

import java.awt.font.MultipleMaster;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybertek.model.Product;
import com.cybertek.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Set<Product>> delete(@PathVariable("id") Long id) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Delete");

		Set<Product> set = productService.delete(id);
		return new ResponseEntity<>(set, responseHeaders, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Set<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("Version", "CyberTek.v1");
		map.add("Operation", "Update");

		Set<Product> set = productService.updateProduct(id, product);
		return new ResponseEntity<>(set, map, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Set<Product>> createProduct(@RequestBody Product product) {

		Set<Product> set = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).header("Version", "CyberTek.v1").header("Operation", "Create")
				.body(set);
	}

	@GetMapping
	public ResponseEntity<Set<Product>> getProducts() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");

		return ResponseEntity.ok().headers(responseHeaders).body(productService.getProducts());

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		return ResponseEntity.ok(productService.getProduct(id));

	}

}
