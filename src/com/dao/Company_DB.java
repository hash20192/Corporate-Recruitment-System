package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.Company;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.JobApply;
import com.bean.Resume;
import com.sun.org.apache.regexp.internal.recompile;

public class Company_DB implements Company_DB_Methods{

	@Override
	public void insert(String emr_firstname, String emr_lastname, String emr_email,
			String emr_gender, String emr_nationality, String company_password, long company_id,
			long emr_mobileno ,String emr_address,String company_name,String company_address,boolean company_status,String company_email) {
		
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		
		String sql = "INSERT INTO "+USER_TABLE+" (emr_firstname,emr_lastname,company_id,company_name,emr_mobileno,company_address,emr_gender,emr_nationality,emr_address,company_status,company_password,emr_email,company_email) VALUES('"+emr_firstname+"','"+emr_lastname+"','"+company_id+"','"+company_name+"',"+emr_mobileno+",'"+company_address+"','"+emr_gender+"','"+emr_nationality+"','"+emr_address+"',"+company_status+",'"+company_password+"','"+emr_email+"','"+company_email+"')";
		int i = st.executeUpdate(sql);
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(String firstname, String lastname, String email,
			String gender, String nationality, long mobileno, String userid) {
		
		
	}

	@Override
	public void delete(long userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Company select(long userid) {
		Company company = new Company();
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select * from "+USER_TABLE+" where company_id='"+userid+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		company.setCompany_name(rs.getString("company_name"));
		company.setCompany_id(rs.getLong("company_id"));
		company.setCompany_email(rs.getString("company_email"));
		company.setCompany_address(rs.getString("company_address"));
		company.setEmr_firstname(rs.getString("emr_firstname"));
		company.setEmr_lastname(rs.getString("emr_lastname"));
		company.setEmr_email(rs.getString("emr_email"));
		company.setEmr_address(rs.getString("emr_address"));
		company.setEmr_gender(rs.getString("emr_gender"));
		company.setEmr_mobileno(rs.getLong("emr_mobileno"));
		company.setEmr_nationality(rs.getString("emr_nationality"));
		st.close();
		con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return company;
	}

	@Override
	public void insert_Job(long company_id,String company_name, String job_title, String nationality,
			String location, String min_experience,String max_experience, String skills,
			String function, String role, String industry,String job_description, String about_company) {
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "insert INTO "+Job_Table+" (`company_id`,`company_name`,`location`,`nationality`,`min_experience`,`max_experience`,`skills`,`function`,`role`,`industry`,`posted_on`,`job_description`,`about_company`,`job_title`) VALUES('"+company_id+"','"+company_name+"','"+location+"','"+nationality+"','"+min_experience+"','"+max_experience+"','"+skills+"','"+function+"','"+role+"','"+industry+"',CURDATE(),'"+job_description+"','"+about_company+"','"+job_title+"')";
		int i = st.executeUpdate(sql);
		st.close();
		con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Job> select_jobs(long companyid) {
		
		List<Job> l = new ArrayList<>();
		String jobdis = null;
		try{
		
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select * from "+Job_Table+" where company_id='"+companyid+"'";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
		Job post = new Job();
		post.setCompnay_name(rs.getString("company_name"));
		post.setCompany_id(rs.getLong("company_id"));
		post.setLocation(rs.getString("location"));
//		post.setNationality(rs.getString("nationality"));
//		post.setSkills(rs.getString("skills"));
//		post.setFunction(rs.getString("function"));
//		post.setRole(rs.getString("role"));
//		post.setIndustry(rs.getString("industry"));
		post.setPosted_on(rs.getDate("posted_on"));
		
		if(rs.getString("job_description").length()>55){
		 jobdis = rs.getString("job_description").substring(0, 55);
		jobdis = jobdis+"........";}
		else{jobdis = rs.getString("job_description");}
		
		post.setJob_description(jobdis);
//		post.setAbout_company(rs.getString("about_company"));
		post.setJob_title(rs.getString("job_title"));
		post.setExp_min(rs.getString("min_experience"));
		post.setExp_max(rs.getString("max_experience"));
		post.setJob_id(rs.getInt("job_id"));
		l.add(post);
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
	public Company update(String emr_firstname, String emr_lastname,
			String emr_email, String emr_gender, String emr_nationality,
			long company_id, long emr_mobileno, String emr_address,
			String company_name, String company_address, String company_email) {
	Company company = new Company() ;
	try{
	Connection con = DBConnector.getConnaction();
	Statement st = con.createStatement();
	String sql="update "+USER_TABLE+" set emr_firstname='"+emr_firstname+"',emr_lastname='"+emr_lastname+"',emr_mobileno="+emr_mobileno+",company_name='"+company_name+"',emr_gender='"+emr_gender+"',emr_nationality='"+emr_nationality+"',emr_email='"+emr_email+"',company_email='"+company_email+"',company_address='"+company_address+"',emr_address='"+emr_address+"' where company_id='"+company_id+"'";
	int i = st.executeUpdate(sql);	
	System.out.println("Rows Updated in Company database (crs_company_info) "+i);
	}
	catch(SQLException e){
		
	}
		return company;
	}

	@Override
	public Company session_Start(String companyemail) {
		Company company = new Company();
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select * from "+USER_TABLE+" where company_email='"+companyemail+"'";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		company.setCompany_name(rs.getString("company_name"));
		company.setCompany_id(rs.getLong("company_id"));
		company.setCompany_email(rs.getString("company_email"));
		st.close();
		con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return company;
	}

	@Override
	public Job job_Edit(long job_id) {
		
		Job post=new Job();
		try{
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select * from "+Job_Table+" where job_id='"+job_id+"'";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
		
		post.setCompnay_name(rs.getString("company_name"));
		post.setCompany_id(rs.getLong("company_id"));
		post.setLocation(rs.getString("location"));
		post.setNationality(rs.getString("nationality"));
		post.setSkills(rs.getString("skills"));
		post.setFunction(rs.getString("function"));
		post.setRole(rs.getString("role"));
		post.setIndustry(rs.getString("industry"));
		post.setPosted_on(rs.getDate("posted_on"));
		post.setJob_description(rs.getString("job_description"));
		post.setAbout_company(rs.getString("about_company"));
		post.setJob_title(rs.getString("job_title"));
		post.setExp_min(rs.getString("min_experience"));
		post.setExp_max(rs.getString("max_experience"));
		post.setJob_id(rs.getInt("job_id"));
		}
		st.close();
		con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	
		return post;
	}

	@Override
	public void job_update(String company_name,
			String job_title, String nationality, String location,
			String min_experience, String max_experience, String skills,
			String function, String role, String industry,
			String job_description, String about_company,long job_id) {
		try{
			
	
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "UPDATE `crs`.`crs_jobpost` SET `company_name` = '"+company_name+"',`location` = '"+location+"',`nationality` = '"+nationality+"',`min_experience` = '"+min_experience+"',`skills` = '"+skills+"',`function` = '"+function+"',`role` = '"+role+"',`industry` = '"+industry+"',`posted_on` = CURDATE(),`job_description` = '"+job_description+"',`about_company` = '"+about_company+"',`job_title` = '"+job_title+"',`max_experience` = '"+max_experience+"' WHERE `job_id` = "+job_id;
		st.executeUpdate(sql);
		}catch(Exception e){
		e.printStackTrace();
	}
		
	}

	@Override
	public List inbox(long company_id) {
		// TODO Auto-generated method stub
		List l = new ArrayList();
		String jobdis = null;
		try{
		
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select company_name,job_title,dateandtime,job_id,COUNT(user_id) AS count FROM job_apply where company_id="+company_id+" GROUP BY job_id ;";
		ResultSet rs = st.executeQuery(sql);

		while(rs.next()){
			JobApply ja = new JobApply();
			ja.setCompany_id(company_id);
			ja.setCompany_name(rs.getString("company_name"));
			ja.setJob_id(rs.getLong("job_id"));
			ja.setJob_title(rs.getString("job_title"));
			ja.setCount(rs.getInt("COUNT"));
			ja.setDate(rs.getDate("dateandtime"));
			l.add(ja);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List job_apply_users(long job_id) {
		// TODO Auto-generated method stub
		List l = new ArrayList();
		String jobdis = null;
		try{
		
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
//		String sql ="SELECT job_apply.*, crs_resume.user_resume FROM job_apply, crs_resume where job_id="+job_id+" && job_apply.user_id=crs_resume.user_id Group by user_id";
		String sql ="SELECT job_apply.* FROM job_apply where job_id="+job_id+"  ;";
		ResultSet rs = st.executeQuery(sql);

		while(rs.next()){
			Resume r = new Resume();
			
			r.setPost_on(rs.getDate("dateandtime"));
			r.setResume(rs.getString("job_apply_resume"));
			r.setUser_id(rs.getLong("user_id"));
			r.setUser_email(rs.getString("user_email"));
			l.add(r);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}


}
