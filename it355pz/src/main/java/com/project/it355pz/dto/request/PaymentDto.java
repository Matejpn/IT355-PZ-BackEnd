package com.project.it355pz.dto.request;

import java.util.Date;
import java.util.List;

import com.project.it355pz.dto.response.PaymentProductDto;

public class PaymentDto {
	
	private int idPayment;
	private String userName;
	private String userSurname;
	private List<PaymentProductDto> products;
	private Date date;
	
	
	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public List<PaymentProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<PaymentProductDto> products) {
		this.products = products;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
