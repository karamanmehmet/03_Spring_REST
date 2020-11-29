package com.cybertek.service;

import java.util.List;
import java.util.Set;

import com.cybertek.model.Product;

public interface ProductService {

	List<Product> getProducts();
	List<Product> delete(Long id);
	List<Product> updateProduct(long id, Product product);
	List<Product> createProduct(Product product);
	Product getProduct(long id);
	
}
