package edu.cs157b.restful;

import javax.persistence.*;


@Entity
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private int id;
	@Column(name = "product_name")
	private String name;
	
	private double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cart cart;
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	//set the cart as null now so it is unclaimed
	public void removeCart(Cart cart) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price2) {
		this.price = price2;
	}
	
}
