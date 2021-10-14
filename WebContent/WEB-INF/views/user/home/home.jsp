<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="xyz.sunnytoday.common.repository.Forecast" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<%--오늘날씨 & 의상추천--%>
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

<%--스케쥴 카드--%>
<section id="schedule">
    <h1 class="schedule__title">다가오는 일정</h1>
    <div class="schedule-slider">
        <div class="schedule-cards">
            <%--단기예보에서 꺼낼날씨--%>
            <%
                int sForecastSize = ((List<Forecast>) request.getAttribute("sForecast")).size();
                int shortEnd = 0;
                int index = 0;
            %>
            <c:forEach var="i" begin="1" end="${fn:length(requestScope.sForecast)}">
                <%index++;%>
                <c:if test="${requestScope.sForecast[fn:length(requestScope.sForecast)  - i].baseDate eq requestScope.mForecast[0].baseDate}">
                    <%shortEnd = sForecastSize - (index + 1);%>
                </c:if>
            </c:forEach>


            <%
                pageContext.setAttribute("shortEnd", shortEnd);

                //중기예보 사용 포맷
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();
            %>

            <c:if test="${pageScope.shortEnd >= 7 && fn:length(requestScope.sForecast) > 7}">
                <c:forEach var="i" begin="7" end="${pageScope.shortEnd}">
                    <%
                        calendar.add(Calendar.DATE, 1);

                        String addClass;
                        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                            addClass = " holiday";
                        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            addClass = " saturday";
                        } else {
                            addClass = "";
                        }
                    %>
                    <div class="schedule-card">
                        <div class="card__wrap">
                            <div class="card__month"><%=monthFormat.format(calendar.getTime())%>
                            </div>

                            <div class="card__top">
                                <div class="card__day<%=addClass%>"><%=dayFormat.format(calendar.getTime())%>
                                </div>
                                <div class="card__holiday-title"></div>
                                <div class="card__modify"><a href="#"><i class="fas fa-pencil-alt"></i></a></div>
                            </div>
                            <div class="card__body"></div>
                            <div class="card__bottom">
                                <c:choose>
                                    <c:when test="${requestScope.sForecast[i].weather eq '맑음'}">
                                        <i class="fas fa-sun"></i>
                                    </c:when>
                                    <c:when test="${requestScope.sForecast[i].weather eq '구름많음'}">
                                        <c:choose>
                                            <c:when test="${requestScope.sForecast[i].chanceOfRain >= 40}">
                                                <i class="fas fa-cloud-sun-rain"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fas fa-cloud-sun"></i>
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
                                <div class="card__forecast">
                                    <i class="fas fa-temperature-high temperatures"></i>
                                    <span class="weather-card__temperatures">${requestScope.sForecast[i].temperature}</span>℃
                                    &nbsp;&nbsp;
                                    <i class="fas fa-tint rain"></i>
                                    <span class="weather-card__rain">${requestScope.sForecast[i].chanceOfRain}</span>%
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <%
                //혹시 단기예보가 비었을 수 있으니 캘린더 날짜 갱신
                Forecast firstForecast = ((List<Forecast>) request.getAttribute("mForecast")).get(0);
                calendar.setTime(dateFormat.parse(firstForecast.getBaseDate()));
                calendar.add(Calendar.DATE, -1);
            %>

            <c:forEach var="i" begin="0" end="${fn:length(requestScope.mForecast) -1}">
                <%
                    calendar.add(Calendar.DATE, 1);

                    String addClass;
                    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        addClass = " holiday";
                    } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        addClass = " saturday";
                    } else {
                        addClass = "";
                    }
                %>
                <div class="schedule-card">
                    <div class="card__wrap">
                        <div class="card__month"><%=monthFormat.format(calendar.getTime())%>
                        </div>
                        <div class="card__top">
                            <div class="card__day<%=addClass%>"><%=dayFormat.format(calendar.getTime())%>
                            </div>
                            <div class="card__holiday-title"></div>
                            <div class="card__modify"><a href="#"><i class="fas fa-pencil-alt"></i></a></div>
                        </div>
                        <div class="card__body"></div>
                        <div class="card__bottom">
                            <c:choose>
                                <c:when test="${requestScope.mForecast[i].weather eq '맑음'}">
                                    <i class="fas fa-sun"></i>
                                </c:when>
                                <c:when test="${requestScope.mForecast[i].weather eq '구름많음'}">
                                    <c:choose>
                                        <c:when test="${requestScope.mForecast[i].chanceOfRain >= 40}">
                                            <i class="fas fa-cloud-sun-rain"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-cloud-sun"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${requestScope.mForecast[i].weather eq '흐림'}">
                                    <c:choose>
                                        <c:when test="${requestScope.mForecast[i].chanceOfRain >= 40}">
                                            <i class="fas fa-cloud-showers-heavy"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-cloud"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                            <div class="card__forecast">
                                <i class="fas fa-temperature-high temperatures"></i>
                                <span class="weather-card__temperatures">${requestScope.mForecast[i].temperature}</span>℃
                                &nbsp;&nbsp;
                                <i class="fas fa-tint rain"></i>
                                <span class="weather-card__rain">${requestScope.mForecast[i].chanceOfRain}</span>%
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<section id="best-posts">
    <div class="best-posts__left">

    </div>
    <div class="bes-posts__right">

    </div>

</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
