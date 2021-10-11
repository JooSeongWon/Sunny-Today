<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<h1>1:1 문의 관리</h1>
<hr>
<form action="<%=request.getContextPath() %>/admin/member/question" id="search" method="get">
<div class="text-left" id="searchBar">
	<label>&nbsp;회원 검색</label>
	<select name="select_option">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	
</div>
</form>
<table class="table">
<tr>
	<th>No.</th>
	<th>처리 상황</th>
	<th>제목</th>
	<th>아이디</th>
	<th>작성일</th>
</tr>
<c:forEach items="${list }" var="question">
<tr>
	<td>${question.question_no }</td>
	<td>[처리상황]</td>
	<td>${question.title }</td>
	<td>${question.id }</td>
	<td>${question.wirte_date }</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>