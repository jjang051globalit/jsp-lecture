<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
	<div class="container-sm mt-5">
		<table class="table">
		<colgroup>
			<col style="width:250px">
			<col >
		</colgroup>
		<tbody>
			
			<tr>
				<th>Title</th>
				<td>${boardDto.title }</td>
			</tr>
			<tr>
				<th>Writer</th>
				<td>${boardDto.name }</td>
			</tr>
			<tr>
				<th>Date</th>
				<td>${boardDto.regDate }</td>
			</tr>
			<tr>
				<th>Hit</th>
				<td>${boardDto.hit }</td>
			</tr>
			<tr>
				<th>Contents</th>
				<td class="contents">${boardDto.contents }</td>
			</tr>
		</tbody>
	</table>
	</div>
<%@ include file="../include/footer.jsp"%>






