package com.cybertek.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cybertek.exception.MyCustomException;
import com.cybertek.model.Product;
import com.cybertek.service.ProductService;

@RestController
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<List<Product>> delete(@PathVariable("id") Long id) {

		if (id > 10) {
			throw new MyCustomException("the id is not in range");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Delete");

		List<Product> list = productService.delete(id);

		return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<List<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("Version", "CyberTek.v1");
		map.add("Operation", "Update");

		List<Product> list = productService.updateProduct(id, product);
		return new ResponseEntity<>(list, map, HttpStatus.OK);
	}

	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Product> createProduct(@RequestBody Product product) {

		List<Product> list = productService.createProduct(product);
		return list;
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");

		return ResponseEntity.ok().headers(responseHeaders).body(productService.getProducts());

	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		return ResponseEntity.ok(productService.getProduct(id));

	}

}
