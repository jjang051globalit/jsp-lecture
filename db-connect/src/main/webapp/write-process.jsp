<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String pUserName = request.getParameter("userName");
	String pTitle = request.getParameter("title");
	String pContents = request.getParameter("contents");
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "jjang051";
	String pw = "1234";
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = "insert into board values (seq_board.nextval,?,?,?,sysdate,0)";
	Class.forName(driver);
	conn = DriverManager.getConnection(url, id, pw);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, pUserName);
	pstmt.setString(2, pTitle);
	pstmt.setString(3, pContents);
	int result = pstmt.executeUpdate(); // select 를 제외한 나머지 update, delete , insert 
	if(result > 0) { 
		response.sendRedirect("list.jsp"); 
	} else {
		out.println("<script>alert('서버오류입니다. 잠시뒤에 다시 시도해 주세요.');history.back();</script>");
	}
%>













