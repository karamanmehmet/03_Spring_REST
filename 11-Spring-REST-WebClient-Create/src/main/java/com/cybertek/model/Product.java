package com.cybertek.model;

public class Product {

    private long id;
    private String name;
    private String description;
    private float price;
    private Category category;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	public Product() {
		super();
	}
    
    
	

}
