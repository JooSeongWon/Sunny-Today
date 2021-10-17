<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 - 문의 페이지</title>

    <%--페이지별 css/ js--%>
<%--     <link href="${cssPath}/board.css" rel="stylesheet"> --%>
    <script src="${jsPath}/question_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="How_was_your_day">
	<h2>문의페이지</h2>
	불편사항 빠르게 처리 도와드리겠습니다
</div>
<hr>
	
	
	
<table>

	<thead>
		<tr class="division">
			<th>번호</th>
			<th>제목</th>
			<th>처리상태</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>

	<tbody>
	
<%-- 	<c:forEach items="" var=""> --%>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>처리상태</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
<%-- 	</c:forEach> --%>

	<tr>
			<td><button class="btnWrite">문의작성</button></td>
	</tr>
	</tbody>

</table>
	
	
	






<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
