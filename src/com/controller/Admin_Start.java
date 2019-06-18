package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Admin;
import com.dao.Admin_DB;
import com.dao.Admin_DB_Methods;

@WebServlet("/Admin_Start")
public class Admin_Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getAttribute("admin_login")!=null)
		{
		try {
		Admin admin=null;
		Admin_DB_Methods adm = new Admin_DB();
		admin = adm.select((String)request.getAttribute("admin_login"));
		HttpSession s = request.getSession();
		s.setAttribute("user", admin);
		response.sendRedirect("admin/admin.jsp");
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
