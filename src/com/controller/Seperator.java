package com.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.sun.net.httpserver.Filter.Chain;

/**
 * Servlet Filter implementation class Seperator
 */
@WebFilter("/Seperator")
public class Seperator implements Filter {

    public Seperator() 
    {	
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	if(request.getAttribute("admin_login")!=null)
		{
			request.setAttribute("admin_login",request.getAttribute("admin_login"));
			RequestDispatcher rd = request.getRequestDispatcher("Admin_Start");
			rd.forward(request, response);
			
		}
	else if(request.getAttribute("user_login")!=null)
		{
			request.setAttribute("user_login",request.getAttribute("user_login"));
			RequestDispatcher rd = request.getRequestDispatcher("User_Start");
			rd.forward(request, response);
			
		}
	else if(request.getAttribute("company_login")!=null)
		{	
			request.setAttribute("company_login",request.getAttribute("company_login"));
			RequestDispatcher rd = request.getRequestDispatcher("Company_Start");
			rd.forward(request, response);
		}
	
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
