package com.spring.board;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.*;


public class MySQLConnectionTest {
	static final String DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/connectDB?useSSL=false";
	static final String USERNAME = "connectuser";
	static final String PASSWORD = "connect123!@#";
	
	@Test
	public void getMySQLConnectionTest() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM board";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println("제목은 " + rs.getString("subject") + "입니다. ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (!conn.isClosed()) {
				conn.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!rs.isClosed()) {
				rs.close();
			}
		 	
			
		}
	}
	
	
}
