package com.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection getConnaction()  {
		
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/crs";
		String  username = "root";
		String password = "";
		
	
			try {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con = DriverManager.getConnection(url,username,password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return con;
	}

}
