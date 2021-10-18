<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -계정정보 찾기</title>
    <%--find css--%>
    <link rel="stylesheet" href="${requestScope.cssPath}/find_style.css">
    <%--find js--%>
    <script src="${requestScope.jsPath}/fins_script.js" defer></script>
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<section class="find active">
    <c:if test="${requestScope.find eq 'id'}">
        <div class="find__wrap">
            <i class="fas fa-search"></i>
            <h1>아이디 찾기</h1>
            <h3>가입하실때 인증하신 이메일 주소를 입력해주세요.</h3>
            <div class="wrap"><input type="email" class="email input" placeholder="여기에 입력하세요"></div>
            <div class="button">아이디 찾기</div>
        </div>
    </c:if>
    <c:if test="${requestScope.find eq 'pw'}">
        <div class="find__wrap">
            <i class="fas fa-search"></i>
            <h1>비밀번호 찾기</h1>
            <h3>아이디를 입력해주세요.</h3>
            <div class="wrap"><input type="text" class="userid input" placeholder="여기에 입력하세요"></div>
            <h3>이메일 주소를 입력해주세요.</h3>
            <div class="wrap"><input type="email" class="email input" placeholder="여기에 입력하세요"></div>
            <div class="button">비밀번호 찾기</div>
        </div>
    </c:if>
</section>


<section class="find">
        <div class="find__wrap">
            <i class="fas fa-paper-plane"></i>
            <h1>발송완료!</h1>
            <h3>이메일을 확인해주세요.</h3>
            <a href="${pageContext.request.contextPath}/login" class="login-link">로그인 화면으로</a>
        </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
