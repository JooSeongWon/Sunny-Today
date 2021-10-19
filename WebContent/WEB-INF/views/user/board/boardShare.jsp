<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% 
	List<Post> boardShareList = (List) request.getAttribute("boardShareList");
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
		<h2>정보공유</h2>
		혼자만 알기 아까운 정보! 나눠봅시다!
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
<div id='full_article'>정보공유</div>
<table>
<thead>
	<tr class="division">
		<th>썸네일</th>
		<th>제목/본문</th>
		<th>작성자</th>
		<th>게시글평점</th>
		<th>날짜</th>
		<th>추천수</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td colspan="6">공지글</td>
	</tr>
	<tr>
		<td colspan="6">공지글</td>
	</tr>
<c:forEach items="${list }" var="boardShareList">
<tr>
	<td rowspan="2">
	<c:choose>
	<c:when test="${empty boardMainList.file }">
	<img class="thumbnail" src="https://via.placeholder.com/40" alt="no picture">
	</c:when>
	<c:otherwise>
	<img class="thumbnail" src="${pageContext.request.contextPath}/upload/${boardShareList.file.thumbnail_url}" alt="no picture">
	</c:otherwise>
	</c:choose>
	</td>
	<td id='title'>
		<a href="/board/detail?postno=${boardShareList.post.post_no }">
		<c:choose>
			<c:when test="${fn:length(boardShareList.post.title) > 20}">
				<c:out value="${fn:substring(boardShareList.post.title,0,19)}"/>....
			</c:when>
			<c:otherwise>
				<c:out value="${boardShareList.post.title }"/>
			</c:otherwise> 
		</c:choose>
		</a>
	</td>
	<td rowspan="2">
		${boardShareList.nick }
	</td>
	<td rowspan="2">
		<div id='circle-grade'>평점</div>
	</td>
	<td rowspan="2">
		${boardShareList.post.write_date }
	</td>
	<td rowspan="2">추천수</td>
</tr>
<tr>
	<td id='content'>
			<c:choose>
			<c:when test="${fn:length(boardShareList.post.content) > 20}">
				<c:out value="${fn:substring(boardShareList.post.content,0,19)}"/>....
			</c:when>
			<c:otherwise>
				<c:out value="${boardShareList.post.content }"/>
			</c:otherwise> 
		</c:choose>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
</section>
</div>
<div id='paging'>
<c:import url="../layout/boardTitlePaging.jsp" />
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
