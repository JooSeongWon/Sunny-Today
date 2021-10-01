<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-10-01
  Time: 오후 4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="weather-card">
  <div class="weather-card__image">
    <i class="fas fa-cloud-sun"></i><%--조건문 넣고 날씨에따라 그림 출력--%>
  </div>
  <div class="weather-card__time"><%--시간넣을곳--%></div>
  <p class="weather-card__detail">
    <%-- span 태그들 기온/강수확률 --%>
    <i class="fas fa-temperature-high temperatures"></i>
    <span class="weather-card__temperatures">21</span>℃
    &nbsp;&nbsp;
    <i class="fas fa-tint rain"></i>
    <span class="weather-card__rain">20</span>%
  </p>
</div>