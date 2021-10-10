<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		console.log("searchBtn clicked");
		$("#search").submit()
	});
	
	$('.check-all').click(function(){
		$('.ab').prop('checked', this.checked);
	})
});

</script>
<title>회원 목록</title>

<div id ="body" class="container">
<h1>회원 목록</h1>
<hr>
<form action="<%=request.getContextPath() %>/member/list" id="search" method="post">

<div class="text-left">
	<label>회원 검색</label>
	<select name="select_option">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
</div>

<div class="text-right">
	<button class="btn" type="button">회원 제재</button>
</div>
</form>

<form action="<%=request.getContextPath()%>/member/punish" method="post" id="purnish">
<table class="table">
<tr>
	<th><input type="checkbox" name="select_all" class="check-all"></th>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>방문 횟수</th>
	<th>가일일</th>
</tr>
<% int i =0; %>
<c:forEach items="${list }" var="member">
<tr>
	<td><input type="checkbox" name="cb<%=i %>" class="ab"></td>
	<td>${member.userno }</td>
	<td><a href="<%=request.getContextPath() %>/member/view?userno=${member.userno }">${member.userid }</a></td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
<%-- 	<td>${member.visitor }</td> --%>
	<td>${member.create_date }</td>
</tr>
<% i++; %>
</c:forEach>
</table>
</form>

<c:import url="/WEB-INF/views/admin/layout/paging.jsp"/>
</div>
<%-- <c:import url="/WEB-INF/views/layout/footer.jsp"/> --%>
</body>
</html>