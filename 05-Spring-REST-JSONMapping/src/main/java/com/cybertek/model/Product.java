package com.cybertek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private float price;
 
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
 
    public Product() {
    }
 
    public Product(String name, String description, float price,
            Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

	@JsonProperty("p_id")  
	public long getId() {
		return id;
	}
	
	@JsonProperty("product_id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("p_name") 
	public String getName() {
		return name;
	}

	@JsonProperty("product_name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("p_description") 
	public String getDescription() {
		return description;
	}

	@JsonProperty("product_description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("p_price") 
	public float getPrice() {
		return price;
	}

	@JsonProperty("product_price")
	public void setPrice(float price) {
		this.price = price;
	}

	@JsonProperty("p_category") 
	public Category getCategory() {
		return category;
	}

	@JsonProperty("product_category")
	public void setCategory(Category category) {
		this.category = category;
	}
 


 
   

	
}
