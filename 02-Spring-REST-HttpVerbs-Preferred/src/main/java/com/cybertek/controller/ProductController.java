package com.cybertek.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cybertek.model.Product;
import com.cybertek.service.ProductService;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Set<Product> delete(@PathVariable("id") Long id) {
		Set<Product> set = productService.delete(id);
		return set;
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public @ResponseBody Set<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		Set<Product> set = productService.updateProduct(id, product);
		return set;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Set<Product> createProduct(@RequestBody Product product) {
		Set<Product> set = productService.createProduct(product);
		return set;
	}

	@RequestMapping(value = "/products")
	public @ResponseBody Set<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/products/{id}")
	public @ResponseBody Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}
	


}
