package com.project.it355pz.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_payment")
	private int idPayment;

	private Date date;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id_users", referencedColumnName="id_users")
	private User user;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="payment")
	private List<Item> items;

	public Payment() {
	}
	

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public int getIdPayment() {
		return idPayment;
	}



	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}



	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setPayment(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setPayment(null);

		return item;
	}

}