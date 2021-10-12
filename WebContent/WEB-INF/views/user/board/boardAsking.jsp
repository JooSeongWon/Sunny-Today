<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${cssPath}/boardmain.css" rel="stylesheet">
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

<div id="btnBox">
	<button id="btnWrite">글쓰기</button>
</div>


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
<c:forEach items="${boardAskingList }" var="boardAskingList">
<tr>
	<td rowspan="2"><img class="thumbnail" src="http://via.placeholder.com/40" alt="썸네일"></td>
	<td id='title'>
		<a href="/board/detail?postno=${boardAskingList.post.post_no }">
		${boardAskingList.post.title }
		</a>
	</td>
	<td rowspan="2">
		<i class="far fa-smile"></i>${boardAskingList.post.user_no }
	</td>
	<td rowspan="2">
		<i class="fas fa-circle fa-3x"></i>
		<div id='circle-grade'>평점</div>
	</td>
	<td rowspan="2">
		${boardAskingList.post.write_date }
	</td>
	<td rowspan="2">추천수</td>
</tr>
<tr>
	<td id='content'>${boardAskingList.post.content }</td>
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
<form action="/board/Asking?search=" method="get">
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
<c:import url="../layout/boardPaging.jsp" />
</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
