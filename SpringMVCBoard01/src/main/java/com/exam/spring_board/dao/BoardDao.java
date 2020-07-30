package com.exam.spring_board.dao;

import java.net.ConnectException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	private BoardDao() {
	}

	private static BoardDao dao = new BoardDao();

	public static BoardDao getInstance() {
		// singleton 적용
		return dao;
	}

	// Connection Pool 구현부
	public Connection getConnection() {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
