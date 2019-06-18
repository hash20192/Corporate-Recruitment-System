package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Admin;
import com.bean.Company;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.Messages;
import com.bean.User;
import com.dao.Admin_DB_Methods;
import com.dao.Admin_DB;
import com.dao.Company_DB;
import com.dao.Company_DB_Methods;
import com.dao.Job_Search_Methods;
import com.dao.Message;
import com.dao.Message_Methods;
import com.dao.User_DB;
import com.dao.User_DB_Methods;


@WebServlet("/Admin_Controller")
public class Admin_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
		if(request.getParameter("allUsers")!=null)
		{
			try{
			Admin_DB_Methods ad = new Admin_DB();
			List<User> l;
			l = (List<User>)ad.selectAll();
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/All_Users.jsp");
			request.setAttribute("allUsers", l);
			rd.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("allCompanies")!=null)
		{
			try{
			Admin_DB_Methods ad = new Admin_DB();
			List<Company> l;
			l = (List<Company>)ad.selectAl();
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/All_Companies.jsp");
			request.setAttribute("allCompanies", l);
			rd.forward(request, response);
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("all_jobs")!=null)
		{
		long company_id = Long.parseLong(request.getParameter("all_jobs"));
		Admin_DB_Methods am = new Admin_DB();
		List<Job> l = (List<Job>)am.all_jobs(company_id);
		RequestDispatcher rd = request.getRequestDispatcher("admin/AP/job_show.jsp");
		request.setAttribute("jobs",l );
		rd.forward(request, response);
		}
		
		if(request.getParameter("profile")!=null && request.getParameter("profile").equals("profile"))
		{
			try{
			Admin_DB_Methods ad = new Admin_DB();
			HttpSession s = request.getSession();
			Admin admin = (Admin)s.getAttribute("user");
			admin = ad.Allselect(admin.getAdmin_id());
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/admin_profile.jsp");
			request.setAttribute("user",admin );
			rd.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("status_company")!=null){
			Admin_DB_Methods ad = new Admin_DB();
			String s = request.getParameter("id");
			StringTokenizer st = new StringTokenizer(s,",");
			String userid = st.nextToken();
			boolean and = Boolean.parseBoolean(st.nextToken());
			if(and==true){
			and=false;
			}
			else if(and==false){
				and=true;
			}
			ad.ad1(Long.parseLong(userid), and);
			List<Company> l;
			l = (List<Company>)ad.selectAl();
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/All_Companies.jsp");
			request.setAttribute("allCompanies", l);
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("status_job")!=null){
			Admin_DB_Methods ad = new Admin_DB();
			String s = request.getParameter("id");
			StringTokenizer st = new StringTokenizer(s,",");
			long jobid = Long.parseLong(st.nextToken());
			boolean and = Boolean.parseBoolean(st.nextToken());
			long company_id = Long.parseLong(st.nextToken());
			System.out.println(jobid+" "+and+" "+company_id);
			if(and==true){
			and=false;
			}
			else if(and==false){
				and=true;
			}
			System.out.println(and);
			ad.ad2(jobid, and);
			List<Job> l;
			l = (List<Job>)ad.all_jobs(company_id);
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/job_show.jsp");
			request.setAttribute("jobs", l);
			rd.forward(request, response);
		}
		
		if(request.getParameter("status_user")!=null){
			Admin_DB_Methods ad = new Admin_DB();
			String s = request.getParameter("id");
			StringTokenizer st = new StringTokenizer(s,",");
			String userid = st.nextToken();
			boolean and = Boolean.parseBoolean(st.nextToken());
			if(and==true){
			and=false;
			}
			else if(and==false){
				and=true;
			}
			ad.ad(Long.parseLong(userid), and);
			List<User> l;
			l = (List<User>)ad.selectAll();
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/All_Users.jsp");
			request.setAttribute("allUsers", l);
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("profile_update")!=null && request.getParameter("profile_update").equals("Update"))
		{
			try{
				
			HttpSession s = request.getSession();
			Admin admin = (Admin)s.getAttribute("user");
			
			long admin_id = admin.getAdmin_id();
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			long mobileno = Long.parseLong(request.getParameter("mobileno"));
			String password = request.getParameter("password");
			
			Admin_DB_Methods ad = new Admin_DB();
			ad.update(admin_id,firstname, lastname, email, gender, password, mobileno);;	
			response.sendRedirect("admin/admin.jsp");
			
		}
	
			
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
		}
		
		if(request.getParameter("user_profile")!=null  )
		{
			try{
			User user = new User();
			long userid = Long.parseLong(request.getParameter("user_profile"));
			User_DB_Methods udm = new User_DB();
			user = udm.select(userid);	
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/user_Profile.jsp");
			request.setAttribute("user", user);
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("company_profile")!=null  )
		{
			try{
			Company c ;
			long companyid = Long.parseLong(request.getParameter("company_profile"));
			Company_DB_Methods cdm = new Company_DB();
			c = cdm.select(companyid);	
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/company_Profile.jsp");
			request.setAttribute("company", c);
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("inbox")!=null  )
		{
			try{
				HttpSession s = request.getSession();
				Admin admin = (Admin)s.getAttribute("user");
				
				String admin_email = admin.getAdmin_email();
			Message_Methods mm = new Message();
			
			List<Messages> m =  mm.all_massages(admin_email) ;
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/inbox.jsp");
			request.setAttribute("messages",m );
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("message_show")!=null  )
		{
			try{
				
			
			Message_Methods mm = new Message();
			
			List<Messages> m =  mm.get_conversation(request.getParameter("thread_id")) ;
			RequestDispatcher rd = request.getRequestDispatcher("admin/AP/message_show.jsp");
			request.setAttribute("messages",m );
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("reply_send")!=null  )
		{
			try{
				
			String thread_id = request.getParameter("thread_id");
			String message_to = request.getParameter("to");
			String message_from = request.getParameter("from");
			String message = request.getParameter("reply");
			String subject = request.getParameter("title");
			System.out.println(thread_id+" "+subject+" "+message);
			Message_Methods mm = new Message();
		    mm.reply_send(message_to, message_from, thread_id, message, subject); ;
		    List<Messages> l = mm.get_conversation(thread_id);
		    RequestDispatcher rd = request.getRequestDispatcher("admin/AP/message_show.jsp");
			request.setAttribute("messages",l );
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(request.getParameter("message_send")!=null){
			String to=null;
			if(request.getParameter("usercompany")!=null){
			to = request.getParameter("usercompany");
			}
			else if(request.getParameter("useruser")!=null){
			to = request.getParameter("useruser");
			}
			String from = request.getParameter("from");
			String message = request.getParameter("message");
			String option = request.getParameter("message_option");
			String title = request.getParameter("message_title");
			Message_Methods mm = new Message();
		
			if(option.equals("internal")){
			mm.message_send(to, from, message,title);
			}
		}
			if(request.getParameter("message_delete")!=null){
				
				
				String thread_ids = request.getParameter("thread_id");
				Message_Methods mm = new Message();
				
				mm.delete_messages(thread_ids);
				
			
			}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
