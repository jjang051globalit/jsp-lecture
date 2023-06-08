<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("utf-8");
   String pLoggedUserId=(String)session.getAttribute("loggedUserId");
   String pNewPw = request.getParameter("newUserPw");
      
   String driver = "oracle.jdbc.OracleDriver";
   String url="jdbc:oracle:thin:@localhost:1521:xe";
   String id="jjang051";
   String pw = "1234";
   
   Connection conn = null;
   PreparedStatement pstmt= null;
   ResultSet rs =null;
   
   String sql = "update member set password = ? where id = ?";
   
   Class.forName(driver);
   conn = DriverManager.getConnection(url,id,pw);
   pstmt=conn.prepareStatement(sql);
   pstmt.setString(1,pNewPw);
   pstmt.setString(2,pLoggedUserId);

   int result = pstmt.executeUpdate();
   if(result > 0){
      session.invalidate();
      response.sendRedirect("login-form.jsp");
   }else{
      out.println("<script>alert('서버오류입니다.'); history.back();</script>");
   }
   
%>