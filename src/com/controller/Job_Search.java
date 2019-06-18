package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.bean.Job;
import com.bean.User;
import com.dao.Job_Search_Methods;

/**
 * Servlet implementation class Job_Search
 */
@WebServlet("/Job_Search")
public class Job_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Job_Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("search")!=null){
			
			Job_Search_Methods jsm = new com.dao.Job_Search() ;
			List<Job> l = new ArrayList<Job>();
			l = jsm.select_jobs(request.getParameter("quary"));
			request.setAttribute("jobs", l);
			RequestDispatcher rd=null;
			HttpSession s = request.getSession(false);
			User u = (User)s.getAttribute("user");	
			if(u!=null){
				rd = request.getRequestDispatcher("user/UP/AdvSearch.jsp");
			}
			else{
				rd = request.getRequestDispatcher("AdvSearch.jsp");
			}
			rd.forward(request, response);
			
		}
	
		if(request.getParameter("jobid")!=null){
			Job_Search_Methods jsm = new com.dao.Job_Search() ;
			Job job = new Job();
			job = jsm.getJob(Integer.parseInt(request.getParameter("jobid")));
			request.setAttribute("job", job);
			RequestDispatcher rd=null;
			HttpSession s = request.getSession(false);
			User u = (User)s.getAttribute("user");
			if(u!=null){
				rd = request.getRequestDispatcher("user/UP/job.jsp");
			}
			else{
				rd = request.getRequestDispatcher("job.jsp");
			}
			rd.forward(request, response);
		}
		
		if(request.getParameter("job_apply")!=null ){
			HttpSession s = request.getSession(false);
			User u = (User)s.getAttribute("user");
			if(u!=null){
				long job_id = Long.parseLong(request.getParameter("job_id"));
				long company_id = Long.parseLong(request.getParameter("company_id"));
				long resume_id = Long.parseLong(request.getParameter("resume_id"));
				Job_Search_Methods jsm = new com.dao.Job_Search() ;
		String resume_name = jsm.item(String.valueOf(resume_id)) ;
			
			jsm.job_apply(u.getUserid(),job_id,company_id, request.getParameter("company_name"), u.getEmail(),resume_name, request.getParameter("job_title"),resume_id, false);
			 response.setContentType("text/html");
			    response.getWriter().write("Job Applied");
			}
			else{
				response.setContentType("text/html");
			    response.getWriter().write("Please Login To Apply");
			}
		}
	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
