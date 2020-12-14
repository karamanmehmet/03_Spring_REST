package com.cybertek.controller;

import java.util.List;

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

	private static int counter_timer = 0;

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<List<Product>> delete(@PathVariable("id") Long id) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Delete");
		List<Product> list = productService.delete(id);
		return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<List<Product>> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("Version", "CyberTek.v1");
		map.add("Operation", "Update");
		List<Product> list = productService.updateProduct(id, product);
		return new ResponseEntity<>(list, map, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<List<Product>> createProduct(@RequestBody Product product) {

		List<Product> set = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).header("Version", "CyberTek.v1").header("Operation", "Create")
				.body(set);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");

		return ResponseEntity.ok().headers(responseHeaders).body(productService.getProducts());

	}

	@GetMapping("products_timeout")
	public ResponseEntity<List<Product>> getProducts_products_timeout() throws InterruptedException {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");
		Thread.sleep(500);

		return ResponseEntity.ok().headers(responseHeaders).body(productService.getProducts());

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		return ResponseEntity.ok(productService.getProduct(id));

	}

	@GetMapping("4xx")
	public ResponseEntity<List<Product>> getProducts_4xx() throws InterruptedException {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");
		Thread.sleep(200);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders)
				.body(productService.getProducts());

	}

	@GetMapping("5xx")
	public ResponseEntity<List<Product>> getProducts_5xx() throws InterruptedException {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");
		Thread.sleep(200);
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).headers(responseHeaders)
				.body(productService.getProducts());

	}

	@GetMapping("products_retry")
	public ResponseEntity<List<Product>> getProductsproducts_retry() throws InterruptedException {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Version", "CyberTek.v1");
		responseHeaders.set("Operation", "Get List");
		System.out.println("Waiting");

		return ResponseEntity.ok().headers(responseHeaders).body(productService.getProducts());

	}

}
