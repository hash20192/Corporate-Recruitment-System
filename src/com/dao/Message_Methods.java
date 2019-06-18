package com.dao;

import java.util.List;

import com.bean.Messages;

public interface Message_Methods {
	public static final String MESSAGE_TABLE="message" ;
	public static final String MESSAGE_THREAD_TABLE="message_thread" ;
	public static final String MESSAGE_PLACEHOLDER_TABLE="message_placeholder" ;
public void message_send(String to,String from ,String message,String title);
public List<Messages> all_massages(String user_email);
public List<Messages> get_conversation(String thread_id);
public void reply_send(String to,String from,String thread_id,String message,String title);
public void delete_messages(String thread_id);
}
