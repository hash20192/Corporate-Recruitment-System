package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

import com.bean.Admin;
import com.bean.Company;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.User;

public class Admin_DB implements Admin_DB_Methods{

	@Override
	public void insert(String firstname, String lastname, String email,
			String gender, String password, long mobileno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long admin_id,String firstname, String lastname, String email,
			String gender,String password, long mobileno) {
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql="update "+USER_TABLE+" set admin_first_name='"+firstname+"',admin_last_name = '"+lastname+"',admin_mobileno = "+mobileno+",admin_gender = '"+gender+"',admin_email ='"+email+"',admin_password ='"+password+"' where admin_id='"+admin_id+"'";
		int i = st.executeUpdate(sql);
		System.out.println("Rows affected in admin databse (crs_admin_info) :"+i);
		st.close();
		con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin Allselect(long adminid) {
		Admin admin = new Admin();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select * from "+USER_TABLE+" where admin_id='"+adminid+"'";
			ResultSet rs = st.executeQuery(Sql);
			rs.next();
			
				String admin_first_name = rs.getString("admin_first_name");
				String admin_last_name = rs.getString("admin_last_name");
				String admin_email = rs.getString("admin_email");
				String admin_gender = rs.getString("admin_gender");
				long admin_id = rs.getLong("admin_id");
				String admin_password = rs.getString("admin_password");
				long admin_mobileno = rs.getLong("admin_mobileno");
				
				admin.setAdmin_first_name(admin_first_name);
				admin.setAdmin_last_name(admin_last_name);
				admin.setAdmin_gender(admin_gender);;
				admin.setAdmin_email(admin_email);
				admin.setAdmin_id(admin_id);
				admin.setAdmin_mobileno(admin_mobileno);
				admin.setAdmin_password(admin_password);
			
			st.close();
			con.close();
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return admin;
	}

	@Override
	public List<User> selectAll() {
		List<User> l = new ArrayList<User>();
		try
		{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String Sql = "select user_id,user_status,user_email from crs_user_info";
		ResultSet rs = st.executeQuery(Sql);
		
			while(rs.next())
			{
			User u = new User() ;
			u.setUserid(rs.getLong("user_id"));
			u.setStatus(rs.getBoolean("user_status"));
			u.setEmail(rs.getString("user_email"));
			l.add(u);
			}

		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l;
		
		
		
	
		}

	@Override
	public void ad(long userid,boolean and) {
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		int x;
		if(and==true)
		{
		x=1;
		}
		else{
		x=0;	
		}
		String sql = "update crs_user_info set user_status ="+x+" where user_id='"+userid+"'";
		int i = st.executeUpdate(sql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Admin select(String adminemail) {
		Admin admin = new Admin();
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String Sql = "select * from "+USER_TABLE+" where admin_email='"+adminemail+"'";
		ResultSet rs = st.executeQuery(Sql);
		rs.next();
		admin.setAdmin_id(rs.getLong("admin_id"));
		admin.setAdmin_first_name(rs.getString("admin_first_name"));
		admin.setAdmin_first_name(rs.getString("admin_last_name"));
		admin.setAdmin_first_name(rs.getString("admin_gender"));
		admin.setAdmin_first_name(rs.getString("admin_mobileno"));
		admin.setAdmin_first_name(rs.getString("admin_password"));
		admin.setAdmin_email(adminemail);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return admin;
	}

	@Override
	public List<Company> selectAl() {
		List<Company> l = new ArrayList<Company>();
		try
		{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String Sql = "select company_id,company_name,company_status from crs_company_info";
		ResultSet rs = st.executeQuery(Sql);
		
			while(rs.next())
			{
			Company c = new Company() ;
			c.setCompany_id(rs.getLong("company_id"));
			c.setCompany_name(rs.getString("company_name"));
			c.setCompany_status(rs.getBoolean("company_status"));
			l.add(c);
			}

		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public void ad1(long comapanyid, boolean and) {
		// TODO Auto-generated method stub
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			int x;
			if(and==true)
			{
			x=1;
			}
			else{
			x=0;	
			}
			String sql = "update crs_company_info set company_status ="+x+" where company_id='"+comapanyid+"'";
			int i = st.executeUpdate(sql);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Job> all_jobs(long company_id) {
			
			List<Job> l = new ArrayList<>();
			String jobdis = null;
			try{
			
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String sql = "select * from "+JOB_TABLE+" where company_id='"+company_id+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			Job post = new Job();
			post.setCompnay_name(rs.getString("company_name"));
			post.setCompany_id(rs.getLong("company_id"));
			post.setJob_status(rs.getBoolean("job_control"));
			post.setPosted_on(rs.getDate("posted_on"));
			post.setAbout_company(rs.getString("about_company"));
			post.setJob_title(rs.getString("job_title"));
			post.setJob_id(rs.getLong("job_id"));
			l.add(post);
			System.out.println(post.getCompnay_name()+" "+post.getCompany_id()+" "+post.isJob_status()+" "+post.getPosted_on()+" "+post.getAbout_company()+" "+post.getJob_title()+" "+post.getJob_id());;
			}
			st.close();
			con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			return l;	
	}

	@Override
	public void ad2(long jobid, boolean and) {
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			int x;
			if(and==true)
			{
			x=1;
			}
			else{
			x=0;	
			}
			System.out.println(x);
			String sql = "update "+JOB_TABLE+" set job_control ="+x+" where job_id='"+jobid+"'";
			int i = st.executeUpdate(sql);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
		
		
	}
}
