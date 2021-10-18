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
    <link href="${cssPath}/question.css" rel="stylesheet">
    <script src="${jsPath}/question_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="questionPageInfo">
	<h2>문의페이지</h2>
	불편사항 빠르게 처리 도와드리겠습니다
</div>
<hr>
	
	
	
<table id="questionTable">

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
	
	<c:forEach items="${list }" var="questionList">
	<tr class="questionListContent">
		<td>${questionList.question.question_no }</td>
		<td>
				<c:if test="${questionList.question.user_no eq loginMember.userno && not empty loginMember.userno}">
					<a href="<%=request.getContextPath() %>/question/detail?questionno=${questionList.question.question_no }">
					<c:choose>
						<c:when test="${fn:length(questionList.question.title) > 20}">
							<c:out value="${fn:substring(questionList.question.title,0,19)}"/>....
						</c:when>
						<c:otherwise>
							<c:out value="${questionList.question.title }"/>
						</c:otherwise> 
					</c:choose>
					</a>
				</c:if>
				
				<c:if test="${questionList.question.user_no ne loginMember.userno && not empty loginMember.userno}">
				<a class="notMtcLogin" href="/#">
					<c:choose>
						<c:when test="${fn:length(questionList.question.title) > 20}">
							<c:out value="${fn:substring(questionList.question.title,0,19)}"/>....
						</c:when>
						<c:otherwise>
							<c:out value="${questionList.question.title }"/>
						</c:otherwise> 
					</c:choose>
				</a>
				</c:if>
				
				<c:if test="${empty loginMember.userno }">
				<a class="notLogin" href="/#">
					<c:choose>
						<c:when test="${fn:length(questionList.question.title) > 20}">
							<c:out value="${fn:substring(questionList.question.title,0,19)}"/>....
						</c:when>
						<c:otherwise>
							<c:out value="${questionList.question.title }"/>
						</c:otherwise> 
					</c:choose>
				</a>
				</c:if>	
		</td>
		<td>
			<c:if test="${empty questionList.question.answer }">
			<button id="prcsn">처리중</button>
			</c:if>
			<c:if test="${not empty questionList.question.answer }">
			<button id="prcsnCmplt">처리완료</button>
			</c:if>
		</td>
		<td>${questionList.nick }</td>
		<td>${questionList.question.write_date }</td>
	</tr>
	</c:forEach>

	<tr>
		<c:if test="${empty loginMember.userno }">
			<td colspan="5" class="questionWrite"><button class="notLoginbtnWrite">문의작성</button></td>
		</c:if>
		<c:if test="${not empty loginMember.userno }">
			<td colspan="5" class="questionWrite"><button class="btnWrite">문의작성</button></td>
		</c:if>
	</tr>
	</tbody>

</table>
	
	
	
<div id='paging'>
<c:import url="../layout/questionPaging.jsp" />
</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
