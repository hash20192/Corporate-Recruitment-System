package com.dao;

import java.util.List;

import com.bean.Job;

public interface Job_Search_Methods {
	String Job_Table = "crs_jobpost" ;
	String Job_Apply = "job_apply";
	
	public List<Job> select_jobs(String Search);
	public Job getJob(long jobid);
	public void job_apply(long user_id,long job_id,long company_id,String company_name,String user_email,String job_title,String job_apply_resume,long resume_id,boolean isread);
	public int count_resumes(long user_id);
	public String item(String item);
}
