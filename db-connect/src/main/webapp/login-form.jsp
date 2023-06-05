<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <form action="login-process.jsp" method="post">
      <div>
        <label><span>ID</span><input type="text" name="userId" /></label>
      </div>
      <div>
        <label><span>PW</span><input type="password"  name="userPw" /></label>
      </div>
      <div>
        <button>LOGIN</button>
      </div>
    </form>
  </body>
</html>
