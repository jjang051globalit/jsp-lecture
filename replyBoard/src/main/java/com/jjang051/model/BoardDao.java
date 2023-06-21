package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jjang051.mybatis.MybatisConnectionFactory;

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
		boardDto.setRegroup(max+1);
		boardDto.setRelevel(1);
		boardDto.setRestep(1);
		
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.insert("writeBoard",boardDto);
		if(result>0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}

	public List<BoardDto> getList(HashMap<String, Integer> pageMap) {
		
		List<BoardDto> boardList = null;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		boardList = sqlSession.selectList("getList",pageMap);
		sqlSession.close();
		return boardList;
		
	}
	public int updateHit(int id) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.update("updateHit",id);
		if(result>0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result; 
		
	}
	public BoardDto getView(int id) {
		BoardDto boardDto = null;
		updateHit(id);
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		boardDto = sqlSession.selectOne("getView",id);
		sqlSession.close();
		return boardDto;
	}

	public int deleteBoard(int id) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.update("deleteBoard",id);
		if(result>0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
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

	public List<BoardDto> search(String category, String searchWord) {
		List<BoardDto> searchList = null;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		HashMap<String, String> searchMap = new HashMap<>();
		searchMap.put("category", category);
		searchMap.put("searchWord", searchWord);
		
		
		searchList = sqlSession.selectList("search",searchMap);
		return searchList;
	}
}


































