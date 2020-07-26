package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection(){
        // 본인 database에 맞게끔 수정해주세요.
        // return getConnection("jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&useSSL=false","connectuser","connect123!@#");
    	return getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
    			
    }

    public static Connection getConnection(String dbURL, String dbId, String dbPassword){
        Connection conn = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver");
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
            System.out.println("연결 성공");
            return conn;
        }catch(Exception ex){
            throw new RuntimeException("Connection Error");
        }
    }
}
