<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/layout.css" />

<script src="../js/bootstrap.bundle.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
			<a href="/"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
				jjang051 </a>
			<ul class="nav nav-pills">
				
				
				
				<c:choose>
					<c:when test="${loggedMember eq null }">
						<li class="nav-item"><a href="../member/login" class="nav-link">login</a></li>
						<li class="nav-item"><a href="../member/join" class="nav-link">join</a></li>
						<!-- <li class="nav-item"><a href="../board/list" class="nav-link">게시판</a></li> -->
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a href="../member/logout" class="nav-link">logout</a></li>
						<li class="nav-item"><a href="../member/info?userId=${loggedMember.id }" class="nav-link">${loggedMember.name }</a></li>
						<li class="nav-item"><a href="../board/list" class="nav-link">게시판</a></li>						
						
					</c:otherwise>	
				</c:choose>
				<%-- <c:if test="${empty loggedMember}">
					<li class="nav-item"><a href="login-form.jsp" class="nav-link">login</a></li>
					<li class="nav-item"><a href="join-form.jsp" class="nav-link">join</a></li>
				</c:if>
				<c:if test="${ not empty loggedMember }">
					<li class="nav-item"><a href="logout.jsp" class="nav-link">logout</a></li>
					<li class="nav-item"><a href="info.jsp" class="nav-link">${loggedMemberName }</a></li>
				</c:if> --%>
			</ul>
		</header>
	</div>
	
	
	
	
	