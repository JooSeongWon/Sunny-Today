<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-10-13
  Time: 오후 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>회원가입 테스트</title>
</head>
<body>
<h3>테스트 용이라 유효성검사, 중복확인 안합니다. 더미 데이터 생성용으로 쓰세요</h3>
<form action="${pageContext.request.contextPath}/test/join" method="post">
  <input type="text" name="userId" maxlength="20" placeholder="아이디 4자이상"><br>
  <input type="password" name="userPw" maxlength="50" placeholder="패스워드 8자이상"><br>
  <input type="email" name="email" placeholder="이메일주소"><br>
  <input type="text" name="nick" placeholder="닉네임"><br>
  <input type="text" name="gender" maxlength="1" placeholder="성별 A-미선택/M-남자/F-여자"><br>
  <input type="text" name="phone" maxlength="11" placeholder="핸드폰번호 - 없이"><br>
  <input type="text" name="birth" maxlength="8" placeholder="생일 ex)20210822"><br>
  <button>회원가입</button>
</form>

</body>
</html>