package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.User_DB;
import com.dao.User_DB_Methods;

/**
 * Servlet implementation class User_Start
 */
@WebServlet("/User_Start")
public class User_Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getAttribute("user_login")!=null)
		{
		try {
		String useremail = (String)request.getAttribute("user_login");
		User_DB_Methods udb = new User_DB();
	
		User user = new User();
		user = udb.select(useremail);
		HttpSession s =  request.getSession();
		s.setAttribute("user", user);	
		
		response.sendRedirect("user/user.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
