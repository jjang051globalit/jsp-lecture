<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pUserId = request.getParameter("userId");
	String pUserPw = request.getParameter("userPw");


	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "jjang051";
	String pw = "1234";
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "select * from member where id = ? and password = ?";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(url,id,pw);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,pUserId);
	pstmt.setString(2,pUserPw);
	rs = pstmt.executeQuery();
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	
	// pageContext < request < session < applicationContext
	
	if(rs.next()) {
		String userId = rs.getString("id");
		String userPw = rs.getString("password");
		String userName = rs.getString("name");
		
		//out.println("로그인 성공");
		// 직접 주소창을 바꾸는 거
		pageContext.setAttribute("pageUserId", userId);
		request.setAttribute("userId", userId);
		session.setAttribute("userId", userId);
		/* RequestDispatcher dispatcher =  request.getRequestDispatcher("login-ok.jsp");
		dispatcher.forward(request, response); */
		//response.sendRedirect("login-ok.jsp?userId="+userId);
		System.out.println(request.getParameter(userId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("login-ok.jsp");
		dispatcher.forward(request, response);
		//request.getRequestDispatcher("login-ok.jsp").forward(request, response);
		
	} else {
		out.println("로그인 실패");
	}
%>












