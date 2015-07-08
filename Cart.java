package edu.cs157b.restful;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "total_amount")
	private float total = 0;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Customer customer;
	
	//@OneToMany(mappedBy="cart", targetEntity = Product.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OneToMany(cascade=CascadeType.ALL)
	private List<Product> Products = new ArrayList<Product>();

	//add the product into the cart 
	public void purchaseProduct(Product product) {
		this.Products.add(product);
		this.setTotal();
	}
	//no longer purchase this product
	void removeProduct(Product product) {
		this.Products.remove(product);
	}
	//return all the products inside cart
	public List<Product> getProduct() {
		return Products;
	}
	//get and set total price of cart
	public double getTotal() {
		return total;
	}
	
	public void setTotal() {
		for (int i = 0; i < Products.size(); i++) {
			this.total += Products.get(i).getPrice();
		}
	}
	//get and set customers for the cart 
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void removeCustomer() {
		this.customer = null;
	}
	//accessors and mutators
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void removeItems() {
		this.Products = null;
	}
}
