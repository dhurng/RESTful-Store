package edu.cs157b.restful;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface Hw2DAO  {
	
	Customer addCustomer(String name);
	Product addProduct(String name, double price);
	Cart addOrder(Customer customer, Product product);
	void addToCart(Cart cart, String prodName);
	void removeFromCart(Cart cart, String prodName);
	List getProducts(Cart cart);
	
	
	//work on this
	List getOrders();
	Cart getSpecOrder(int id);
		
	void updateTotal(Cart cart);
	//put this into the list of orders do we need this?
	void submitOrder(Cart cart);
	void updateCart(Cart cart);
	
	
	void deleteCart(int id);
	
	Customer searchCustomerID(int id);
	Customer searchCustomer(String name);
	Product searchProduct(String name);
	Product searchProductID(int id);
	Cart searchCart(int id);
	
	void updatePrice(int productID, double price);
	
	void addMoreProd(int cartID, String prodName);
}
