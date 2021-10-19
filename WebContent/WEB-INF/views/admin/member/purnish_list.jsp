<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 헤더 파일 불러오기 -->
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<style type="text/css">
#searchBar{
	background:#ccc;
}
#purnish_table {
	display:fixed;
}
#purnish_table > thead {
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
	$('#revokehBtn').click(function(){
		$('#purnish').submit();
	})
});

</script>

<title>제재 목록</title>
<!-- 스타일 쪽에 header랑 body로 레이아웃을 나눠서
	id를 body로 줘야 해요 -->
<div id ="body" class="container">
<h1>제재 목록</h1>
<hr>
<form action="<%=request.getContextPath() %>/admin/purnish/list" id="search" method="get">

<div class="text-left" id="searchBar">
	<label>&nbsp;회원 검색</label>
	<select name="select_option">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	
</div>

<div class="text-left">
		<button class="btn btn-primary" id="revokehBtn" type="button">복구</button>
</div>

</form>

<form action="<%=request.getContextPath()%>/admin/purnish/list" method="post" id="purnish">
<table class="table" id="purnish_table">
<thead>
<tr style="font-size:1em">
	<th><input type="checkbox" name="select_all" class="check-all"></th>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>휴대폰</th>
	<th>이메일</th>
	<th>제재</th>
	<th>정지기간</th>
</tr>
</thead>
<% int i =0; %>
<c:forEach items="${list }" var="map">
<tbody>

<tr style="font-size:0.75em">
	<td><input type="checkbox" name="cb<%=i %>" value="${map.b.ban_no }" class="ab"></td>
	<td>${map.b.ban_no }</td>
	<td><a href="<%=request.getContextPath() %>/admin/purnish/view?ban_no=${map.b.ban_no }">${map.m.userid }</a></td>
	<td>${map.m.nick }</td>
	<td>${map.m.phone }</td>
	<td>${map.m.email }</td>
	<c:choose>
		<c:when test="${map.b.ban_type eq 'W'}">
			<td>게시물</td>
		</c:when>
		<c:when test="${map.b.ban_type eq 'L'}">
			<td>로그인</td>
		</c:when>
	</c:choose>
	<td>${map.b.expiry_date }</td>
</tr>
<% i++; %>

</tbody>

</c:forEach>
</table>

</form>

<c:import url="/WEB-INF/views/admin/layout/ban_paging.jsp"/>
</div>

</body>
</html>