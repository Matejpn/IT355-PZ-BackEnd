package com.project.it355pz.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_item")
	private int idItem;

	private int quantity;

	//bi-directional many-to-one association to Payment
	@ManyToOne
	@JoinColumn(name="payment_id_payment", referencedColumnName="id_payment")
	private Payment payment;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id_product", referencedColumnName="id_product")
	private Product product;

	public Item() {
	}

	public int getIdItem() {
		return idItem;
	}


	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}


	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}