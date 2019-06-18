package com.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class User {
private long userid;
private String firstname;
private String password;
private String repassword;
private String lastname;
private String nationality;
private String gender;
private String email;
private int type;
private boolean status;
private long mobileno;
private String resume;  



public long getUserid() {
	return userid;
}
public void setUserid(long userid) {
	this.userid = userid;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}

public String getRepassword() {
	return repassword;
}
public void setRepassword(String repassword) {
	this.repassword = repassword;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public long getMobileno() {
	return mobileno;
}
public void setMobileno(long mobileno) {
	this.mobileno = mobileno;
}
public String getResume() {
	return resume;
}
public void setResume(String resume) {
	this.resume = resume;
}


}
