package com.project.it355pz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product")
	private int idProduct;

	private String gender;

	private boolean isDeleted;

	private String name;

	private int price;

	private String url;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="product")
	private List<Item> items;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="brand_id_brand", referencedColumnName="id_brand")
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id_category", referencedColumnName="id_category")
	private Category category;

	public Product() {
	}


	public int getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setProduct(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setProduct(null);

		return item;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}