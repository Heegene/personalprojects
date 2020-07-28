package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection(){
        return getConnection("jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false","connectuser","connect123!@#");
    	// return getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger"); // Oracle 버전
    			
    }

    public static Connection getConnection(String dbURL, String dbId, String dbPassword){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        	// Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle 버전
            conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
            System.out.println("연결 성공");
            return conn;
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new RuntimeException("Connection Error");
        }
    }
}
