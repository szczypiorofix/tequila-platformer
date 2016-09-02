package com.platformer.game.main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class MySQLConnect {


// JDBC driver name and database URL
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static final String DB_URL = "jdbc:mysql://localhost/testbase1"; /// TUTAJ DO ZMIANY!

//  Database credentials
static final String USER = "root";
static final String PASS = "";

	
// https://www.db4free.net/signup.php


public MySQLConnect()
{
}

public void sendToMySQL()
{
	Connection conn = null;
	Statement stmt = null;
	   try{

		  System.out.println("Rejestracja sterownika JDBC: "+JDBC_DRIVER);
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("£¹czenie z baz¹ danych: "+DB_URL);
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      stmt = conn.createStatement();
	      String sql;

	      sql = "SELECT Id, firstName, lastName FROM table1";
	      ResultSet rs = stmt.executeQuery(sql);
	      	      
	      rs = stmt.executeQuery(sql);

	      while(rs.next()){
		     int indeks  = rs.getInt("Id");	  
	    	 String firstname  = rs.getString("firstName");
	         String lastname = rs.getString("lastName");
	         
	         System.out.print("ID: " +indeks);
	         System.out.print(", Numer: " +firstname);
	         System.out.println(", Nazwa: " +lastname);
	      }
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println();
	   System.out.println("Do widzenia!");
	}
}