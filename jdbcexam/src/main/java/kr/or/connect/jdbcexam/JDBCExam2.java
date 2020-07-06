package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam2 {
	public static void main(String[] args) {
		
		// roleId, description을 주고 role 테이블에 삽입하는 메서드 
		int roleId = 500;
		String description = "CTO";
		
		Role role = new Role(roleId, description);
		RoleDao dao = new RoleDao();
		int insertCount = dao.addRole(role);
		System.out.println(insertCount);
		
		
		
	}
}
