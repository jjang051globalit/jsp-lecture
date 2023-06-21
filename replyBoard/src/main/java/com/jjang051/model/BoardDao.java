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
	
	public int updateRelevel(BoardDto boardDto) {
		int result = 0;
		getConnection();
		String sql = "update replyboard set relevel = relevel + 1 where regroup = ? and relevel > ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardDto.getRegroup());
			pstmt.setInt(2,boardDto.getRelevel());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getMaxRegroup() {
		// 
		int result = 0;
		getConnection();
		String sql = "select nvl( max(regroup),0) as regroupmax from replyboard";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("regroupmax");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//dao
	public int writeBoard(BoardDto boardDto) {
		int result = 0;
		int max = getMaxRegroup();
		//max++;
		getConnection();
		String sql = "insert into replyboard values(seq_replyboard.nextval,?,?,?,?,sysdate,0,?,?,?,1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getName());
			pstmt.setString(3, boardDto.getTitle());
			pstmt.setString(4, boardDto.getContents());
			pstmt.setInt(5, max+1);
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 1);
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
//		String sql = "select * from"
//				+ "(select rownum as no,b.* from "
//				+ "    (select * from replyboard order by id desc) b) where no >= ?  and no <= ?";
		String sql ="select rownum as no, b.* from ("+
							"select * from replyboard order by regroup desc, relevel asc"+
						") b";
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, start);
			//pstmt.setInt(2, end);
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
				boardDto.setRegroup(rs.getInt("regroup"));
				boardDto.setRelevel(rs.getInt("relevel"));
				boardDto.setRestep(rs.getInt("restep"));
				boardDto.setAvailable(rs.getInt("available"));
				
				
				
				boardList.add(boardDto);
			}
			System.out.println(boardList.get(0).getName());
			System.out.println(boardList.get(0).getTitle());
			System.out.println(boardList.get(0).getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	public void updateHit(int id) {
		getConnection();
		String sql = "update replyboard set hit = hit + 1 where id = ?";
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
		String sql = "select * from replyboard where id = ?";
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
				boardDto.setRegroup(rs.getInt("regroup"));
				boardDto.setRelevel(rs.getInt("relevel"));
				boardDto.setRestep(rs.getInt("restep"));
				boardDto.setAvailable(rs.getInt("available"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardDto;
	}

	public int deleteBoard(int id) {
		int result = 0;
		getConnection();
		//String sql = "delete from replyboard where id = ?";
		String sql = "update replyboard set available = 0 where id = ?";
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
		String sql = "update replyboard set  title = ?, name = ?, contents = ? where id = ?";
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

	public double getTotal() {
		double total = 0;
		getConnection();
		String sql = "select count(*) as total from replyboard";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				total =  rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return total;
	}

	public int replyBoard(BoardDto boardDto) {
		int result = 0;
		getConnection();
		String sql = "insert into replyboard values(seq_replyboard.nextval,?,?,?,?,sysdate,0,?,?,?,1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getName());
			pstmt.setString(3, boardDto.getTitle());
			pstmt.setString(4, boardDto.getContents());
			pstmt.setInt(5, boardDto.getRegroup());
			pstmt.setInt(6, boardDto.getRelevel());
			pstmt.setInt(7, boardDto.getRestep());
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public ArrayList<BoardDto> search(String category, String searchWord) {
		ArrayList<BoardDto> searchList = null;
		getConnection();
		//컬럼명은 ? 못씀...
		//컬럼며은 data binding 안됨...
		// all 처리....
		String sql = null;
		String selectedCategory = null;
		if(category.equals("all")) {
			sql ="select * from replyboard where "+category+" like '%' || ? || '%'";
		} else {
			sql ="select * from replyboard where "+category+" like '%' || ? || '%'";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1,category);
			pstmt.setString(1,searchWord);
			
			rs = pstmt.executeQuery();
			searchList = new ArrayList<>();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setId(rs.getInt("id"));
				boardDto.setUserId(rs.getString("userId"));
				boardDto.setName(rs.getString("name"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContents(rs.getString("contents"));
				boardDto.setRegDate(rs.getString("regDate"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegroup(rs.getInt("regroup"));
				boardDto.setRelevel(rs.getInt("relevel"));
				boardDto.setRestep(rs.getInt("restep"));
				boardDto.setAvailable(rs.getInt("available"));
				
				searchList.add(boardDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchList;
	}
}


































