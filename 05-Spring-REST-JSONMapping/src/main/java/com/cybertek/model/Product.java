package com.cybertek.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	private long id;
	private String name;
	   
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


	
}
