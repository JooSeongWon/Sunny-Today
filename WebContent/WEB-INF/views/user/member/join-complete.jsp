<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -가입완료!</title>
    <%--join css--%>
    <link rel="stylesheet" href="${requestScope.cssPath}/join-c_style.css">
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<section id="complete">
    <div class="complete__wrap">
        <i class="fas fa-user-plus"></i>
        <h3 class="complete__title">가입이 완료되었습니다.</h3>
        <p class="complete__description">
            모든 가입 절차가 마무리 되었습니다! <br>
            이제 오늘도 맑음의 모든 서비스를 이용하실 수 있습니다. <br><br>
            <a href="${pageContext.request.contextPath}/login">로그인 하러 가기!</a>
        </p>
    </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
