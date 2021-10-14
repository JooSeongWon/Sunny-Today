<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -로그인</title>
    <%--login css--%>
    <link rel="stylesheet" href="${requestScope.cssPath}/login_style.css">
    <%--login js--%>
    <script src="${requestScope.jsPath}/jsencrypt.min.js" defer></script>
    <script src="${requestScope.jsPath}/login_script.js" defer></script>
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<section id="login">
    <div class="login-form">
        <div class="login__title">
            <h1>회원 로그인</h1>
            <a class="join-btn" href="#">회원가입</a>
        </div>
        <div class="line"></div>
        <div class="data">
            <label for="input-id"><i class="fas fa-user-tag"></i></label>
            <input id="input-id" class="data__input" name="userId" type="text" maxlength="20" placeholder="아이디">
        </div>
        <div class="data">
            <label for="input-pw"><i class="fas fa-user-lock"></i></label>
            <input id="input-pw" class="data__input" name="userId" type="password" maxlength="20" placeholder="비밀번호">
        </div>
        <div class="etc">
            <label class="label-keep"><input type="checkbox" class="input-keep" name="willKeep" value="Y">로그인 유지</label>
            <div class="find">
                <div class="find__id">아이디 찾기</div>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <div class="find__pw">비밀번호 찾기</div>
            </div>
        </div>

        <div class="buttons">
            <div id="buttons__origin" class="button">로그인</div>
            <div class="line"></div>
            <div id="buttons__naver" class="button">
                <div class="button__logo">N</div>
                NAVER
            </div>
            <div id="buttons__google" class="button">
                <div class="button__logo">G</div>
                GOOGLE
            </div>
        </div>
    </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
