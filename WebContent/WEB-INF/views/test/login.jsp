<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-10-11
  Time: 오후 2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인 테스트</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/test/login" method="post">
    <input type="text" name="userId" placeholder="아이디"><br>
    <input type="password" name="userPw" placeholder="패스워드"><br>
    <button>로그인</button>
</form>
</body>
</html>