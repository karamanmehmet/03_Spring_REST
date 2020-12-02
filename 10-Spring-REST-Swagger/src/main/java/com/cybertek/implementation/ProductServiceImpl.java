package com.cybertek.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybertek.model.Category;
import com.cybertek.model.Product;
import com.cybertek.repository.CategoryRepository;
import com.cybertek.repository.ProductRepository;
import com.cybertek.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}


	public List<Product> delete(Long id) {
		
		productRepository.deleteById(id);
		return productRepository.findAll();
	}

	public List<Product> updateProduct(long id, Product product) {
		
		Category category =categoryRepository.findByName(product.getCategory().getName());

		
		Product obj = productRepository.findById(id).get();
		obj.setDescription(product.getDescription());
		obj.setName(product.getName());
		obj.setPrice(product.getPrice());
		obj.setCategory(category);
		
		productRepository.save(obj);
		
		return productRepository.findAll();
	}

	public List<Product> createProduct(Product product) {
		
		Category category =categoryRepository.findByName(product.getCategory().getName());
		product.setCategory(category);
		
		productRepository.save(product);
		
		return productRepository.findAll();
	}

	public List<Product> getProducts() {
		return productRepository.findAll();

	}

	public Product getProduct(long id) {
		return productRepository.findById(id).get();
	}

}
