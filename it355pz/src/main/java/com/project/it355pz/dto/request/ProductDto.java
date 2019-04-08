package com.project.it355pz.dto.request;

public class ProductDto {
	
	private int idProduct;
	private String category;
	private String brand;
	private String name;
	private String url;
	private int price;
	private boolean isDeleted;
	
	
	public int getidProduct() {
		return idProduct;
	}
	public void setidProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
