package edu.cs157b.restful;

import java.util.List;

/**
 * 
 * @author davidhurng
 * Test connection
 */

public class Tester {
	
	public static void main(String[] args) {

		Hw2DAO dao = new ConcreteDAO();

		Customer customer = dao.addCustomer("David Hurng");
		Customer customer2 = dao.addCustomer("Shelby");
		Customer customer3 = dao.addCustomer("Michelle");
		
		
		Product product = dao.addProduct("apple", 5.00);
		Product product2 = dao.addProduct("strawberries", 9.00);
		Product product3 = dao.addProduct("beer", 8.00);
		Product product4 = dao.addProduct("bread", 12.00);
		
		
		//Customer customer = dao.addCustomer("David Hurng");
		//Customer customer2 = dao.addCustomer("Michelle Cady");
		//Product product = dao.addProduct("apples", 12);
		//Product product2 = dao.addProduct("bananas", 6.25);
		
		Cart cart = dao.addOrder(customer, product);
		
		//dao.addToCart(cart, product);	
		
		System.out.println("================ Order =====================");
		
		//initial cart with just 1 order
		//Cart cart = dao.addOrder(customer, product);
		Cart cart2 = dao.addOrder(customer2, product4);
		
		//dao.addToCart(cart, product2.getName());
		//dao.addToCart(cart, product3.getName());
		
		
		//dao.updateCart(cart);
		//dao.updatePrice(productID, price);
		
		System.out.println(dao.getProducts(cart));
		
		cart.setTotal();
		System.out.println(cart.getTotal());
		
		System.out.println("=================================================");

		System.out.println(dao.getSpecOrder(1));
		
		System.out.println("=================================================");
		
		//this seems to work!
		dao.updatePrice(2, 100);
		
		
		System.out.println("================================================");
		
		List<Product> test;
		
		//System.out.println(cart2.getProduct());
		//System.out.println(product4.getCart());

		dao.deleteCart(2);
		
		//System.out.println(cart2.getProduct());
		//System.out.println(product4.getCart());

		//dao.deleteCart(2);
	}
}