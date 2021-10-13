<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		console.log("searchBtn clicked");
		$("#search").submit()
	});

	$("#setAdminBtn").click(function(){
		console.log("setAdminBtn clicked");
		$("#setAdmin").submit()
	});

	$("#deltAdminBtn").click(function(){
		console.log("delAdminBtn clicked");
		$("#delAdmin").submit()
	});
	
	
});
</script>

<title>관리자 설정</title>

<div id="body" class="container">

<h1>쪽지 전송</h1>
<hr>

<form action="<%=request.getContextPath() %>/admin/set" id="search" method="get">

<div class="text-left" id="searchBar">
	<label>&nbsp;회원 검색
	<input class="search-query" type="text" name="search" placeholder="search"/>
	</label>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
</div>

</form>

<table class="table">
<tr>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>이메일</th>
	<th>가입일</th>
	<th><th>
</tr>
<c:forEach items="${list }" var="member">
<tr>
	<td>${member.userno }</td>
	<td>${member.userid }</td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
	<td>${member.create_date }</td>
	<c:choose>
		<c:when test="${member.admin eq 'N'}">
		<form action="<%=request.getContextPath() %>/admin/set" id="setAdmin" method="post">
			<input type="hidden" id="set" name="userno" value="${member.userno }">
			<input type="hidden" name="val" value="set">
			<td><button class="btn btn-default" id="setAdminBtn">관리자 권한지정</button></td>
		</form>
		</c:when>
		<c:when test="${member.admin ne 'N'}">
		<form action="<%=request.getContextPath() %>/admin/set" id="delAdmin" method="post">
			<input type="hidden" id="del"  name="userno" value="${member.userno }">
			<input type="hidden" name="val" value="del">
			<td><button class="btn btn-danger" id="delAdminBtn">관리자 권한삭제</button></td>
		</form>
		</c:when>
	</c:choose>
</tr>
</c:forEach>
</table>

<c:import url="/WEB-INF/views/admin/layout/adminSet_paging.jsp"/>

</div>
</body>
</html>