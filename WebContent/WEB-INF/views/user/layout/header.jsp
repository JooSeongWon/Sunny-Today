<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-09-30
  Time: 오전 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div id="header">
        <div class="enter">
            <c:if test="${empty sessionScope.userno}">
                <a href="<%=request.getContextPath() %>/test/login">LOGIN |</a>
                <a href="<%=request.getContextPath() %>/test/join"> JOIN US</a>
            </c:if>
            <c:if test="${not empty sessionScope.userno}">
                ${sessionScope.nick} 님, 안녕하세요! &nbsp;&nbsp;&nbsp;
                <a href="<%=request.getContextPath() %>/test/logout"> LogOut</a>
            </c:if>
        </div>
        <h1 class="header__title">오늘도 맑음 <i class="fas fa-sun"></i></h1>
    </div>
</header>
<a href="#" class="top">
    <i class="fas fa-arrow-up"></i>
</a>