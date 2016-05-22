package com.poolit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private String email=null;
     private String pwd=null;
     private String dob=null;
     private String mobileno=null;
     private String gender=null;
     static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
     static final String DB_URL = "jdbc:mysql://localhost/POOLIT";

     //  Database credentials
     static final String USER = "root";
     static final String PASS = "thub";
     
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		   java.util.Date DOBJavaDate = null;
		   DateFormat formatter1 = new SimpleDateFormat("DD-mm-yyyy");
		   System.out.println();
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		
		email=request.getParameter("e-mail");
		pwd=request.getParameter("pwd");
		dob=request.getParameter("dob");
		mobileno=request.getParameter("mobileno");
		gender=request.getParameter("gender");
		
		DOBJavaDate = (java.util.Date)formatter1.parse(dob);
		
		
		try {
		   String SQL = "insert into user(email,password,dob,mobile_no,gender,is_poolit_user) values(?,?,?,?,?,?)";
		   pstmt = conn.prepareStatement(SQL);
/*		   pstmt.setString(1,email);
		   pstmt.setString(2,pwd);
		   pstmt.setDate(3,new java.sql.Date(DOBJavaDate.getTime()));
		   pstmt.setString(4,mobileno);
		   pstmt.setString(5,gender);
		   pstmt.setBoolean(6,true);
		  */
		   pstmt.setString(1,"myemail@xty.com");
		   pstmt.setString(2,"dsgfd");
		   pstmt.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
		   pstmt.setString(4,"123456789");
		   pstmt.setString(5,"male");
		   pstmt.setBoolean(6, true);
		   
		 int i= pstmt.executeUpdate();
		 System.out.println(i+" records inserted");
		 
		}
		catch (SQLException e) {
		  System.out.println("in exception 1");
		  e.printStackTrace();
		}
		finally {
		  
		}
		 pstmt.close();
	      conn.close();
	   }catch(SQLException se){
		   System.out.println("in exception 2");
	      se.printStackTrace();
	   }catch(Exception e){
		   System.out.println("in exception 3");
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(pstmt!=null)
	            pstmt.close();
	      }catch(SQLException se2){
	    	  System.out.println("in exception 4");
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try	
	}

}
