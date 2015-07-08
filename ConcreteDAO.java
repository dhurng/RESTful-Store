package edu.cs157b.restful;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConcreteDAO implements Hw2DAO {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	
	public Customer addCustomer(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setName(name);
		
		session.save(customer);
		
		session.getTransaction().commit();
		session.close();
		return customer;
	}
	
	public Product addProduct(String name, double price) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		
		session.save(product);
		
		session.getTransaction().commit();
		session.close();
		return product;
	}
		
	
	public Cart addOrder(Customer customer, Product product) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
				
		Cart cart = new Cart();
		cart.setCustomer(customer);	
		cart.purchaseProduct(product);
		
		customer.setCart(cart);
		product.setCart(cart);

		session.save(cart);
		
		session.getTransaction().commit();
		session.close();
		return cart;
	}
	
	public void addToCart(Cart cart, String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
				
		String hql = "FROM Product WHERE name =:name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		
		Product product = null;
		if(query.uniqueResult() != null) {
			product = (Product)query.uniqueResult();
			
			cart.purchaseProduct(product);
			product.setCart(cart);
			
			cart.setTotal();

		}
		
		else {
			System.out.println("Sorry we are all out of that!");
		}
		
		//cart.purchaseProduct(product);
		//product.setCart(cart);
				
		session.getTransaction().commit();
		session.close();
	}
	
	public void removeFromCart(Cart cart, String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
				
		String hql = "FROM Product WHERE name =:name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		
		Product product = null;
		if(query.uniqueResult() != null) {
			product = (Product)query.uniqueResult();
			
			cart.removeProduct(product);
			product.removeCart(cart);
		}
		
		else {
			System.out.println("Sorry there was no item in the cart!");
		}
		
		//cart.purchaseProduct(product);
		//product.setCart(cart);
				
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Product> getProducts(Cart cart) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = cart.getProduct();
				
		session.getTransaction().commit();
		session.close();
		return products;
	}
	
	public List<Cart> getOrders() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//need to get all the orders
		String hql = "FROM Cart";
		Query query = session.createQuery(hql);

		List<Cart> carts = null;
		if(query.list() != null) {
			carts = (List<Cart>) query.list();
			
			return carts;
		}
		
		else {
			System.out.println("Sorry there was no item in the cart!");
			return null;
		}
	}
	
	public Cart getSpecOrder(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Cart WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		
		Cart cart = null;
		if(query.uniqueResult() != null) {
			cart = (Cart) query.uniqueResult();
			
			return cart;
		}
		else {
			System.out.println("Sorry this cart does not exist!");
			return null;
		}
	}
	
	public void deleteCart(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> test;
		//delete		
		String hql = "FROM Cart WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		
		Cart cart = null;
		if(query.uniqueResult() != null) {
			cart = (Cart) query.uniqueResult();
			
			Product product = null;
			for (int i = 0; i < cart.getProduct().size(); i++) {
				test = cart.getProduct();
				product = test.get(i);
				product.removeCart(cart);
				//products wont have carts
				test.remove(product);
			}
			
			Customer customer = cart.getCustomer();
			customer.removeCart();
			cart.removeCustomer();
						
			session.save(product);
			session.save(customer);
			session.delete(cart);
		}
		else {
			System.out.println("Sorry this cart does not exist!");
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void submitOrder(Cart cart) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//to put in the list for what?
		
				
		session.getTransaction().commit();
		session.close();
	}
	
	public Customer searchCustomerID(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//delete
		String hql = "FROM Customer WHERE customer_id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		
		Customer customer = null;
		if(query.uniqueResult() != null) {
			customer = (Customer) query.uniqueResult();
			return customer;
		}
		else {
			System.out.println("Sorry this Customer does not exist!");
			return customer;
		}
	}
	
	public Customer searchCustomer(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//delete
		String hql = "FROM Customer WHERE customer_name =:name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		
		Customer customer = null;
		if(query.uniqueResult() != null) {
			customer = (Customer) query.uniqueResult();
			return customer;
		}
		else {
			System.out.println("Sorry this Customer does not exist!");
			return customer;
		}
	}
	
	public Product searchProduct(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//delete
		String hql = "FROM Product WHERE product_name =:name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		
		Product product = null;
		if(query.uniqueResult() != null) {
			product = (Product) query.uniqueResult();
			return product;
		}
		else {
			System.out.println("Sorry this Product does not exist!");
			return product;
		}
	}
	
	public Product searchProductID(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		//delete
		String hql = "FROM product WHERE product_id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		
		Product product = null;
		if(query.uniqueResult() != null) {
			product = (Product) query.uniqueResult();
			return product;
		}
		else {
			System.out.println("Sorry this Product does not exist!");
			return product;
		}
	}
	
	public Cart searchCart(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Cart WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		
		Cart cart = null;
		if(query.uniqueResult() != null) {
			cart = (Cart) query.uniqueResult();
			return cart;
		}
		else {
			System.out.println("Sorry this Cart does not exist!");
			return cart;
		}
	}
	
	public void updatePrice(int productID, double price) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Product WHERE product_id =:productID";
		Query query = session.createQuery(hql);
		query.setInteger("productID", productID);
		
		Product product = null;
		if(query.uniqueResult() != null) {
			product = (Product)query.uniqueResult();
			
			product.setPrice(price);
			
			session.update(product);
			
			session.save(product);
			
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateTotal(Cart cart) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		cart.setTotal();
		
		session.save(cart);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateCart(Cart cart) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		int cartid = cart.getId();
		
		String hql = "UPDATE cart WHERE id =:cartid";
		Query query = session.createQuery(hql);
		query.setInteger("cartid", cartid);

	}
	
	public void  addMoreProd(int cartID, String prodName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM cart WHERE id =:cartID";
		Query query = session.createQuery(hql);
		query.setInteger("cartID", cartID);
		
		Cart cart = (Cart)query.uniqueResult();
		
		String hql2 = "FROM product WHERE product_name =:prodName";
		Query query2 = session.createQuery(hql2);
		query2.setString("prodName", prodName);
		
		Product product = (Product)query.uniqueResult();
		
		cart.purchaseProduct(product);
		
		session.getTransaction().commit();
		session.close();
	}

}
