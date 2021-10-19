<%@page import="xyz.sunnytoday.dto.Post" %>
<%@page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Post> boardAskingList = (List) request.getAttribute("boardAskingList");
%>

<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -전체 카테고리</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/board.css" rel="stylesheet">
    <link href="${cssPath}/paging_style.css" rel="stylesheet">
    <script src="${jsPath}/board_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="How_was_your_day">
    <h2>질문 응답</h2>
    모르는게 있으면 언제든지!
</div>
<hr>

<div>
    <div class="menu-left">
        <div><h2>카테고리</h2></div>
        <div><a href="/board/main">전체 글</a></div>
        <div><a href="/board/list/daily">일상룩</a></div>
        <div><a href="/board/list/buy">지름 게시판</a></div>
        <div><a href="/board/list/share">정보공유</a></div>
        <div><a href="/board/list/asking">질문 응답</a></div>
        <div><a href="/board/list/mine">내가 쓴 글</a></div>
    </div>

    <section class="main-board">
        <div id='full_article'>질문 응답</div>

        <table>
            <thead>
            <tr class="division">
                <th>썸네일</th>
                <th>제목/본문</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="6" style="background-color: #fdfdb9"><a
                        href="/board/detail?postno=${requestScope.notice[0].post_no}">[공지] ${requestScope.notice[0].title}</a>
                </td>
            </tr>
            <tr>
                <td colspan="6" style="background-color: #fdfdb9"><a
                        href="/board/detail?postno=${requestScope.notice[1].post_no}">[공지] ${requestScope.notice[1].title}</a>
                </td>
            </tr>
            <c:forEach items="${list }" var="boardAskingList">
                <tr>
                    <td rowspan="2">
                        <c:choose>
                            <c:when test="${empty boardMainList.file }">
                                <img class="thumbnail" src="${pageContext.request.contextPath}/resources/img/no-img.PNG"
                                     alt="no picture">
                            </c:when>
                            <c:otherwise>
                                <img class="thumbnail"
                                     src="${pageContext.request.contextPath}/upload/${boardMainList.file.thumbnail_url}"
                                     alt="picture">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td id='title'>
                        <a href="/board/detail?postno=${boardAskingList.post.post_no }">
                            <c:choose>
                                <c:when test="${fn:length(boardAskingList.post.title) > 14}">
                                    <c:out value="${fn:substring(boardAskingList.post.title,0,13)}"/>....
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${boardAskingList.post.title }"/>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                    <td rowspan="2">
                            ${boardAskingList.nick }
                    </td>
                    <td rowspan="2">
                            <%-- 		<jsp:useBean id="askingToday" class="java.util.Date"></jsp:useBean> --%>
                            <%-- 		<fmt:parseNumber value="${askingToday.time / (1000 * 60 * 60 * 24)}" var="nowDays" integerOnly="true" /> --%>
                            <%-- 		<fmt:parseNumber value="${boardAskingList.post.write_date.time / (1000 * 60 * 60 * 24)}" var="regDays" integerOnly="true" /> --%>
                            <%-- 		<c:set value="${nowDays - regDays }" var="dayDiff" /> --%>
                            <%-- 		<c:choose> --%>
                            <%-- 			<c:when test="${dayDiff == 0 }"> --%>
                            <%-- 				<fmt:formatDate value="${boardAskingList.post.write_date }" pattern="HH:mm:ss"/> --%>
                            <%-- 			</c:when> --%>
                            <%-- 			<c:otherwise> --%>
                            <%-- 				<fmt:formatDate value="${boardAskingList.post.write_date }" pattern="yyyy.MM.dd"/> --%>
                            <%-- 			</c:otherwise> --%>
                            <%-- 		</c:choose>	 --%>
                            ${boardAskingList.post.write_date }
                    </td>
                    <td rowspan="2">${boardAskingList.post.hit}</td>
                </tr>
                <tr>
                    <td id='content'>
                        <c:choose>
                            <c:when test="${fn:length(boardAskingList.post.content) > 14}">
                                <c:out value="${fn:substring(boardAskingList.post.content,0,13)}"/>....
                            </c:when>
                            <c:otherwise>
                                <c:out value="${boardMainList.post.content }"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </section>


</div>

<div class="btnWriteStart">
    <button class="btnWrite">글쓰기</button>
</div>

<div class="searchArea">
    <form action="/board/search?" method="get">
        <input type="hidden" name="boardTitle" value="asking"/>
        <select name="select">
            <option value="title">제목</option>
            <option value="content">본문</option>
            <option value="nick">작성자</option>
        </select>
        <input type="text" name="keyword" placeholder="검색어 입력"/>
        <button class="search">검색</button>
    </form>
</div>


<div id='paging'>
    <c:import url="../layout/boardTitlePaging.jsp"/>
</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
