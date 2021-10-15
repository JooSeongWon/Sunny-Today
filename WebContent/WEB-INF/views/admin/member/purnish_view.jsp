<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#revokeBtn').click(function(){
		console.log("revokeBtn clicked");
 		$('form').submit();
	})
	$('#backBtn').click(function(){
		console.log("backBtn clicked");
		$(location).attr("href", "/admin/purnish/list");
	})
})
</script>

<style type="text/css">
#body .table {
    table-layout: fixed;
    
    white-space: pre-wrap;
    word-break: break-all;
}
</style>

<div id="body" class="container">
<h1>제재 관리</h1>
<hr>
<div class="text-center">
	<h2>제재 세부 사항</h2>
</div>

<form action="<%=request.getContextPath() %>/admin/purnish/view" method="post">
<c:forEach items="${list }" var="map">

<table class="table table-bordered">

<tr>
	<td class="info">아이디</td>
	<td>${map.m.userid }</td>
	<td class="info">가입일</td>
	<td>${map.m.create_date }</td>
</tr>

<tr>
	<td class="info">닉네임</td>
	<td colspan="3">${map.m.nick }</td>
</tr>
<tr>
	<td class="info">사유</td>
	<td colspan="3">${map.b.reason }</td>
</tr>

<tr>
	<td class="info">기간</td>
	<td colspan="3">${map.b.ban_date } ~ ${map.b.expiry_date }</td>
</tr>

</table>

<input type="hidden" name="ban_no" value="${map.b.ban_no }"/>

</c:forEach>
<br>
<div class="text-center">
	<button class="btn btn-primary" type="button" id="revokeBtn">복구</button>
	<button class="btn bnt-danger" type="button" id="backBtn">목록</button>
</div>
</form>



</div>
</body>
</html>