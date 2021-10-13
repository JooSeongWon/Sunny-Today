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
    <script src="${jsPath}/home_script.js" defer></script>

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
                <div class="weather-card">
                    <div class="weather-card__image">
                        <c:choose>
                            <c:when test="${requestScope.sForecast[0].weather eq '맑음'}">
                                <i class="fas fa-sun"></i>
                            </c:when>
                            <c:when test="${requestScope.sForecast[0].weather eq '구름많음'}">
                                <c:choose>
                                    <c:when test="${requestScope.sForecast[0].chanceOfRain >= 40}">
                                        <i class="fas fa-cloud-sun-rain"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-cloud-sun"></i>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${requestScope.sForecast[0].weather eq '흐림'}">
                                <c:choose>
                                    <c:when test="${requestScope.sForecast[0].chanceOfRain >= 40}">
                                        <i class="fas fa-cloud-showers-heavy"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-cloud"></i>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </div>
                    <p class="weather-card__detail">
                        <%-- span 태그들 기온/강수확률 --%>
                        <i class="fas fa-temperature-high temperatures"></i>
                        <span class="weather-card__temperatures">${requestScope.sForecast[0].temperature}</span>℃
                        &nbsp;&nbsp;
                        <i class="fas fa-tint rain"></i>
                        <span class="weather-card__rain">${requestScope.sForecast[0].chanceOfRain}</span>%
                    </p>
                </div>
                <p class="weather__region">
                    ${requestScope.r1}
                    <c:if test="${not empty requestScope.r2}"> / ${requestScope.r2}</c:if>
                    <i class="fas fa-map-marker-alt"></i></p>
                <p class="weather__description">지금 날씨는 [${requestScope.sForecast[0].weather}] 입니다.<br>모바일 데이터 환경에서는 정확하지
                    않을 수 있습니다.</p>
            </div>
            <div class="slider">
                <div class="slider__arrow"><i class="fas fa-chevron-left"></i></div>
                <div class="slider__wrap">
                    <div class="slider__contents">

                        <c:forEach var="i" begin="1" end="6">
                            <%String am = "sun";%>
                            <c:if test="${requestScope.sForecast[i].intBastTime >= 20 || requestScope.sForecast[i].intBastTime < 6}">
                                <%am = "moon";%>
                            </c:if>
                            <div class="weather-card">
                                <div class="weather-card__image">
                                    <c:choose>
                                        <c:when test="${requestScope.sForecast[i].weather eq '맑음'}">
                                            <i class="fas fa-<%=am%>"></i>
                                        </c:when>
                                        <c:when test="${requestScope.sForecast[i].weather eq '구름많음'}">
                                            <c:choose>
                                                <c:when test="${requestScope.sForecast[i].chanceOfRain >= 40}">
                                                    <i class="fas fa-cloud-<%=am%>-rain"></i>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fas fa-cloud-<%=am%>"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${requestScope.sForecast[i].weather eq '흐림'}">
                                            <c:choose>
                                                <c:when test="${requestScope.sForecast[i].chanceOfRain >= 40}">
                                                    <i class="fas fa-cloud-showers-heavy"></i>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fas fa-cloud"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>

                                </div>
                                <div class="weather-card__time">${requestScope.sForecast[i].intBastTime} : 00</div>
                                <div class="weather-card__detail">
                                        <%-- span 태그들 기온/강수확률 --%>
                                    <i class="fas fa-temperature-high temperatures"></i>
                                    <span class="weather-card__temperatures">${requestScope.sForecast[i].temperature}</span>℃
                                    <i class="fas fa-tint rain"></i>
                                    <span class="weather-card__rain">${requestScope.sForecast[i].chanceOfRain}</span>%
                                </div>
                            </div>
                        </c:forEach>

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
