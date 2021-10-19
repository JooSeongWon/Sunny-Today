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
            <c:if test="${not empty sessionScope.admin and (sessionScope.admin eq 'A' or sessionScope.admin eq 'S')}">
            <a class="enter__admin" href="${pageContext.request.contextPath}/admin/check">관리자 페이지</a>
            </c:if>
            <c:if test="${empty sessionScope.userno}">
                <a href="<%=request.getContextPath() %>/login">LOGIN |</a>
                <a href="<%=request.getContextPath() %>/join"> JOIN US</a>
            </c:if>
            <c:if test="${not empty sessionScope.userno}">
                <c:if test="${empty sessionScope.pictureThumbnail}"><i class="far fa-smile"></i></c:if>
                <c:if test="${not empty sessionScope.pictureThumbnail}"><img class="avatar"
                                                                             src="${pageContext.request.contextPath}/upload/${sessionScope.pictureThumbnail}"
                                                                             alt="프로필 사진"></c:if>
                &nbsp;&nbsp;${sessionScope.nick}님, 안녕하세요!
                <a href="<%=request.getContextPath() %>/logout"> LogOut</a>
            </c:if>
        </div>
        <h1 class="header__title">오늘도 맑음 <i class="fas fa-sun"></i></h1>
    </div>
</header>
<a href="#" class="top">
    <i class="fas fa-arrow-up"></i>

</a>
