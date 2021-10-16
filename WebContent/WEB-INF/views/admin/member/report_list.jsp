<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 헤더 파일 불러오기 -->
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<style type="text/css">
#searchBar{
	background:#ccc;
}

#report_table > thead {
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
		console.log("deleteBtn clicked")
		$('#deleteForm').submit();
	})
});

</script>

<title>신고 목록</title>
<!-- 스타일 쪽에 header랑 body로 레이아웃을 나눠서
	id를 body로 줘야 해요 -->
<div id ="body" class="container">
<h1>신고 목록</h1>
<hr>
<form action="<%=request.getContextPath() %>/admin/member/report" id="search" method="get">

<div class="text-left" id="searchBar">
	
	<label>&nbsp;카테고리 선택</label>
	<select name="select_category">
		<option value="all">전체</option>
		<option value="post">게시글</option>
		<option value="comment">댓글</option>
	</select>
	
	<label>&nbsp;회원 검색</label>
	<select name="select_search">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
</div>


</form>

<form action="<%=request.getContextPath() %>/admin/member/report" method="post" id="deleteForm">

<div class="text-left">
		<button class="btn btn-primary" id="deleteBtn" type="button">삭제</button>
</div>
<table class="table" id="member_table">
<thead>
<tr>
	<th><input type="checkbox" name="select_all" class="check-all"></th>
	<th>No.</th>
	<th>신고 형태</th>
	<th>처리상황</th>
	<th>제목</th>
	<th>아이디</th>
	<th>신고일자</th>
</tr>
</thead>
<tbody>
<% int i =0; %>
<c:forEach items="${list }" var="map">
<tr>
	<td><input type="checkbox" name="ch<%=i %>" class="ab" value="${map.rp.report_no }"></td>
	<td>${map.rp.report_no }</td>
	<c:choose>
		<c:when test="${map.rp.report_type eq 'P' and map.rp.report_type eq 'P' }">
			<td>게시물</td>
		</c:when>
		<c:when test="${map.rp.report_type eq 'C' and map.rp.report_type eq 'C' }">
			<td>댓글</td>
		</c:when>
	</c:choose>
	
	<c:choose>
		<c:when test="${map.rp.execute_result eq null or map.rp.execute_result eq '' }">
			<td>[처리 전]</td>
		</c:when>
	
		<c:when test="${map.rp.execute_result ne null and map.rp.execute_result ne '' }">
			<td>[처리 완료]</td>
		</c:when>

	</c:choose>
	
	<td><a href="<%=request.getContextPath() %>/admin/report/execute?report_no=${map.rp.report_no }&report_type=${map.rp.report_type }">${map.rpc.title }</a></td>
	<td>${map.m.userid }</td>
	<td>${map.rp.report_date }</td>
<% i++; %>	

</tr>
</c:forEach>
</tbody>
</table>
<input type="hidden" name="report_type" value="${map.rp.report_type }">
</form>

<c:import url="/WEB-INF/views/admin/layout/report_paging.jsp"/>

</div>
</body>
</html>