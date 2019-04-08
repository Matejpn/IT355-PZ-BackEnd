package com.project.it355pz.dto.request;

public class ItemDto {
	private int idProduct;
	private int quantity;
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ItemDto [idProduct=" + idProduct + ", quantity=" + quantity + "]";
	}
	
	
}
