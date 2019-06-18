package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Company;
import com.dao.Company_DB;
import com.dao.Company_DB_Methods;

/**
 * Servlet implementation class company_Start
 */
@WebServlet("/Company_Start")
public class Company_Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/*
	 * This method is just for get operation.
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if(request.getAttribute("company_login")!=null)
		{
		try {
		Company company = new Company();
		Company_DB_Methods db = new Company_DB();
		HttpSession s = request.getSession();
		company = db.session_Start(((String)request.getAttribute("company_login")) );
		s.setAttribute("user",company );
		response.sendRedirect("company/company.jsp");
		return;
			} 
		catch (Exception e) 
			{
			e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
