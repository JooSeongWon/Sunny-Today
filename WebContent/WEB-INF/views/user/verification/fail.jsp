<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -인증오류</title>
    <%--join css--%>
    <link rel="stylesheet" href="${requestScope.cssPath}/verify.css">
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<section id="verify-fail" class="verify">
    <div class="verify__wrap">
        <i class="fas fa-exclamation-triangle"></i>
        <h3 class="verify_title">인증세션 오류.</h3>
        <p class="verify__description">
            링크가 만료되었거나 존재하지 않는 링크입니다.<br>
            올바른 링크에 계속해서 같은문제가 발생할 경우 관리자에게 문의하세요! <br><br>
            <a href="${pageContext.request.contextPath}/">메인으로 이동하기</a>
        </p>
    </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
