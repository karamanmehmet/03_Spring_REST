package com.cybertek;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cybertek.model.Category;
import com.cybertek.model.Product;
import com.cybertek.repository.CategoryRepository;
import com.cybertek.repository.ProductRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	
	@Bean
	public CommandLineRunner mappingDemo(ProductRepository productRepository, CategoryRepository categoryRepository
			) {
		return args -> {
			
			
	        Category category = new Category("Computer");
	         
	        Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);
	         
	        Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);
	         
	        Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);
	         
	        Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);
			
	        Category category2 = new Category("Software");		
	        
	        Product antivirus = new Product("Kaspersky 2020", "Best Antivirus 2020 - Rewarded", 50, category2);
	         
	        Product firewall = new Product("Cisco Firewall", "Enterpsise level most used FW", 5000, category2);
	        
	        
            categoryRepository.save(category);
            categoryRepository.save(category2);
            
            
            productRepository.save(pc);
            productRepository.save(laptop);
            productRepository.save(phone);
            productRepository.save(tablet);
            productRepository.save(antivirus);
            productRepository.save(firewall);
			
		};
	}
	
}
