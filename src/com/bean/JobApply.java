package com.bean;

import java.util.Date;

public class JobApply {
private Long job_id;
private Long company_id;
private String company_name;
private String job_title;
private String user_email;
private Long user_id;
private int count;
private Date date;

public Long getJob_id() {
	return job_id;
}
public void setJob_id(Long job_id) {
	this.job_id = job_id;
}
public Long getCompany_id() {
	return company_id;
}
public void setCompany_id(Long company_id) {
	this.company_id = company_id;
}
public String getCompany_name() {
	return company_name;
}
public void setCompany_name(String company_name) {
	this.company_name = company_name;
}
public String getJob_title() {
	return job_title;
}
public void setJob_title(String job_title) {
	this.job_title = job_title;
}
public String getUser_email() {
	return user_email;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;
}
public Long getUser_id() {
	return user_id;
}
public void setUser_id(Long user_id) {
	this.user_id = user_id;
}
public boolean isIsread() {
	return isread;
}
public void setIsread(boolean isread) {
	this.isread = isread;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
private boolean isread;
}
