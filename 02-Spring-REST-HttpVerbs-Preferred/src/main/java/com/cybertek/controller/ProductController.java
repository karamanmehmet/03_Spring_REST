package com.cybertek.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Set<Product> delete(@PathVariable("id") Long id) {
		Set<Product> set = productService.delete(id);
		return set;
	}

	@PutMapping("/{id}")
	public Set<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		Set<Product> set = productService.updateProduct(id, product);
		return set;
	}

	@PostMapping
	public Set<Product> createProduct(@RequestBody Product product) {
		Set<Product> set = productService.createProduct(product);
		return set;
	}

	@GetMapping
	public Set<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping(value = "/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}

}
