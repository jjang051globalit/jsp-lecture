<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file = "../include/header.jsp" %>
<body>
  <form action="../member/loginProcess" method="post">
    <div class="container-xl">
      <div class="row mt-5 justify-content-center">
        <div class="col-6">
          <div class="form-floating mb-3">
            <input type="text" name="userId" class="form-control" id="floatingInput" placeholder="아이디를 입력해 주세요." />
            <label for="floatingInput">ID</label>
          </div>
          <div class="form-floating mb-3">
            <input type="password" name="userPw" class="form-control" id="floatingPassword" placeholder="Password" />
            <label for="floatingPassword">Password</label>
          </div>
          <div class="text-center">
            <button type="submit" class="btn btn-primary btn-lg">Login</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</body>
<%@ include file = "../include/footer.jsp" %>
