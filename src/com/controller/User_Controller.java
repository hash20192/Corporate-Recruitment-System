package com.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.MD5Encoder;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import sun.security.provider.MD5;

import com.bean.Admin;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.Messages;
import com.bean.Resume;
import com.bean.User;
import com.dao.Job_Search_Methods;
import com.dao.Message;
import com.dao.Message_Methods;
import com.dao.User_DB;
import com.dao.User_DB_Methods;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.ServletConfig;


/**
 * Servlet implementation class user_Controller
 */
@WebServlet("/User_Controller")
public class User_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Controller() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		if(request.getParameter("profile")!=null  )
		{
			try{
			HttpSession s = request.getSession();
			User user =	(User)s.getAttribute("user");
			long userid = user.getUserid();
			User_DB_Methods udm = new User_DB();
			user = udm.select(userid);	
			List<Resume> l = udm.select_resume(userid) ;
			RequestDispatcher rd = request.getRequestDispatcher("/user/UP/user_Profile.jsp");
			request.setAttribute("user", user);
			request.setAttribute("resume", l);
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("profile_update")!=null && request.getParameter("profile_update").equals("Update"))
		{
		
			try{
				
			HttpSession s = request.getSession();
			User user = (User)s.getAttribute("user");
			
			long userid = user.getUserid();
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			long mobileno = Long.parseLong(request.getParameter("mobileno"));
			User_DB_Methods udm = new User_DB();
			System.out.println(userid);

		
			udm.update(firstname, lastname, email, mobileno,request.getParameter("file"),userid);	
			response.sendRedirect("/user/Up/User.jsp");
			
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		if(request.getParameter("user_resume")!=null){
			response.sendRedirect("user/UP/user_Resume.jsp");
		}
		
		if(request.getParameter("resume_show")!=null){
			try{
			
			String fullname = request.getParameter("full_name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone_numbers");
			String website = request.getParameter("websites");
			String address1 = request.getParameter("address_line1");
			String address2 = request.getParameter("address_line2");
			String address3 = request.getParameter("address_line3");
		
/*			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.newPage();
			document.add(new Paragraph(fullname));
			response.setContentType("application/pdf");
	        response.setHeader( "Content-Disposition", "attachment; filename=\"resume.pdf\"" );
			document.close();*/
			User_DB_Methods udb = new User_DB();
			Image img = udb.preview_resume(fullname,email,phone,website,address1,address2,address3);
		/*	BufferedImage outImage =   
		       new BufferedImage(700,600,  
		       BufferedImage.TYPE_INT_RGB);  
			  Graphics2D g2d = outImage.createGraphics();  */
		       /* g2d.drawImage(img, 0, 0, null);  */
		      /*  JPEGImageEncoder encoder =  JPEGCodec.createJPEGEncoder(out);  
		        encoder.encode(outImage);  */
			File f = new File("MyFile.jpg");
			response.setContentType("image/jpeg");  
			OutputStream out = response.getOutputStream();
			BufferedImage outImage =   
		              new BufferedImage(img.getHeight(null),img.getHeight(null),  
		                BufferedImage.TYPE_INT_RGB);  
		                  
		        Graphics2D g2d = outImage.createGraphics();  
		        g2d.drawImage(img, 0, 0, null);  
		        JPEGImageEncoder encoder =  JPEGCodec.createJPEGEncoder(out);  
		        encoder.encode(outImage);  
		        out.close();  
		      RequestDispatcher rd = request.getRequestDispatcher("/user_Resume.jsp");
		      
		      rd.forward(request, response);
		      
		    return;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		if(request.getParameter("resume_add")!=null){
//			File file = new File(request.getParameter(" "));
//			FileInputStream fin = new FileInputStream(file);
//			PreparedStatement pre = conn.prepareStatement("insert into MyTable values(?)");
//			pre.setBinaryStream(1,fin,(int)file.length());
//			pre.executeUpdate();
		}
		
		if(request.getParameter("get_resumes")!=null){
			try{
				
				HttpSession s = request.getSession();
				User user = (User)s.getAttribute("user");
				long userid = user.getUserid();
				User_DB_Methods udm = new User_DB();
				response.setContentType("text/html");
				List<Resume> l = udm.select_resume(userid);	
				RequestDispatcher rd = request.getRequestDispatcher("/user/UP/select_resume.jsp");
				request.setAttribute("resume_list", l);
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
		
		if(request.getParameter("inbox")!=null  )
		{
			try{
				HttpSession s = request.getSession();
				User user = (User)s.getAttribute("user");
				
				String user_email = user.getEmail();
			Message_Methods mm = new Message();
			
			List<Messages> m =  mm.all_massages(user_email) ;
			RequestDispatcher rd = request.getRequestDispatcher("user/UP/inbox.jsp");
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
			RequestDispatcher rd = request.getRequestDispatcher("user/UP/message_show.jsp");
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
		    RequestDispatcher rd = request.getRequestDispatcher("user/UP/message_show.jsp");
			request.setAttribute("messages",l );
			rd.forward(request, response);
		}
	
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		if(request.getParameter("delete_resume")!=null  ){
			try{
				HttpSession s = request.getSession();
				User user = (User)s.getAttribute("user");
				
				String user_email = user.getEmail();
				User_DB_Methods udm = new User_DB();
				long resume_id = (Long.parseLong(request.getParameter("resume_id")));
				List<Job> l = udm.check_applied_resume(user_email, resume_id);
				PrintWriter out = response.getWriter();	
			
				int j=0;
				String list="";
				Job job;
				
				if(l==null){
					response.setContentType("text/html");
					out.print("<input type=\"hidden\" id=\"job_list\" name=\"job_ids\" value=\"0\" >");
					Resume r =udm.get_resume_details(resume_id);
					String base = getServletContext().getRealPath("/");
					String filePath = base+"/tempfiles/";
					File f = new File(filePath+user.getUserid()+"_"+r.getResume());
					boolean b =f.delete();
					if(b){
						System.out.println("File deleted");
					}
					else{
						System.out.println("File is not deleted");
					}
				}
				else{
					Iterator<Job> i = l.iterator();
					while(i.hasNext() && j<l.size()){
						if(j==0){
						job = l.get(j);
						
						list = list+String.valueOf(job.getJob_id());
						System.out.println(list);
						j++;
						}
						else{
							job = l.get(j);
							list = list+","+String.valueOf(job.getJob_id());
							System.out.println(list);
							j++;
						}
					}
					System.out.println(list+" "+l.size());
					response.setContentType("text/html");
					out.print("<input type=\"hidden\" id=\"job_list\" name=\"job_ids\" value="+list+" >");
					out.print("<input type=\"hidden\" id=\"resume_id_update\" name=\"resume_id_update\" value="+request.getParameter("resume_id")+" >");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
