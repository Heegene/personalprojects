package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> resultlist = new ArrayList<>();
    	// DB 연결 객체
    	Connection conn = null;
    	// 명령문 담는 객체
    	PreparedStatement ps = null;
    	// 결과를 담는 객체
    	ResultSet rs = null;
    	System.out.println("");
    	
    	try {
			// 드라이버 로딩 
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		// DB 연결
    		conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
    		String sql =
    				"SELECT name, phone, companyname, createdate FROM businesscard WHERE  name like %'?'% ";
    		// 명령문 담음
    		ps.getConnection().prepareStatement(sql);
    		// parameter 지정
    		ps.setString(1, keyword);
    		// 결과값 resultset에 담음
    		rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			String name = rs.getString(1);
    			String phone = rs.getString(2);
    			String companyName = rs.getString(3);
    			Date createDate = rs.getDate(4);
    			
    			BusinessCard businesscard = new BusinessCard(name, phone, companyName);
    			
    			resultlist.add(businesscard);
    			
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return resultlist;
    	// list로 반환해야함 
    }

//    public BusinessCard addBusinessCard(BusinessCard businessCard){
//	// 구현하세요.
//    }
}
