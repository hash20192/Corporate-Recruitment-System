package com.dao;


import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.Part;

import com.bean.Job;
import com.bean.Resume;
import com.bean.User;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;




public interface User_DB_Methods {

	String USER_TABLE="crs_user_info";
	String DB_NAME="crs";
	String USER_RESUME="crs_resume";

	
public void insert(String firstname,String lastname,String email,String password,long mobileno);
public void update(String firstname, String lastname, String email, long mobileno,String resume,long user_id);
public void delete(String userid);
public User select(long userid);
public List<Resume> select_resume(long userid); 
public User select(String useremail);
public Image preview_resume(String fullname,String email,String phone,String website,String address1,String address2,String address3);
public ByteArrayOutputStream create_pdf(String fullname,String email,String phone,String website,String address1,String address2,String address3);
public void insert_resume(String fname,long userid,String resume_tite);
public List<Job> check_applied_resume(String user_email,long resume_id);
public Resume get_resume_details(long resume_id);
public int update_resume_details(long resume_id,String resume_name,String resume_title,String job_ids,long user_id);
}
