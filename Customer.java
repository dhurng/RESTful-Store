package edu.cs157b.restful;


import javax.persistence.*;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	private int id;
	@Column(name = "customer_name")
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="customer")
	private Cart cart;
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public void removeCart() {
		this.cart = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
