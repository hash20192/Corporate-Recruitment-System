package com.dao;



import java.awt.Image;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextListener;
import javax.servlet.http.Part;

import com.bean.DBConnector;
import com.bean.Job;
import com.bean.Resume;
import com.bean.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class User_DB implements User_DB_Methods{

	@Override
	public void insert(String firstname, String lastname, String email,String password, long mobileno)
	{
		
		// TODO Auto-generated method stub
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		
		String sql = "insert into "+USER_TABLE+" (user_first_name,user_last_name,user_mobileno,user_status,user_email,user_password) values ('"+firstname+"','"+lastname+"','"+mobileno+"',0,'"+email+"','"+password+"')";
		int i = st.executeUpdate(sql);
		System.out.println("Rows affected :"+i);
		st.close();
		con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
 public void update(String firstname, String lastname, String email,long mobileno, String resume,long userid)  {
		// TODO Auto-generated method stub
		 FileInputStream fis = null;
		try{
			Connection con = DBConnector.getConnaction();
			File file = (File)new File("tempfiles/"+resume);
		    fis = new FileInputStream(file.getAbsolutePath());
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			String sql="update "+USER_TABLE+" set user_first_name='"+firstname+"',user_last_name = '"+lastname+"' ,user_mobileno = "+mobileno+",user_email ='"+email+" where user_id='"+userid+"'";
			String sql1="update "+USER_RESUME+" set user_resume='"+fis+"' ";
			int i = st.executeUpdate(sql);
			int i1 = st1.executeUpdate(sql1);
			System.out.println("Rows affected in User databse (crs_user_info) & (user_resume) :"+i+" "+i1);
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}	
	}

	@Override
	public void delete(String user_id) {
		// TODO Auto-generated method stub
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String Sql = "delete from "+USER_TABLE+" where user_id='"+user_id+"'";
		int i = st.executeUpdate(Sql);
		System.out.println("Rows Affected :"+i);
		st.close();
		con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}

	@Override
	public User select(long user_id) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select * from "+USER_TABLE+" where user_id='"+user_id+"'";
			ResultSet rs = st.executeQuery(Sql);
			
			while(rs.next()){
				String firstname = rs.getString("user_first_name");
				String lastname = rs.getString("user_last_name");
				String email = rs.getString("user_email");
//				String gender = rs.getString("user_gender");
				long userid = rs.getLong("user_id");
				long mobileno = rs.getLong("user_mobileno");
//				String nationality = rs.getString("user_nationality");
				
				user.setFirstname(firstname);;
				user.setLastname(lastname);
//				user.setGender(gender);
				user.setMobileno(mobileno);
//				user.setNationality(nationality);
				user.setUserid(userid);
				user.setEmail(email);
			}
			
			
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return user;
	}


	@Override
	public ByteArrayOutputStream create_pdf(String fullname,String email,String phone,String website,String address1,String address2,String address3) {
		Document doc = new Document();
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		try{
		 	
	        
	        PdfWriter docWriter = null;
	        docWriter = PdfWriter.getInstance(doc, baosPDF);
	        doc.open();
	        
	        /*if(){
	        PdfWriter.getInstance(document, response.getOutputStream());
	        }
	        else{}*/
	        
	        doc.newPage();
	        Paragraph p1 = new Paragraph(email);
	        Paragraph p2 = new Paragraph(phone);
	        Paragraph p3 = new Paragraph(website);
	        Paragraph p4 = new Paragraph(address1);
	        Paragraph p5 = new Paragraph(address2);
	        Paragraph p6 = new Paragraph(address3);
	        doc.addTitle(fullname);
	       
	       
	       doc.add(p1);
	        doc.add(p2);
	        doc.add(p3);
	        doc.add(p4);
	        doc.add(p5);
	        doc.add(p6);
	        
	        doc.close();
	        docWriter.close();
	        
			}
	   catch(DocumentException e){
	   
		   e.printStackTrace();
	        }
		return baosPDF;
	}

	@Override
	public Image preview_resume(String fullname,String email,String phone,String website,String address1,String address2,String address3) {

		 ByteBuffer buf = null;
	     buf = ByteBuffer.wrap(create_pdf(fullname,email,phone,website,address1,address2,address3).toByteArray());
	     PDFFile pdffile;
	     Image img = null;
		try {
			pdffile = new PDFFile(buf);
			PDFPage page = pdffile.getPage(1);
			Rectangle rect = new Rectangle(0, 0, (int)page.getBBox().getWidth(), (int)page.getBBox().getHeight());
			img = page.getImage(rect.width, rect.height, //width &amp; height
	                rect, // clip rect
	                null, // null for the ImageObserver
	                true, // fill background with white
	                true) // block until drawing is done
	        ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return img;
	}

	@Override
	public User select(String useremail) {
		User user = new User();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select * from "+USER_TABLE+" where user_email='"+useremail+"'";
			ResultSet rs = st.executeQuery(Sql);
			
			while(rs.next()){
				String firstname = rs.getString("user_first_name");
				String lastname = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				long mobileno = rs.getLong("user_mobileno");
				long userid = rs.getLong("user_id");
				
				user.setFirstname(firstname);;
				user.setLastname(lastname);
				user.setMobileno(mobileno);
				user.setEmail(email);
				user.setUserid(userid);
			}
			
			
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return user;
	}

	@Override
	public void insert_resume(String fname, long userid,String resume_title) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "insert into "+USER_RESUME+" (`user_id`,`post_on`,`user_resume`,`resume_title`)VALUES("+userid+",NOW(),'"+fname+"','"+resume_title+"')";
			st.executeUpdate(Sql);
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Resume> select_resume(long userid) {
		// TODO Auto-generated method stub
		List<Resume> l = new ArrayList<Resume>();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select user_resume,resume_title,idcrs_resume from "+USER_RESUME+" where user_id='"+userid+"'";
			ResultSet rs = st.executeQuery(Sql);
			
			while(rs.next()){
				Resume r = new Resume();
				r.setResume(rs.getString("user_resume"));
				r.setResume_title(rs.getString("resume_title"));
				r.setResume_id(rs.getLong("idcrs_resume"));
				l.add(r);
			}
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return l;
	}

	@Override
	public List<Job> check_applied_resume(String user_email,long resume_id) {
		
		List<Job> l=null;
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select  job_id from job_apply where user_email='"+user_email+"' && resume_id='"+resume_id+"'";
			ResultSet rs = st.executeQuery(Sql);
			 l = new ArrayList<>();
			if (rs.next()!=false) {
				rs.beforeFirst();
				while(rs.next()){
				Job j = new Job();
				j.setJob_id(rs.getLong("job_id"));
				l.add(j);
				}
			}
			else{
				l=null;
			}
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return l;
	}

	@Override
	public Resume get_resume_details(long resume_id) {
		// TODO Auto-generated method stub
		Resume r=null ;
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select  resume_title,user_resume from crs_resume where idcrs_resume='"+resume_id+"'";
			ResultSet rs = st.executeQuery(Sql);
			
			while(rs.next()){
			r = new Resume();
			r.setResume_title(rs.getString("resume_title"));
			r.setResume(rs.getString("user_resume"));
			}
			Statement st1 = con.createStatement();
			String sql1 = "delete from crs_resume where idcrs_resume='"+resume_id+"'";
			st1.executeUpdate(sql1);
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return r;
	}

	@Override
	public int update_resume_details(long resume_id,String resume,String resume_title,String job_ids,long user_id) {
		// TODO Auto-generated method stub
		int i=0,j=0,k=0;
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			String sql = "update "+USER_RESUME+" set user_resume='"+resume+"',resume_title='"+resume_title+"' where idcrs_resume="+resume_id;
			String sql1 = "update job_apply set job_apply_resume='"+resume+"' where user_id="+user_id+" && job_id IN ("+job_ids+") ";
			j = st.executeUpdate(sql);
			k =  st.executeUpdate(sql1);
			if(j!=0 && k!=0){
				i=1;
			}
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return i;
	}

	

}
