<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% 
	List<Post> boardMainList = (List) request.getAttribute("boardMainList");
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
    <script src="${jsPath}/home_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

	<div><h1>커뮤니티</h1></div>
	<div>당신의 오늘은 어떠셨나요?</div>
	<hr>
	
<div class="menu-left">
	<div><h1>카테고리</h1></div>
	<div>전체 글</div>
	<div>일상룩</div>
	<div>지름 게시판</div>
	<div>정보공유</div>
	<div>질문 응답</div>
	<div>내가 쓴 글</div>
</div>

<section class="main-board">
<div id='full_article'>전체 글</div>
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
<%-- 	<% for(int i=0; i<boardMainList.size(); i++) {%> --%>
<tr>
	<td rowspan="2"><img class="thumbnail" src="http://via.placeholder.com/40" alt="썸네일"></td>
	<td id='title'>제목</td>
	<td rowspan="2"><i class="far fa-smile"></i>사용자</td>
	<td rowspan="2">평점</td>
	<td rowspan="2">작성된 날짜</td>
	<td rowspan="2">추천수</td>
</tr>
<tr>
	<td id='content'>본문입니다</td>
</tr>
<%-- <%} %> --%>
</tbody>
</table>
</section>

<%--footer--%>
<%-- <c:import url="../layout/footer.jsp"/> --%>
</body>
</html>
