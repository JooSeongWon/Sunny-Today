<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-09-30
  Time: 오전 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div id="navbar">

        <div class="navbar__buttons">
            <a href="${pageContext.request.contextPath}/" class="navbar__home">
                <i class="fas fa-sun"></i>
                <p class="navbar__title">오늘도 맑음</p>
            </a>
            <i class="fas fa-bars navbar__bars"></i>
        </div>
        <ul class="navbar__list">
            <a href="<%= request.getContextPath() %>/schedule">
                <li class="navbar__item">내 일정</li>
            </a>
            <a href="<%= request.getContextPath() %>/board/main">
                <li class="navbar__item">커뮤니티</li>
            </a>
            <a href="#">
                <li class="navbar__item">마이페이지</li>
            </a>
            <a href="<%= request.getContextPath() %>/message">
                <li class="navbar__item">쪽지</li>
            </a>
            <a href="#">
                <li class="navbar__item">문의</li>
            </a>
        </ul>
    </div>
</nav>