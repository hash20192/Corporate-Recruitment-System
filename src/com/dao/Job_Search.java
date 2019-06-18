package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.DBConnector;
import com.bean.Job;

public class Job_Search implements Job_Search_Methods {

	@Override
	public List<Job> select_jobs(String Search) {
		List<Job> l = new ArrayList<>();
		String jobdis = null;
		try{
		
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql = "select * from "+Job_Table+" where role Like \"%"+Search+"%\" && job_control=true ";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
		Job post = new Job();
		post.setCompnay_name(rs.getString("company_name"));
		post.setCompany_id(rs.getLong("company_id"));
		post.setLocation(rs.getString("location"));
		post.setPosted_on(rs.getDate("posted_on"));
		
		if(rs.getString("job_description").length()>55){
		 jobdis = rs.getString("job_description").substring(0, 55);
		jobdis = jobdis+"........";}
		else{jobdis = rs.getString("job_description");}
		
		post.setJob_description(jobdis);
		post.setJob_title(rs.getString("job_title"));
		post.setExp_min(rs.getString("min_experience"));
		post.setExp_max(rs.getString("max_experience"));
		post.setJob_id(rs.getLong("job_id"));
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
	public Job getJob(long jobid) {
		Job post = new Job();
		try{
			
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String sql = "select * from "+Job_Table+" where job_id='"+jobid+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
			
			post.setCompnay_name(rs.getString("company_name"));
			post.setCompany_id(rs.getLong("company_id"));
			post.setLocation(rs.getString("location"));
			post.setPosted_on(rs.getDate("posted_on"));
			post.setJob_description(rs.getString("job_description"));
			post.setJob_title(rs.getString("job_title"));
			post.setExp_min(rs.getString("min_experience"));
			post.setExp_max(rs.getString("max_experience"));
			post.setJob_id(rs.getInt("job_id"));
			post.setNationality(rs.getString("nationality"));
			post.setFunction(rs.getString("function"));
			post.setRole(rs.getString("role"));
			post.setIndustry(rs.getString("industry"));
			post.setSkills(rs.getString("skills"));
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
	public void job_apply(long user_id, long job_id,long company_id,String company_name,String user_email,String job_apply_resume,String job_title,long resume_id,boolean isread) {
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnaction();
		try {
			Statement st = con.createStatement();
			String sql = "insert into "+Job_Apply+" (`job_id`,`company_id`,`company_name`,`job_title`,`user_id`,`user_email`,`isread`,`dateandtime`,job_apply_resume,resume_id) VALUES("+job_id+","+company_id+",'"+company_name+"','"+job_title+"',"+user_id+",'"+user_email+"',"+isread+",CURRENT_TIMESTAMP(),'"+job_apply_resume+"',"+resume_id+");";
			System.out.println(sql);
			int i=st.executeUpdate(sql);
		System.out.println("Job Applied"+i);
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}

	@Override
	public int count_resumes(long user_id) {
		Connection con = DBConnector.getConnaction();
		int total_resume=0;
		try {
			Statement st = con.createStatement();
		String sql= "SELECT COUNT(user_resume) AS total_resume FROM crs_resume WHERE user_id="+user_id+"";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		total_resume = rs.getInt("total_resume");
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return total_resume ;
	}

	@Override
	public String item(String item) {
		// TODO Auto-generated method stub
		String resume_name="";
		try {
		Connection con = DBConnector.getConnaction();
		Statement st = con.createStatement();
		String sql= "SELECT user_resume FROM crs_resume WHERE idcrs_resume="+item+"";
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		resume_name = rs.getString("user_resume");
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return resume_name;
	}

}
