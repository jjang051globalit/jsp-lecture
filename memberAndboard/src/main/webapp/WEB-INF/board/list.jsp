<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container-sm mt-5">
	<table class="table">
		<colgroup>
			<col style="width:50px">
			<col >
			<col style="width:150px">
			<col style="width:250px">
			<col style="width:50px">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">Title</th>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Hit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items = "${boardList}" var = "boardDto">
			<tr>
				<td>${boardDto.id }</td>
				<!-- query paramamter  get -->
				<td><a href="../board/view?id=${boardDto.id}">${boardDto.title }</a></td>
				<td>${boardDto.name }</td>
				<td>${boardDto.regDate }</td>
				<td>${boardDto.hit }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="mt-5">
		<a href="../board/write" class="btn btn-primary">WRITE</a>
		<!-- <a href="" class="btn btn-danger">DELETE</a> -->
	</div>
</div>
<%@ include file="../include/footer.jsp"%>






