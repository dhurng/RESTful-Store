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
@Path("/")
public class Restful {
	
	private static OrderEntryService oes = new OrderEntryService();
	
	Hw2DAO dao = new ConcreteDAO();
	
	//===================== Customers ================================
	@Path("customers")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnCustomers() throws Exception
	{ 
		PreparedStatement query = null;
		String myString = null;
		String returnString = " ";
		Connection conn = null;
		
		try{
			conn = DatabaseConnection.getDataSource().getConnection();
			query = conn.prepareStatement("select * from customer");
			ResultSet rs = query.executeQuery();
			while(rs.next()) 
			{
				String id = rs.getString("customer_id");
				String name = rs.getString("customer_name");
			 
				returnString += id + " " + name + "\n" + "</br>"; 
			}
			
			query.close();
		}
		finally {
			if (conn!=null) conn.close();
		}
		return "<p> All Customers: </p> " + returnString;

	}
	
	@Path("customers")
	@POST
	public String addCustomers(@FormParam("name")String name) 
	{
		
		oes.addCustomer(name);
		return "Customer's name: " + name + " is added.";
	}
	
	//====================== Products ==============================
	@Path("products")			
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnProducts() throws Exception
	{ 	
		PreparedStatement query = null;
		String myString = null;
		String returnString = " ";
		Connection conn = null;
		
		try{
			conn = DatabaseConnection.getDataSource().getConnection();
			query = conn.prepareStatement("select * from product");
			ResultSet rs = query.executeQuery();
			while(rs.next()) 
			{
				String id = rs.getString("product_id");
				String name = rs.getString("product_name");
				int price = rs.getInt("price");
			 
				returnString += name + "\n $" + price + "</br>"; 
			}
			
			query.close();
		}
		finally {
			if (conn!=null) conn.close();
		}
		return "<p> All Products in Store: </p> " + returnString;
	}
		
	@Path("products")
	@POST
	public String addProduct(@FormParam("name")String name,
							@FormParam("price")double price) 
	{
		//Product product = dao.addProduct(name, price);
		oes.addProduct(name, price);
		return "Added into Store: " + "<br></br>" + "Product name: " + name + "<br></br>" + "Price: " + price;
	}

	//========================= Orders =================================
	//get all existing orders
	
	
	@Path("orders")			
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnOrders() throws Exception
	{ 
		{ 	
			PreparedStatement query = null;
			String myString = null;
			String returnString = " ";
			Connection conn = null;
			
			try{
				conn = DatabaseConnection.getDataSource().getConnection();
				query = conn.prepareStatement("select * from cart");
				ResultSet rs = query.executeQuery();
				while(rs.next()) 
				{
					int id = rs.getInt("id");
					float total = rs.getFloat("total_amount");
					int customerID = rs.getInt("customer_customer_id");
					
					//Customer customer = dao.searchCustomerID(customerID);
					Customer customer = oes.searchCustomerID(customerID);
				 
					returnString += customer.getName() + "\n $" + total + " Cart#: " + id + "</br>"; 
				}
				
				query.close();
			}
			finally {
				if (conn!=null) conn.close();
			}
			return "<p> All Orders made: </p>" + returnString;
		}
	}
		
	//make the order with just 1 customer and 1 product for now
	@Path("orders")
	@POST
	public String createCart(@FormParam("name")String customerName,
							@FormParam("product")String productName) 
	{	
		Customer customer = oes.searchCustomer(customerName);
		Product product = oes.searchProduct(productName);
		
		Cart cart = oes.addOrder(customer, product);
		
		return "Cart is ready for " + customer.getName() + " and has item " + product.getName();
	}
	
	@Path("orders/cart")
	@POST
	public String addToCart(@FormParam("product")String productName,
							@FormParam("id")int id)
	{	
		Cart cart = oes.searchCart(id);
		//Product product = dao.searchProduct(productName);
		Product product = oes.searchProduct(productName);
				
		oes.addToCart(cart, productName);
		
		return "Cart " + cart.getId() + " added " + productName;

	}
	
	//============================ Other Functions =============================
		
	@Path("orders/{id}")			
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnOrdersbyID(@PathParam("id")int id) 
	{ 
		
		Cart cart = oes.getSpecOrder(id);
		String more = "";
		String returnString =  "CartID: " + cart.getId() + "</br> Total:" + cart.getTotal() + "</br> Customer Name: " + cart.getCustomer().getName() + "</br> Products in Cart: ";
		
		for(int i = 0; i < cart.getProduct().size(); i++) {
			Product products = cart.getProduct().get(i);
			more += "</br>" + products.getName();
			//return returnString + more;
		}
		
		return returnString + more;
	}
	
	//curl -X POST --data "name = trial" http://... 
	
	//need to update
	@Path("products/{id}/{newprice}")
	@PUT
	@Produces(MediaType.TEXT_HTML)
	public String updatePrice(@PathParam("id")int id,
							  @PathParam("newprice")double price)
	{
		//int id = Integer.parseInt(idp);
		//double price = Double.parseDouble(pricep);
		
		//Product product = oes.searchProductID(id);
		oes.updatePrice(id, price);
		
		//return "Works!";
		return "Product ID: " + id + "\n new price: $" + price + "</br>"; 
	}

	//need to delete
	@Path("orders/{id}")
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String deleteOrder(@PathParam("cart")int id)
	{		
		//get rid of the foreign key
		oes.deleteCart(id);
		return "Deleted";
	}
	
}