package com.cybertek.controller;

import java.util.List;

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
	public @ResponseBody List<Product> delete(@PathVariable("id") Long id) {
		return productService.delete(id);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public @ResponseBody List<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public @ResponseBody List<Product> createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@RequestMapping(value = "/products")
	public @ResponseBody List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/products/{id}")
	public @ResponseBody Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}
	


}
