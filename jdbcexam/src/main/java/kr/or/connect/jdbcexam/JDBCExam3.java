package kr.or.connect.jdbcexam;

import java.util.Arrays;
import java.util.List;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam3 {
	public static void main(String[] args) {
		
		// Role을 다중으로 가져오는(롤을 리스트 형태로 받아오는) 메소드 테스트
		
		RoleDao dao = new RoleDao();
		List<Role> list = dao.getRoles();
		
		for (Role role : list) {
			System.out.println(role);
		}
		
	}
}
