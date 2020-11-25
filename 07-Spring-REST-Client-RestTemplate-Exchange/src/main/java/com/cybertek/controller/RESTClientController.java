package com.cybertek.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cybertek.model.Product;

@RestController
@RequestMapping("/client")
public class RESTClientController {

	private RestTemplate restTemplate;

	private final String BASE_API = "http://localhost:8080/products";

	@Autowired
	public RESTClientController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Set> delete(@PathVariable("id") Long id) {
		String url = BASE_API + "/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-CORP", "CYBERTEK");

		HttpEntity<Set> requestEntity = new HttpEntity<>(null, headers);

		return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Set.class);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Set> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

		String url = BASE_API + "/" + id;
		return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Set.class);

	}

	@PostMapping
	public ResponseEntity<Set> createProduct(@RequestBody Product product) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

		String url = BASE_API;
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, Set.class);

	}

	@GetMapping
	public ResponseEntity<Set> getProducts() {

		System.out.println("Client Initiated");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-CORP", "CYBERTEK");

		HttpEntity<Set> requestEntity = new HttpEntity<>(null, headers);

		String url = BASE_API;
		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Set.class);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-CORP", "CYBERTEK");

		HttpEntity<Product> requestEntity = new HttpEntity<>(null, headers);

		String url = BASE_API + "/" + id;
		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class);

	}

}
