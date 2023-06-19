<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container-sm mt-5">
	<table class="table">
		<colgroup>
			<col style="width: 250px">
			<col>
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
	<div class="mt-5 text-center">
		<a href="../board/write" class="btn btn-primary">WRITE</a>
		<c:if test="${loggedMember.id eq boardDto.userId}">
			<a href="../board/modify?id=${boardDto.id}" class="btn btn-primary">MODIFY</a>
			<a href="../board/delete?id=${boardDto.id}" id="btnDelete" class="btn btn-danger">DELETE</a>
		</c:if>
		<a href="../board/list?clickPage=${param.clickPage }" class="btn btn-danger">LIST</a>
	</div>
	<script>
		const btnDelete = document.querySelector("#btnDelete");
		btnDelete.addEventListener("click",(e)=>{
			e.preventDefault();
			const isDelete = confirm("정말 삭제하시겠습니까?");
			if(isDelete) {
				location.href="../board/delete?id="+${boardDto.id};				
			} 
		});
	</script>
</div>
<%@ include file="../include/footer.jsp"%>






