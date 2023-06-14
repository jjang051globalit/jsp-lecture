package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {
	private String driver = "oracle.jdbc.OracleDriver"; 
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private String id = "jjang051"; 
	private String pw = "1234"; 
	
	private Connection conn = null; 
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; 
	// MVC  design pattern  
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void close() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int writeBoard(BoardDto boardDto) {
		int result = 0;
		getConnection();
		String sql = "insert into board values(seq_board.nextval,?,?,?,?,sysdate,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getName());
			pstmt.setString(3, boardDto.getTitle());
			pstmt.setString(4, boardDto.getContents());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}


























