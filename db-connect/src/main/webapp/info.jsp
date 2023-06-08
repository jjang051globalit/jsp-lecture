<!-- prettier-ignore -->
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>
<!-- prettier-ignore -->
<%
	//  db 접속
	
	String pLoggedUserId =  (String) session.getAttribute("loggedUserId");
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "jjang051";
	String pw = "1234";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql =  
			"select id,name,email,address,lpad(zonecode,5,'0') as changeZonecode,"
			+ "detailAddress from member where id = ?";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(url,id,pw);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, pLoggedUserId);
	rs = pstmt.executeQuery();
	String address = null;
	String detailAddress = null;
	String zonecode = null;
	String name = null;
	String email = null;
	String allAddress =  null;
	if(rs.next()) {
		address = rs.getString("address");
		detailAddress = rs.getString("detailAddress");
		zonecode = rs.getString("changeZonecode");
		name = rs.getString("name");
		email = rs.getString("email");
		allAddress= address+ " / "+detailAddress;
	}
	if(detailAddress==null) detailAddress = "상세주소 없음";
	  
	
%>

<div class="container-sm mt-5">
  <table class="table">
    <tbody>
      <tr>
        <th scope="row">ID</th>
        <td><%= pLoggedUserId %></td>
      </tr>

      <tr>
        <th scope="row">Name</th>
        <td><%= name %></td>
      </tr>
      <tr>
        <th scope="row">Email</th>
        <td><%= email %></td>
      </tr>
      
      <tr>
        <th scope="row">주소</th>
        <td><%= address +" / "+ detailAddress %></td>
      </tr>
      <tr>
        <th scope="row">우편번호</th>
        <td><%= zonecode %></td>
      </tr>
    </tbody>
  </table>
  <div class="mt-5">
    <a href="modify.jsp" class="btn btn-info">회원 정보 수정</a>
    <a href="delete.jsp" class="btn btn-danger">회원 탈퇴</a>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
