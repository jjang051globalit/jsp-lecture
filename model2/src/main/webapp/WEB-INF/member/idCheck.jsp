<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:out value = "${isState}" />
<c:choose>
	<c:when test="${count > 0 }">
		<c:set var="isState" value="false" />	
	</c:when>
	<c:otherwise>
		<c:set var="isState" value="true" />
	</c:otherwise>
</c:choose>
{"isOk":<c:out value="${isState }"></c:out>,"userName":"jjang051"}
