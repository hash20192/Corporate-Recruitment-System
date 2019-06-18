package com.dao;

import java.util.List;

import com.bean.Admin;
import com.bean.Company;
import com.bean.Job;
import com.bean.User;

public interface Admin_DB_Methods {

	String USER_TABLE="crs_admin_info";
	String DB_NAME="crs";
	String JOB_TABLE="crs_jobpost";
	
public void insert(String firstname,String lastname,String email,String gender,String password,long mobileno);
public void update(long admin_id,String firstname, String lastname, String email,String gender, String password,long mobileno);
public void ad(long userid,boolean and);
public void ad1(long comapanyid,boolean and);
public void ad2(long jobid,boolean and);
public void delete(String adminid);
public Admin Allselect(long adminid);
public Admin select(String adminemail);
public List<User> selectAll ();
public List<Company> selectAl();
public List<Job> all_jobs(long company_id);
}
