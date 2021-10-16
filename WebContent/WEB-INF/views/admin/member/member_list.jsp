<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 헤더 파일 불러오기 -->
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<style type="text/css">
#searchBar{
	background:#ccc;
}

#member_table > thead {
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
});
</script>

<title>회원 목록</title>
<!-- 스타일 쪽에 header랑 body로 레이아웃을 나눠서
	id를 body로 줘야 해요 -->
<div id ="body" class="container">
<h1>회원 목록</h1>
<hr>
<form action="<%=request.getContextPath() %>/admin/member/list" id="search" method="get">

<div class="text-left" id="searchBar">
	<label>&nbsp;회원 검색</label>
	<select name="select_option">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	
</div>
<div class="text-right">
		<button class="btn btn-default" id="purnishBtn" type="button">회원 제재</button>
</div>

</form>

<form action="<%=request.getContextPath()%>/admin/member/list" method="post" id="purnish">
<table class="table" id="member_table">
<thead>
<tr>
	<th><input type="checkbox" name="select_all" class="check-all"></th>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>이메일</th>
	<th>가입일</th>
</tr>
</thead>
<tbody>
<% int i =0; %>
<c:forEach items="${list }" var="member">
<tr>
	<td><input type="checkbox" name="cb<%=i %>" class="ab"></td>
	<td>${member.userno }</td>
	<td><a href="<%=request.getContextPath() %>/admin/member/view?userno=${member.userno }">${member.userid }</a></td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
<%-- 	<td>${member.visitor }</td> --%>
	<td>${member.create_date }</td>
</tr>
<% i++; %>
</c:forEach>
</tbody>
</table>
</form>

<c:import url="/WEB-INF/views/admin/layout/member_paging.jsp"/>
</div>
<%-- <c:import url="/WEB-INF/views/layout/footer.jsp"/> --%>
</body>
</html>