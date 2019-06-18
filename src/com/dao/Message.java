package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sun.security.action.GetLongAction;

import com.bean.DBConnector;
import com.bean.Messages;
import com.bean.Resume;

public class Message implements Message_Methods {

	@Override
	public void message_send(String to, String from, String message,String title)
	{
		// TODO Auto-generated method stub
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			Statement st2 = con.createStatement(); 
			String sql2 = "SELECT MAX(thread_id) from "+MESSAGE_THREAD_TABLE;
			String sql = "insert into "+MESSAGE_THREAD_TABLE+" (`thread_create_date`,`sender_id`,`receiver_id`) values (now(),'"+from+"','"+to+"')";
			int i = st.executeUpdate(sql);
			ResultSet rs = st2.executeQuery(sql2);
			rs.next();
			long thread_id= rs.getLong("MAX(thread_id)");
			String sql1 = "insert into "+MESSAGE_TABLE+" (`message`,`subject`,`message_to`,`message_from`,`thread`,`datentime`,`is read`) values ('"+message+"','"+title+"','"+to+"','"+from+"','"+thread_id+"',now(),0)";
			Statement st1 = con.createStatement();
			int j = st1.executeUpdate(sql1);
			System.out.println("Rows affected :"+i);
			System.out.println("Rows affected :"+j);
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public List<Messages> all_massages(String user_email) {
		// TODO Auto-generated method stub
		List<Messages> l = new ArrayList<Messages>();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			String sql ="select DISTINCT thread_id from "+MESSAGE_THREAD_TABLE+" where receiver_id='"+user_email+"' || sender_id='"+user_email+"' ";
			ResultSet rs1 = st1.executeQuery(sql);
			String thread_ids="";
			int i=0;
			while(rs1.next()){
				
				if(i==0){
					thread_ids =rs1.getString("thread_id") ;
					i++;
	        	}
	        	else{
	        		thread_ids = thread_ids+","+rs1.getString("thread_id");
	        	}
			}
			System.out.println(thread_ids);
			String Sql1 = "SELECT thread, subject, MIN(message) as message, MIN(message_to) as message_to, MIN(message_from) as message_from ,MIN(datentime) as datentime ,MIN(message_id) as message_id FROM "+MESSAGE_TABLE+" where thread IN ("+thread_ids+") GROUP BY subject,thread ";
			ResultSet rs = st.executeQuery(Sql1);
			
			while(rs.next()){
				Messages m = new Messages();
				m.setTo(rs.getString("message_to"));
				m.setFrom(rs.getString("message_from"));
				m.setTitle(rs.getString("subject"));
				m.setDate(rs.getDate("datentime"));
				m.setId(rs.getLong("message_id"));
				m.setThread(rs.getLong("thread"));
				m.setMessage(rs.getString("message"));
				l.add(m);
			}
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return l;
	}

	@Override
	public List<Messages> get_conversation(String thread_id) {
		// TODO Auto-generated method stub
		List<Messages> l = new ArrayList<Messages>();
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement();
			String Sql = "select * from "+MESSAGE_TABLE+" where thread='"+thread_id+"'";
			ResultSet rs = st.executeQuery(Sql);
			
			while(rs.next()){
				Messages m = new Messages();
				m.setTo(rs.getString("message_to"));
				m.setFrom(rs.getString("message_from"));
				m.setTitle(rs.getString("subject"));
				m.setDate(rs.getDate("datentime"));
				m.setId(rs.getLong("message_id"));
				m.setThread(rs.getLong("thread"));
				m.setMessage(rs.getString("message"));
				l.add(m);
			}
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return l;
	}

	@Override
	public void reply_send(String to, String from, String thread_id,
			String message ,String title) {
		// TODO Auto-generated method stub
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement(); 
			String sql = "insert into "+MESSAGE_TABLE+" (`message`,`subject`,`message_to`,`message_from`,`thread`,`datentime`) values('"+message+"','"+title+"','"+from+"','"+to+"',"+thread_id+",now()) ";
			System.out.println(sql);
			int i = st.executeUpdate(sql);
		
			System.out.println("Rows affected :"+i);
			
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void delete_messages(String thread_id) {
		// TODO Auto-generated method stub
		try{
			Connection con = DBConnector.getConnaction();
			Statement st = con.createStatement(); 
			String sql = "delete from "+MESSAGE_THREAD_TABLE+" where thread_id IN ("+thread_id+") ";
			System.out.println(sql);
			int i = st.executeUpdate(sql);
		
			System.out.println("Rows affected :"+i);
			
			st.close();
			con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

}
