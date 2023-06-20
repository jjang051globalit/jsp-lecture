<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container-sm mt-5">
	<form action="../board/replyProcess" method="post">
		<input type="text" name="userId" value="${loggedMember.id }">
		<input type="text" name="regroup" value="${param.regroup }">
		<input type="text" name="relevel" value="${param.relevel }">
		<input type="text" name="restep" value="${param.restep }">
		<div class="mb-3">
			<label for="title" class="form-label">제목</label> 
			<input type="text"	class="form-control" name="title" id="title" placeholder="제목을 입력하세요.">
		</div>
		<div class="mb-3">
			<label for="userName" class="form-label">이름</label> 
			<input	type="text" class="form-control" name="userName" id="userName" placeholder="이름을 입력하세요.">
		</div>
		<div class="mb-3">
			<label for="contents" class="form-label">내용</label>
			<textarea class="form-control" id="contents" name="contents" rows="10" id="editor"></textarea>
		</div>
		<div class="text-center">
			<button type="submit" id="btnSubmit" class="btn btn-primary btn-lg">CONFIRM</button>
			<button type="reset" id="btnCancel" class="btn btn-secondary btn-lg">CANCEL</button>
			<a href="../board/list" class="btn btn-primary btn-lg">LIST</a>
		</div>
	</form>
	<script>
      $("#contents").summernote({
        //placeholder: 'Hello Bootstrap 5',
        tabsize: 2,
        height: 100
      });
    </script>
</div>
<%@ include file="../include/footer.jsp"%>






