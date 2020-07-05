package kr.or.connect.jdbcexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.jdbcexam.dto.Role;

public class RoleDao {
	// DB 주소나 계정명, 비밀번호는
	// 계속해서 상수처럼 사용될 것이므로
	// 아예 static 으로 선언해놓고 시작
	
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
	// 이클립스에서 타임존을 인식하지 못하는 오류가 있으므로 serverTimezone=UTC 를 url뒤에 붙임
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	// role을 가져오는 메소드
	public Role getRole(Integer roleId) {
		Role role = null;
		Connection conn = null;
		// DB와 연결하는 객체
		PreparedStatement ps = null;
		// 명령문 담는 객체
		ResultSet rs = null;
		// 결과 담는 객체
		
		// 뭔가 연결하는 건 예외처리를 할 상황이 많으므로
		// try catch 를 사용
		
		try {
			// 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			// 쿼리를 String 변수로 정의
			// 인자값 바뀔것이므로 ? 를 사용함 (preparedstatement의 특징)
			// 물음표가 바인딩된 부분만 바뀜 
			String sql = "SELECT description, role_id FROM role WHERE role_id= ?";
			ps = conn.prepareStatement(sql);
			// 첫번째 파라미터인 파라미터인덱스는 물음표의 순서임
			// 물음표 하나뿐이므로 1 기재 
			ps.setInt(1, roleId);
			// rs 객체에 결과값 담음
			rs = ps.executeQuery();
			
			// 결과가 비지 않았으면 조건 가져오기
			if (rs.next()) {
				String description = rs.getString(1);
				// 첫번째 description(첫번째 컬럼 가져오란뜻)
				int id = rs.getInt("role_id");
				// 두번째 role_id(숫자로 안하고 이름으로 해도 가져와짐)
				role = new Role(id, description);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally { 
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}
		
		// 접속, 연결했던 부분 꼭 닫아야 함
		return role;
		
	}
	
	// 데이터를 입력하는 메소드 
	public int addRole(Role role) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName( "com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO role(role_id, description) VALUES (?, ?)";
		
		try {
		conn = DriverManager.getConnection(dburl,dbuser,dbpasswd);
			
			ps = conn.prepareStatement(sql);
			
			// 물음표에 대한 값을 바인딩
			ps.setInt(1,  role.getRoleId());
			ps.setString(2, role.getDescription());
			
			insertCount = ps.executeUpdate();
			// executeupdate라는 쿼리 사용
			// SELECT의 경우=executequery
			// insert, delete, update = executeupdate
			// 이 쿼리는 실행되면 인트값을 받아옴
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return insertCount;
		
		
	}
	
	
}
