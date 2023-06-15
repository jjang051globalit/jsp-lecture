package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public ArrayList<BoardDto> getList() {
		ArrayList<BoardDto> boardList = null;
		getConnection();
		String sql = "select * from board order by id desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boardList = new ArrayList<>();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setId(rs.getInt("id"));
				boardDto.setUserId(rs.getString("userId"));
				boardDto.setName(rs.getString("name"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setRegDate(rs.getString("regDate"));
				boardDto.setHit(rs.getInt("hit"));
				boardList.add(boardDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	public void updateHit(int id) {
		getConnection();
		String sql = "update board set hit = hit + 1 where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public BoardDto getView(int id) {
		BoardDto boardDto = null;
		updateHit(id);
		getConnection();
		String sql = "select * from board where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto = new BoardDto();
				boardDto.setId(rs.getInt("id"));
				boardDto.setUserId(rs.getString("userId"));
				boardDto.setName(rs.getString("name"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setRegDate(rs.getString("regDate"));
				boardDto.setHit(rs.getInt("hit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDto;
	}

	public int deleteBoard(int id) {
		int result = 0;
		getConnection();
		String sql = "delete from board where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int modifyBoard(BoardDto boardDto) {
		int result = 0;
		getConnection();
		String sql = "update board set  title = ?, name = ?, contents = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getName());
			pstmt.setString(3, boardDto.getContents());
			pstmt.setInt(4, boardDto.getId());
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}


































