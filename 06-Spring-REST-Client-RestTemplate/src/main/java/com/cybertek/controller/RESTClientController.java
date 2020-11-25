package com.cybertek.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
	public Set<Product> delete(@PathVariable("id") Long id) {
		String url = BASE_API + "/{id}";
		restTemplate.delete(url, id);

		return restTemplate.getForObject(BASE_API, Set.class);

	}

	@PutMapping("/{id}")
	public Set<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		String url = BASE_API + "/{id}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

		restTemplate.put(url, httpEntity, id);

		return restTemplate.getForObject(BASE_API, Set.class);
	}

	@PostMapping
	public Set<Product> createProduct(@RequestBody Product product) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

		return restTemplate.postForObject(BASE_API, httpEntity, Set.class);

	}

	@GetMapping
	public Set<Product> getProducts() {

		String url = BASE_API;
		return restTemplate.getForObject(url, Set.class);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		String url = BASE_API + "/{id}";
		return restTemplate.getForEntity(url, Product.class, id);

	}

}
