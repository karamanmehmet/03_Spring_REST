package com.cybertek.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cybertek.model.Product;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class RESTClientController {

	private WebClient.Builder webClientBuilder;

	private final String BASE_API = "http://localhost:8080/api/products";
	private final String BASE_API_BY_ID = "http://localhost:8080/api/products/{id}";

	@Autowired
	public RESTClientController(WebClient.Builder webClientBuilder ) {
		this.webClientBuilder = webClientBuilder;
	}

	@DeleteMapping("/{id}")
	public Set<Product> delete(@PathVariable("id") Long id) {
		return  webClientBuilder.build()
				.delete()
				.uri(BASE_API_BY_ID,id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Set.class)
				.block();

	}

	@PutMapping("/{id}")
	public Set<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

			return  webClientBuilder.build()
				.put()
				.uri(BASE_API_BY_ID,id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(product), Product.class)	
				.retrieve()
				.bodyToMono(Set.class)
				.block();
	}

	@PostMapping
	public Set<Product> createProduct(@RequestBody Product product) {

		return  webClientBuilder.build()
				.post()		
				.uri(BASE_API)	
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(product), Product.class)			
				.retrieve()
				.bodyToMono(Set.class)
				.block();

	}

	@GetMapping
	public Set<Product> getProducts() {

		return  webClientBuilder.build()
				.get()
				.uri(BASE_API)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Set.class)
				.block();

	}

	@GetMapping(value = "/{id}")
	public Product getProduct(@PathVariable("id") Long id) {

		System.out.println("WebClient Initiated");
		
		return  webClientBuilder.build()
					.get()
					.uri(BASE_API_BY_ID+"/rr",id)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve()
					.onStatus(HttpStatus::is4xxClientError,
							error -> Mono.error(new RuntimeException("API not found")))
						.onStatus(HttpStatus::is5xxServerError,
							error -> Mono.error(new RuntimeException("Server is not responding")))
					.bodyToMono(Product.class)
					.block();
		


	}

}
