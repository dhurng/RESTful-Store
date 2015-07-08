package edu.cs157b.restful;


import java.sql.PreparedStatement;

import edu.cs157b.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.cs157b.util.*;

import java.sql.*;
import java.util.Date;

import edu.cs157b.restful.HibernateUtil;

@Path("/database")
public class DatabaseAccess {
	private static final String api_version= "00.01.00";
	
	
	@Path("/customers")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnCustomers()
	{ 
		Hw2DAO dao = new ConcreteDAO();
		Customer customer = dao.addCustomer("David Hurng");
		return "<p>This works!</p>" +  customer.getName(); 
	}
	
	@Path("/customers")
	@POST
	public String addCustomers(@FormParam("name")String name) 
	{
		Hw2DAO dao = new ConcreteDAO();
		return "FormParam: Customer's name: " + name + " is added.";
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle()
	{ return "<p> Java Web Service</p>" ; }
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion()
	{ return "<p> Version</p>" + api_version ; }
	
	@Path("/dbaccess")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception
	{
		PreparedStatement query = null;
		String myString = null;
		String returnString = " ";
		Connection conn = null;
		
		try{
			conn = DatabaseConnection.getDataSource().getConnection();
			query = conn.prepareStatement("select * from book");
			ResultSet rs = query.executeQuery();
			while(rs.next()) 
			{String title = rs.getString("title");
			 String author = rs.getString("author");
			 int copies = rs.getInt("copies");
			 
			returnString += title + " " + author + " " + copies + "\n"; 
			}
			
			query.close();
		}
		finally {
			if (conn!=null) conn.close();
		}
		return "<p> Test </p> " + returnString;
	}
	
}