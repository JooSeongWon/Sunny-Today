<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<title>문의 목록</title>
<style type="text/css">

#searchBar{
	background:#ccc;
}

#question_table > thead {
	background:#DFD;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		console.log("searchBtn clicked");
		$("#search").submit()
	});
	
	$('.check-all').click(function(){
		$('.ab').prop('checked', this.checked);
	})
	$('#deleteBtn').click(function(){
		console.log('#deleteBtn');
		$('#deleteList').submit();
	})
});
</script>

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
<form action="<%=request.getContextPath() %>/admin/member/question" id="deleteList" method="post">

<div class="text-left">
	<button id="deleteBtn" type="button" class="btn btn-primary">삭제</button>
</div>

<table class="table" id="question_table">
<thead>
<tr>
	<th><input type="checkbox" name="select_all" class="check-all"></th>
	<th>No.</th>
	<th>처리 상황</th> 
	<th>제목</th>
	<th>아이디</th>
	<th>작성일</th>
</tr>
</thead>
<% int i=0; %>
<c:forEach items="${list }" var="map">

<tbody>

<tr>
	<td><input type="checkbox" name="ch<%=i %>" class="ab" value="${question.question_no }"></td>
	<td>${map.q.question_no }</td>
	<c:choose>
		<c:when test="${map.p.answer eq '' or map.p.answer eq null }">
			<td>[처리 전]</td>
		</c:when>
		<c:when test="${map.p.answer ne '' and map.p.answer ne null }">
			<td>[처리 완료]</td>
		</c:when>
	</c:choose>
	<td><a href="<%=request.getContextPath() %>/admin/answer/view?question_no=${question.question_no }">${question.title }</a></td>
	<td>${map.m.id }</td>
	<td>${map.p.write_date }</td>
</tr>
<% i++; %>
</c:forEach>
</tbody>

</table>
</form>

<c:import url="/WEB-INF/views/admin/layout/question_paging.jsp"/>
</div>


</body>
</html>