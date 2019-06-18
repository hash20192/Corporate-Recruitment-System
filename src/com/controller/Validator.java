package com.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.bean.DBConnector;
import com.bean.Error;

@WebFilter("/Validator")
public class Validator implements Filter {

	  String id ;
	  String pass ;
	  String user ;
	  Boolean status;
    
	  public Validator() 
	  	{
       
    	  id = null;
		  pass = null;
		  user = null;
		  status = null;
    	
	  	}

	/**
	 * @see Filter#destroy()
	 */
	  
	public void destroy() 
		{
		// TODO Auto-generated method stub
		}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
		{
		if(request.getParameter("type")!=null && request.getParameter("type").equals("0"))
			{
			user = "admin";	
			}
	
		else if(request.getParameter("type")!=null && request.getParameter("type").equals("1"))
			{
			user = "user";
			}
	
		else if(request.getParameter("type")!=null && request.getParameter("type").equals("2"))
			{
			user = "company"; 
			}
		
		Error er = new Error();
		if(request.getParameter(user+"email")!="" && request.getParameter(user+"pass")!="" && request.getParameter("user")!=null)	
			{
			
			String sql="select * from crs_"+user+"_info"+ " where "+user+"_email = '"+request.getParameter(user+"email")+"' ";
			
			try 
				{
				Connection con = DBConnector.getConnaction();		
				Statement st = con.createStatement();
				
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				id = rs.getString(user+"_email");
				pass = rs.getString(user+"_password");
				status = rs.getBoolean(user+"_status");
				
				if(request.getParameter(user+"email").equals(id) && request.getParameter(user+"pass").equals(pass) && status==true )
					{
					
					request.setAttribute(user+"_login", id);
					chain.doFilter(request, response);
					}
				
			else{
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				er.setLfail("User Blocked");
				request.setAttribute("error",er);
				rd.forward(request, response);
				}
				}
				catch (SQLException e) 
					{
					RequestDispatcher rd = request.getRequestDispatcher("user\\UP\\user_login.jsp");
					er.setLfail("Invalid Email or Password");
					request.setAttribute("error",er);
					rd.forward(request, response);
					}
			}
		else{
			RequestDispatcher rd =null;
			if(user.equals("user")){
			rd = request.getRequestDispatcher("user/UP/user_login.jsp");
			}
			if(user.equals("company")){
				rd = request.getRequestDispatcher("company/CP/company_login.jsp");
				}
			if(user.equals("admin")){
				rd = request.getRequestDispatcher("admin/AP/admin_login.jsp");
				}
			if(request.getParameter(user+"id")==""){
			er.setEmail("Please Enter Email");
			}	
			if(request.getParameter(user+"pass")==""){
			er.setPassword("Please Enter Password");
			}
			request.setAttribute("error",er);
			rd.forward(request, response);
	
		}
		}	
	

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
