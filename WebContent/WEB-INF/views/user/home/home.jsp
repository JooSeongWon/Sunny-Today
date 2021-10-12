<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/home_style.css" rel="stylesheet">
    <script src="${jsPath}/home_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<%--home section--%>
<section id="service">
    <div class="service__wrap">
        <div class="weather">
            <div class="weather__main">
                <p class="weather__date"><fmt:formatDate value="<%=new Date()%>" pattern="yy.MM.dd HH:mm"/></p>
                <c:import url="weather-card.jsp"/>
                <p class="weather__region">서울 / 대한민국 <i class="fas fa-map-marker-alt"></i></p>
                <p class="weather__description">오늘 날씨는 흐리고 어쩌고 저쩌고~~</p>
            </div>
            <div class="slider">
                <div class="slider__arrow"><i class="fas fa-chevron-left"></i></div>
                <div class="slider__wrap">
                    <div class="slider__contents">
                        <c:import url="weather-card.jsp"/>
                        <c:import url="weather-card.jsp"/>
                        <c:import url="weather-card.jsp"/>
                        <c:import url="weather-card.jsp"/>
                        <c:import url="weather-card.jsp"/>
                    </div>
                </div>
                <div class="slider__arrow"><i class="fas fa-chevron-right"></i></div>
            </div>
        </div>

        <div class="costume">
            <div class="costume__title">오늘은 이런 의상 어때요?</div>

            <div class="costume__clothes">
                <div class="clothes">
                    <img class="clothes__image" src="http://via.placeholder.com/100x100" alt="상의">
                    <div class="clothes__description">반팔 티셔츠</div>
                </div>
                <div class="clothes">
                    <img class="clothes__image" src="http://via.placeholder.com/100x100" alt="하의">
                    <div class="clothes__description">청 바지</div>
                </div>
            </div>

            <div class="costume__gender">
                <button class="gender__male">남</button>
                <button class="gender__female">여</button>
            </div>
            <div class="costume__refresh"><i class="fas fa-sync"></i></div>
        </div>
    </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
