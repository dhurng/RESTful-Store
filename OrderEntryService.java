package edu.cs157b.restful;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.cs157b.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.cs157b.util.*;

import java.sql.*;
import java.util.Date;

import edu.cs157b.restful.HibernateUtil;

//Web service methods

public class OrderEntryService {
	
	public static Hw2DAO dao = new ConcreteDAO();
	
	public OrderEntryService() {
		Customer customer = dao.addCustomer("David Hurng");
		Customer customer2 = dao.addCustomer("Michelle Cady");
		Product product = dao.addProduct("apples", 12);
		Product product2 = dao.addProduct("bananas", 6.25);
		
		Cart cart = dao.addOrder(customer, product);
	}
	//dao.addOrder(customer, product);

	//Restful test = new Restful();
	
	public Customer addCustomer(String name) {
		return dao.addCustomer(name);
	}
	
	public Product addProduct(String name, double price) {
		return dao.addProduct(name, price);
	}
	
	public Customer searchCustomerID(int customerID) {
		return dao.searchCustomerID(customerID);
	}
	
	public Customer searchCustomer(String name) {
		return dao.searchCustomer(name);
	}
	
	public Product searchProduct(String name) {
		return dao.searchProduct(name);
	}
	
	public Cart addOrder(Customer customer, Product product) {
		return dao.addOrder(customer, product);
	}
	
	public Cart searchCart(int id){
		return dao.searchCart(id);
	}
	
	public void addToCart(Cart cart, String prodName) {
		dao.addToCart(cart, prodName);
	}
	
	public Cart getSpecOrder(int id) {
		return dao.getSpecOrder(id);
	}
	
	public Product searchProductID(int id) {
		return dao.searchProductID(id);
	}
	
	public void updatePrice(int id, double price) {
		dao.updatePrice(id, price);
	}
	
	public void updateTotal(Cart cart) {
		dao.updateTotal(cart);
	}
	
	public void deleteCart(int id) {
		dao.deleteCart(id);
	}
	
	public List<Cart> getCart() {
		List<Cart> orders = dao.getOrders();
		return orders;
	}
	
	public Cart getCart(int id) {
		Cart cart = dao.getSpecOrder(id);
		return cart;
	}
	
	public void submitOrder(Cart cart) {
		dao.submitOrder(cart);
	}
	
	public void updateOrder(Cart cart) {
		dao.updateCart(cart);
	}
	
	public void deleteOrder(int id) {
		dao.deleteCart(id);
	}
}