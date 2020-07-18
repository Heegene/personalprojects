package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul";
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public List<BusinessCard> searchBusinessCard(String keyword) {
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
			String sql = "SELECT name, phone, companyname, createdate FROM businesscard WHERE name LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String companyName = rs.getString(3);
				Timestamp createDate = rs.getTimestamp(4);

				BusinessCard businesscard = new BusinessCard(name, phone, companyName);
				businesscard.setCreateDate(createDate);

				resultlist.add(businesscard);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultlist; // list 반환
	}

	public void addBusinessCard(BusinessCard businessCard) throws SQLException {
		int addCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		String name = businessCard.getName();
		String phone = businessCard.getPhone();
		String companyName = businessCard.getCompanyName();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO businesscard VALUES (?, ?, ?, sysdate())";

		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, companyName);

			addCount = ps.executeUpdate();
			System.out.println(addCount + " 건의 입력이 완료되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		}

	}
}
