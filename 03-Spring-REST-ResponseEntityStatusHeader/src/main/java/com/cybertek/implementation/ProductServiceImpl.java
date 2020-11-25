package com.cybertek.implementation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cybertek.model.Product;
import com.cybertek.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static Map<Long, Product> productRepo = new HashMap<>();
	static {
		Product tv = new Product();
		tv.setId(1);
		tv.setName("LG 40' Smart TV");
		productRepo.put(tv.getId(), tv);

		Product macbook = new Product();
		macbook.setId(2);
		macbook.setName("MacBook Pro");
		productRepo.put(macbook.getId(), macbook);

		Product microphone = new Product();
		microphone.setId(3);
		microphone.setName("BTW Microphone");
		productRepo.put(microphone.getId(), microphone);

		Product notebook = new Product();
		notebook.setId(4);
		notebook.setName("Dell 15' i7 Notebook");
		productRepo.put(notebook.getId(), notebook);

		Product chromebook = new Product();
		chromebook.setId(5);
		chromebook.setName("Google Chrome Book - Education 13' ");
		productRepo.put(chromebook.getId(), chromebook);
	}

	public Set<Product> delete(Long id) {
		productRepo.remove(id);
		return new HashSet(productRepo.values());
	}

	public Set<Product> updateProduct(long id, Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new HashSet(productRepo.values());
	}

	public Set<Product> createProduct(Product product) {
		productRepo.put(product.getId(), product);
		return new HashSet(productRepo.values());
	}

	public Set<Product> getProducts() {
		return new HashSet(productRepo.values());

	}

	public Product getProduct(long id) {
		return productRepo.get(id);
	}

}
