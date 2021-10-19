<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% 
	List<Post> boardMineList = (List) request.getAttribute("boardMineList");
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
		<h2>내가 쓴 글</h2>
		당신이 쓴 소중한 게시글을 확인하세요
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
<div id='full_article'>내가 쓴 글</div>
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
<c:forEach items="${list }" var="boardMineList">
<tr>
	<td rowspan="2">
	<c:choose>
	<c:when test="${empty boardMineList.file }">
	<img class="thumbnail" src="https://via.placeholder.com/40" alt="no picture">
	</c:when>
	<c:otherwise>
	<img class="thumbnail" src="${pageContext.request.contextPath}/upload/${boardMineList.file.thumbnail_url}" alt="no picture">
	</c:otherwise>
	</c:choose>
	</td>
	<td id='title'>
		<a href="/board/detail?postno=${boardMineList.post.post_no }">
		<c:choose>
			<c:when test="${fn:length(boardMineList.post.title) > 20}">
				<c:out value="${fn:substring(boardMineList.post.title,0,19)}"/>....
			</c:when>
			<c:otherwise>
				<c:out value="${boardMineList.post.title }"/>
			</c:otherwise> 
		</c:choose>
		</a>
	</td>
	<td rowspan="2">
		${boardMineList.nick }
	</td>
	<td rowspan="2">
		<div id='circle-grade'>평점</div>
	</td>
	<td rowspan="2">
		${boardMineList.post.write_date }
	</td>
	<td rowspan="2">추천수</td>
</tr>
<tr>
	<td id='content'>
	<c:choose>
			<c:when test="${fn:length(boardMineList.post.content) > 20}">
				<c:out value="${fn:substring(boardMineList.post.content,0,19)}"/>....
			</c:when>
			<c:otherwise>
				<c:out value="${boardMineList.post.content }"/>
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
<form action="/board/buy" method="get">
	<select name="select">
			<option value="title" >제목</option>
			<option value="content">본문</option>
			<option value="nick">작성자</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어 입력" />
	<button class="search">검색</button>
</form>
</div>


<div id='paging'>
<c:import url="../layout/boardTitlePaging.jsp" />
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
