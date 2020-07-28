package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection(){
<<<<<<< HEAD
        // 본인 database에 맞게끔 수정해주세요.
        // return getConnection("jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&useSSL=false","connectuser","connect123!@#");
    	return getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
=======
        return getConnection("jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false","connectuser","connect123!@#");
    	// return getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger"); // Oracle 버전
>>>>>>> 6a8e7501abac37ad1a372ba49caabe1142482439
    			
    }

    public static Connection getConnection(String dbURL, String dbId, String dbPassword){
        Connection conn = null;
        try{
<<<<<<< HEAD
            // Class.forName("com.mysql.jdbc.Driver");
        	Class.forName("oracle.jdbc.driver.OracleDriver");
=======
            Class.forName("com.mysql.jdbc.Driver");
        	// Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle 버전
>>>>>>> 6a8e7501abac37ad1a372ba49caabe1142482439
            conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
            System.out.println("연결 성공");
            return conn;
        }catch(Exception ex){
<<<<<<< HEAD
=======
        	ex.printStackTrace();
>>>>>>> 6a8e7501abac37ad1a372ba49caabe1142482439
            throw new RuntimeException("Connection Error");
        }
    }
}
