<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -전체 카테고리</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/board.css" rel="stylesheet">
    <script src="${jsPath}/board_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="How_was_your_day">
	<h2></h2>
	당신의 이야기를 들려주세요 :)
</div>
<hr>


<div class="container">

<h1>게시글 상세보기</h1>
<hr>

<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td colspan="3">${detailBoard.boardno }</td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3">${detailBoard.title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${detailBoard.userid }</td>
<td class="info">닉네임</td><td>${nick }</td>
</tr>

<tr>
<td class="info">조회수</td><td>${detailBoard.hit }</td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3">${detailBoard.writeDate }</td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>

<tr><td colspan="4">${viewBoard.content }</td></tr>

</table>

<!-- 첨부파일 -->
<div>
<c:if test="${not empty boardFile }">
<a href="/upload/${boardFile.storedname }" download="${boardFile.originname }">${boardFile.originname }</a>
</c:if>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div>


<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
