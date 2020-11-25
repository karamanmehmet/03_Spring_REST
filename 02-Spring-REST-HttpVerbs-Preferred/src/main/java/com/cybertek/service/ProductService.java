package com.cybertek.service;

import java.util.List;
import java.util.Set;

import com.cybertek.model.Product;

public interface ProductService {

	Set<Product> getProducts();
	Set<Product> delete(Long id);
	Set<Product> updateProduct(long id, Product product);
	Set<Product> createProduct(Product product);
	Product getProduct(long id);
	
}
