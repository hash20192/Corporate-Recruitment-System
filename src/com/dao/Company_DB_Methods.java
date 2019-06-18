package com.dao;

import java.util.List;

import com.bean.Company;
import com.bean.Job;

public interface Company_DB_Methods {
	String USER_TABLE="crs_company_info";
	String DB_NAME="crs";
	String Job_Table="crs_jobpost";
	String JOB_APPLY="job_apply";
	
public Company session_Start(String companyemail);
public void insert(String emr_firstname,String emr_lastname,String emr_email,String emr_gender,String emr_nationality,String emr_password,long emr_mobileno,String emr_address,String company_name,String company_address,boolean company_status,String company_email);
public Company update(String emr_firstname,String emr_lastname,String emr_email,String emr_gender,String emr_nationality,long company_id,long emr_mobileno,String emr_address,String company_name,String company_address,String company_email);
public void update(String firstname, String lastname, String email,String gender,String nationality, long mobileno,String userid);
public void delete(long userid);
public Company select(long userid);
public void insert_Job(long company_id,String company_name,String job_title,String nationality,String location,String min_experience,String max_experience,String skills,String function,String role,String industry,String job_description,String about_company);
public List<Job> select_jobs(long companyid);
public Job job_Edit(long job_id);
public void job_update(String company_name,String job_title,String nationality,String location,String min_experience,String max_experience,String skills,String function,String role,String industry,String job_description,String about_company,long job_id);
public List inbox(long company_id); 
public List job_apply_users(long job_id);
}
