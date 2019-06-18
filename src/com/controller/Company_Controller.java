package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Company;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.Messages;
import com.bean.User;
import com.dao.Company_DB;
import com.dao.Company_DB_Methods;
import com.dao.Message;
import com.dao.Message_Methods;


/**
 * Servlet implementation class company_Controller
 */
@WebServlet("/Company_Controller")
public class Company_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Company user = (Company) session.getAttribute("user");
		
		
		System.out.println("hi "+user.getCompany_name());
		
		if(request.getParameter("register")!=null && request.getParameter("register").equals("company_registration")){
		
			try{
			Connection con = DBConnector.getConnaction();		
			Statement st = con.createStatement();
			String emr_firstname = request.getParameter("emr_firstname");
			String emr_lastname = request.getParameter("emr_lastname");
			String emr_email = request.getParameter("emr_email");
			String emr_gender = request.getParameter("emr_gender");
			long emr_mobileno = Long.parseLong(request.getParameter("emr_mobileno"));
			String emr_nationality = request.getParameter("emr_nationality");
			String emr_address = request.getParameter("emr_address");
			
			String company_name = request.getParameter("company_name");
			String company_password = request.getParameter("company_password");
			String company_address = request.getParameter("company_address");
			String company_email = request.getParameter("company_email");
			boolean company_status = false;

			
			Company_DB_Methods cd = new Company_DB();
			cd.insert(emr_firstname, emr_lastname, emr_email, emr_gender, emr_nationality, company_password, emr_mobileno, emr_address, company_name, company_address, company_status, company_email);
			response.sendRedirect("index.jsp");
			st.close();
			con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("job_create")!=null ){
			System.out.println("hi");
			response.sendRedirect("company/JP/job_post.jsp");	
		}
	
		
		if(request.getParameter("create_exam")!=null && request.getParameter("create_exam").equals("exam") ){
			System.out.println("hi");
			response.sendRedirect("company/EP/exam_create.jsp");	
		}
		
		if(request.getParameter("job_post")!=null && request.getParameter("job_post").equals("Post Job")){
			
			HttpSession s = request.getSession();
			Company company = (Company)s.getAttribute("user");
			Company_DB_Methods cm = new Company_DB();
			
			long company_id = (company.getCompany_id());
			String company_name = company.getCompany_name();
			String job_title = request.getParameter("job_title");
			String nationality = request.getParameter("nationality");
			String location = request.getParameter("location");
			String min_experience = request.getParameter("min_experience");
			String max_experience = request.getParameter("max_experience");
			String skills = request.getParameter("skills");
			String function = request.getParameter("function");
			String role = request.getParameter("role");
			String industry = request.getParameter("industry"); 
			String job_description = request.getParameter("job_description");
			job_description = job_description.replaceAll("(\r\n|\n)", "<br />");
			String about_company = request.getParameter("about_company");
	
			
			cm.insert_Job(company_id, company_name, job_title, nationality, location, min_experience, max_experience, skills, function, role, industry, job_description, about_company);
			response.sendRedirect("company/company.jsp");
		}
		
	if(request.getParameter("job_show")!=null ){
			
			
			HttpSession s = request.getSession();
			Company company = (Company)s.getAttribute("user");
			Company_DB_Methods cm = new Company_DB();
			List<Job> l = (List<Job>)cm.select_jobs(company.getCompany_id());
			RequestDispatcher rd = request.getRequestDispatcher("company/JP/job_show.jsp");
			request.setAttribute("jobs",l );
			rd.forward(request, response);
		}
	
		if(request.getParameter("inbox")!=null  )
		{
			try{
				HttpSession s = request.getSession();
				Company c =(Company)s.getAttribute("user");
				Company_DB_Methods cdb = new Company_DB();
				long company_id = c.getCompany_id();
				List l = cdb.inbox(company_id);
				request.setAttribute("job_apply", l);
				String user_email = c.getCompany_email();
				Message_Methods mm = new Message();
			List<Messages> m =  mm.all_massages(user_email) ;
			request.setAttribute("messages",m );
			RequestDispatcher rd = request.getRequestDispatcher("company/CP/inbox.jsp");
			if(m.equals(null) && l.equals(null)){
				request.setAttribute("null", "No Messages Found");
				System.out.println("hi");
			}
			rd.forward(request, response);
		}

			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	
	if(request.getParameter("job_apply_show")!=null ){
		HttpSession s = request.getSession();
		Company c =(Company)s.getAttribute("user");
		Company_DB_Methods cdb = new Company_DB();
		long company_id = c.getCompany_id();
		List l = cdb.job_apply_users(Long.parseLong(request.getParameter("job_id")));
		RequestDispatcher rd = request.getRequestDispatcher("company/JP/job_apply_show.jsp");
		request.setAttribute("user_list", l);
		rd.forward(request, response);
	}
	
	if(request.getParameter("profile")!=null ){
		
		Company_DB_Methods cdb = new Company_DB();
		HttpSession s = request.getSession();
		Company company = (Company)s.getAttribute("user");
		long company_id = company.getCompany_id();
		company = cdb.select(company_id);
		request.setAttribute("company", company);
		RequestDispatcher rd = request.getRequestDispatcher("company/CP/company_Profile.jsp");
		rd.forward(request, response);
		
		}
	
	if(request.getParameter("company_profile")!=null ){
		Company_DB_Methods cmd = new Company_DB();
		String emr_firstname = request.getParameter("emr_firstname");
		String emr_lastname = request.getParameter("emr_lastname");
		String emr_email = request.getParameter("emr_email");
		String emr_gender = request.getParameter("emr_gender");
		long emr_mobileno = Long.parseLong(request.getParameter("emr_mobileno"));
		String emr_nationality = request.getParameter("emp_nationality");
		String emr_address = request.getParameter("emr_address");
		
		String company_name = request.getParameter("company_name");
		long company_id = Long.parseLong(request.getParameter("company_id"));
		String company_address = request.getParameter("company_address");
		String company_email = request.getParameter("company_email");
		cmd.update(emr_firstname, emr_lastname, emr_email, emr_gender, emr_nationality, company_id, emr_mobileno, emr_address, company_name, company_address, company_email);
		response.sendRedirect("company/company.jsp");
	}
	
	if(request.getParameter("job_edit")!=null){
		Company_DB_Methods cmd = new Company_DB();
		Job post = cmd.job_Edit(Long.parseLong(request.getParameter("job_edit")));
		RequestDispatcher rd = request.getRequestDispatcher("company/JP/job_edit.jsp");
		request.setAttribute("post", post);
		rd.forward(request, response);
		return;
	}
	
	if(request.getParameter("job_update")!=null){
		Company_DB_Methods cmd = new Company_DB();
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		String company_name = request.getParameter("company_name");
		String job_title = request.getParameter("job_title");
		String nationality = request.getParameter("nationality");
		String location = request.getParameter("location");
		String min_experience = request.getParameter("min_experience");
		String max_experience = request.getParameter("max_experience");
		String skills = request.getParameter("skills");
		String function = request.getParameter("function");
		String role = request.getParameter("role");
		String industry = request.getParameter("industry"); ;
		String job_description = request.getParameter("job_description");;
		String about_company = request.getParameter("about_company");
		cmd.job_update(company_name, job_title, nationality, location, min_experience, max_experience, skills, function, role, industry, job_description, about_company, job_id);	
		response.sendRedirect("company/company.jsp");
	}
	
	if(request.getParameter("fileName")!=null){
	String fileName = request.getParameter("fileName") ;
    if(fileName == null || fileName.equals("")){
        throw new ServletException("File Name can't be null or empty");
    }
    String FILES_DIR = getServletContext().getRealPath("/tempfiles/");
    File file = new File(FILES_DIR+File.separator+fileName);
    if(!file.exists()){
        throw new ServletException("File doesn't exists on server.");
    }
    System.out.println("File location on server::"+file.getAbsolutePath());
    ServletContext ctx = getServletContext();
    InputStream fis = new FileInputStream(file);
    String mimeType = ctx.getMimeType(file.getAbsolutePath());
    response.setContentType(mimeType != null? mimeType:"application/octet-stream");
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
     
    ServletOutputStream os       = response.getOutputStream();
    byte[] bufferData = new byte[1024];
    int read=0;
    while((read = fis.read(bufferData))!= -1){
        os.write(bufferData, 0, read);
    }
    os.flush();
    os.close();
    fis.close();
    System.out.println("File downloaded at client successfully");
	}
	
	
	
	if(request.getParameter("view_resume")!=null){
		String user_id = request.getParameter("user_id");
		String DOCUMENT_LOCATION = getServletContext().getRealPath("/tempfiles")+"\\"+user_id+"_"+request.getParameter("resume_name") ;
		response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        

        InputStream in = new FileInputStream(DOCUMENT_LOCATION);
        OutputStream out = response.getOutputStream();

        // Copy the bits from instream to outstream
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
           out.write(buf, 0, len);
        }
        in.close();

	        
	    } 
	
	

	if(request.getParameter("message_send")!=null){
		String to=null;
		if(request.getParameter("useruser")!=null){
		to = request.getParameter("useruser");
		}
		else if(request.getParameter("useradmin")!=null){
		to = "hash@gmail.com";
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
	

	
	if(request.getParameter("message_show")!=null  )
	{
		try{
			
		
		Message_Methods mm = new Message();
		
		List<Messages> m =  mm.get_conversation(request.getParameter("thread_id")) ;
		RequestDispatcher rd = request.getRequestDispatcher("company/CP/message_show.jsp");
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
	    RequestDispatcher rd = request.getRequestDispatcher("company/CP/message_show.jsp");
		request.setAttribute("messages",l );
		rd.forward(request, response);
	}

		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
