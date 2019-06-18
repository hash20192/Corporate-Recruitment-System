package com.bean;

import java.util.Date;

public class Messages {
private String to;
private String from;
private String title;
private Date date;
private long id;
private long thread ;
private String message;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getThread() {
	return thread;
}
public void setThread(long thread) {
	this.thread = thread;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
