package com.sportyshoes.webservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shoes")
public class Shoe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shoe_id")
	private long id;
	
	@Column(name="shoe_name")
	private String name;
	
	@Column(name="shoe_brand")
	private String brand;
	
	@Column(name="shoe_desc")
	private String description;
	
	@Column(name="shoe_price")
	private double price;
	
	public Shoe() {
		super();
	}
	
	public Shoe(String name, String brand, String description, double price) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public Shoe(long id, String name, String brand, String description, double price) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
