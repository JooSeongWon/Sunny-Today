<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#search").submit()
	});
	
	$('.check-all').click(function(){
		$('.check').prop('checked', this.checked);
	})
	
	$("#sendBtn").click(function(){
		$("#send").submit()
	});
	
});
</script>

<title>쪽지 발송</title>

<div id="body" class="container">

<h1>쪽지 발송</h1>
<hr>

<div class="col-md-11">


<div class="form-inline">
	<form action="<%=request.getContextPath() %>/admin/message" id="search" method="get">
		<label class="form-group" for="search">&nbsp;회원 검색&nbsp;</label>
		<select name="select" class="form-control form-group">
			<option value="id">아이디</option>
			<option value="nick">닉네임</option>
			<option value="email">이메일</option>
		</select>
		<input class="form-control search-query form-group" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default form-group" id="searchBtn" type="button">검색</button>
	</form>
</div>

<form action="<%=request.getContextPath() %>/admin/message/write" id="send" method="get" class="form-group">
<table class="table">
<tr>
<th colspan="3"><span>&nbsp; 총 회원수 : ${paging.totalCount }</span></th>
<th colspan="3" class="text-right">
	<button class="btn btn-default" id="sendBtn" type="button">쪽지발송</button>
</th>
</tr>
<tr>
	<th><input type="checkbox" name="check-all" class="check-all"></th>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>이메일</th>
	<th>가입일</th>
</tr>
<c:forEach items="${list }" var="member">
<tr>
	<td><input type="checkbox" name="no[]" value="${member.userno }" class="check"></td>
	<td>${member.userno }</td>
	<td>${member.userid }</td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
	<td>${member.create_date }</td>
</tr>
</c:forEach>
</table>
</form>

<c:import url="/WEB-INF/views/admin/layout/message_paging.jsp"/>
</div>

</body>
</html>