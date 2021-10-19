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
		<h2>신고하기</h2>
		신고하는 사유를 작성해주세요
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
<div id='full_article'>신고하기</div>

<c:forEach items="${list }" var="map">

<form action="/board/report" id="report" method="post">
<input type="hidden" name="comments_no" value="${map.c.comments_no}">
<input type="hidden" name="post_no" value="${map.p.post_no}">
<input type="hidden" name="user_no" value="${userno}"/>
<input type="hidden" name="target_no" value="${map.m.userno}">

<table id="reportTable">
<tr id="reportTableWidth">
	<td>제목</td>
	<td>${map.p.title }</td>
</tr>
<tr id="reportUserNick">
	<td>작성자</td>
	<td>${map.m.nick }</td>
</tr>

<tr class="selectReportReason">
	<td colspan="2">사유선택</td>
</tr>

<tr class="selectReportReason">
	<td colspan="2" class="reportReason">
	<input type="radio" name="report_reason" value="advertisement" id="ad"> 
	<label for="ad"> 부적절한 홍보 댓글/게시글</label>
	</td>
</tr>
<tr class="selectReportReason">
	<td colspan="2" class="reportReason">
	<input type="radio" name="report_reason" value="pornography" id="porn">
	<label for="porn"> 음란성 또는 청소년에게 부적합한 내용</label>
	</td>
</tr>
<tr class="selectReportReason">
	<td colspan="2" class="reportReason">
	<input type="radio" name="report_reason" value="defamation" id="defam">
	<label for="defam"> 명예훼손/사생활 침해 및 저작권 침해</label>
	</td>
</tr>
<tr class="selectReportReason" id="reportReasontr">
	<td colspan="2" class="reportReason">
	<input type="radio" name="report_reason" value="etc" id="etc">
	<label for="etc">기타</label>
	</td>
</tr>

<tr>
	<td colspan="2" class="reportReason" id="reportReasonDetail">
	<label for="detail">
	상세 이유를 작성해주세요!
	</label>
	<p
	id="reportDetail-hint"
	class="input-hint">
	불편하셨던 이유를 상세히 적어주시면 신고 처리에 큰 도움이 됩니다. 감사합니다.
	</p>
	<textarea id="detail" name="report_detail" aria-describedby="reportDetail-hint"></textarea>
	</td>
</tr>

<tr>
	<td id="sendBtntd">
	<button type="button" id="sendBtn">전송</button>
	</td>
	<td id="cancelBtntd">
	<button type="button" id="cancelBtn">취소</button>
	</td>
</tr>

</table>
</form>
</c:forEach>

</section>
</div>
<div class="clearArea"></div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>

</body>
</html>