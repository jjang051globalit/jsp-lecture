<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container-sm mt-5">
	<table class="table">
		<colgroup>
			<col style="width: 50px">
			<col>
			<col style="width: 150px">
			<col style="width: 250px">
			<col style="width: 50px">
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
			<c:forEach items="${boardList}" var="boardDto" varStatus="status">
				<tr>
					
					<td>${pageDto.total - pageDto.pagePerList*(clickPage-1) - status.index }</td>
					<td><a href="../board/view?id=${boardDto.id}&clickPage=${clickPage}">${boardDto.title }</a></td>
					<td>${boardDto.name }</td>
					<td>${boardDto.regDate }</td>
					<td>${boardDto.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<a class="page-link" 
				href="" aria-label="Previous"> 
					<span aria-hidden="true">처음으로</span>
				</a>
			</li>
			<c:if test="${pageDto.pageStart ne 1 }">
			<li class="page-item">
				<a class="page-link" 
				href="../board/list?clickPage=${pageDto.pageStart - pageDto.pageBlock + (pageDto.pageBlock-1) }" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			</c:if>
			<c:forEach begin="${pageDto.pageStart }" end = "${pageDto.pageEnd}" step="1" var="page" varStatus="status" >
				<li class="page-item ${page == clickPage?'active':''}">
					<a class="page-link" 
					href="../board/list?clickPage=${page}">${page}</a>
				</li>
			</c:forEach>
			<c:if test="${pageDto.pageEnd ne pageDto.pageTotal }">
				<li class="page-item">
					<a class="page-link" 
					href="../board/list?clickPage=${pageDto.pageStart+pageDto.pageBlock }" 
						aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			<li class="page-item">
				<a class="page-link" 
				href="../board/list?clickPage=${pageDto.pageStart - pageDto.pageBlock + (pageDto.pageBlock-1) }" aria-label="Previous"> 
					<span aria-hidden="true">마지막으로</span>
				</a>
			</li>
		</ul>
	</nav>

	<div class="mt-5">
		<a href="../board/write" class="btn btn-primary">WRITE</a>
		<!-- <a href="" class="btn btn-danger">DELETE</a> -->
	</div>
</div>
<%@ include file="../include/footer.jsp"%>






