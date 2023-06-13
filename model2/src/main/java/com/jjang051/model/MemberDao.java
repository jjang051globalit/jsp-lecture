package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
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
	
	public int insertMember(MemberDto memberDto) {
		int result = 0;
		getConnection();
		String sql =  "insert into member values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getName());
			pstmt.setString(3, memberDto.getPassword());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setInt(5, memberDto.getZonecode());
			pstmt.setString(6, memberDto.getAddress());
			pstmt.setString(7, memberDto.getDetailAddress());
			pstmt.setString(8, memberDto.getExtraAddress());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	
	
	
	public MemberDto loginMember(MemberDto memberDto) {
		MemberDto loggedMemberDto = null;
		getConnection();
		String sql = "select * from member where id = ? and password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loggedMemberDto = new MemberDto();
				String userId =  rs.getString("id");
				String userName = rs.getString("name");
				loggedMemberDto.setId(userId);
				loggedMemberDto.setName(userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return loggedMemberDto;
	}

	public int idCheck(String userId) {
		int result = 0;
		getConnection();
		String sql = "select count(*) as count from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public MemberDto getMemberInfo(String userId) {
		MemberDto infoMemberDto = null;;
		getConnection();
		String sql =  "select * from member where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				infoMemberDto = new MemberDto();
				infoMemberDto.setId(rs.getString("id"));
				infoMemberDto.setName(rs.getString("name"));
				infoMemberDto.setEmail(rs.getString("email"));
				infoMemberDto.setAddress(rs.getString("address"));
				infoMemberDto.setZonecode(rs.getInt("zonecode"));
				infoMemberDto.setDetailAddress(rs.getString("detailAddress"));
				infoMemberDto.setExtraAddress(rs.getString("extraAddress"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return infoMemberDto;
	}

	public int modifyMember(MemberDto memberDto) {
		int result= 0;
		
		getConnection();
		String sql = "update member set name = ?,email = ?,zonecode = ?,address = ?,detailAddress = ?,extraAddress = ?"
				+" where id = ? and password = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getName());
			pstmt.setString(2, memberDto.getEmail());
			pstmt.setInt(3, memberDto.getZonecode());
			pstmt.setString(4, memberDto.getAddress());
			pstmt.setString(5, memberDto.getDetailAddress());
			pstmt.setString(6, memberDto.getExtraAddress());
			pstmt.setString(7, memberDto.getId());
			pstmt.setString(8, memberDto.getPassword());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
}













