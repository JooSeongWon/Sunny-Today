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

    <script>
        'use strict'

        function insertSample() {
            document.querySelector('#userno').setAttribute('value', '10');
            document.querySelector('#userid').setAttribute('value', 'sunny');
            document.querySelector('#nick').setAttribute('value', 'rainy');
            document.querySelector('#admin').setAttribute('value', 'N');
            document.querySelector('#pictureno').setAttribute('value', 'Null');
        }

        function logout() {
            const form = document.createElement('form');
            form.setAttribute('method', 'post');
            form.setAttribute('action', '${pageContext.request.contextPath}/test/login');

            const hiddenField = document.createElement('input');
            hiddenField.setAttribute('type', 'hidden');
            hiddenField.setAttribute('name', 'logout');
            hiddenField.setAttribute('value', 'true');
            form.appendChild(hiddenField);

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>

<%--비로그인 상태에서는 로그인 입력창을 보여줍니다.--%>
<c:choose>
    <c:when test="${empty sessionScope.member}">
        <form action="${pageContext.request.contextPath}/test/login" method="post">
            <label>userNo : <input type="text" name="userno" id="userno"></label>&nbsp;&nbsp;&nbsp;
            <label>userId : <input type="text" name="userid" id="userid"></label>&nbsp;&nbsp;&nbsp;
            <label>nick : <input type="text" name="nick" id="nick"></label>&nbsp;&nbsp;&nbsp;
            <label>nick : <input type="text" name="admin" id="admin"></label>&nbsp;&nbsp;&nbsp;
            <label>nick : <input type="text" name="pictureno" id="pictureno"></label><br>
            <button>로그인</button> &nbsp;&nbsp;&nbsp;
            <button onclick="insertSample();" type="button">샘플 값 채우기</button>
        </form>
    </c:when>
    <c:otherwise>
        로그인 되어 있습니다. userId =  ${sessionScope.member.userid}  /   Nick = ${sessionScope.member.nick} <br>
        <button onclick="logout();">로그아웃</button>
    </c:otherwise>
</c:choose>

</body>
</html>