<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id ="body" class="container">
<h1>회원 목록</h1>
<hr>
<div class="text-left">
	<label>회원 검색</label>
	<select name="select_option">
		<option value="userid">회원ID</option>
		<option value="nick">닉네임</option>
	</select>
	<input type="text" name="search" value="search"/>
</div>

<div class="text-right">
	<button class="btn">회원 제재</button>
</div>
<table class="table">
<tr>
	<th>No.</th>
	<th>아이디</th>
	<th>닉네임</th>
	<th>방문 횟수</th>
	<th>가일일</th>
</tr>

<c:forEach items="${list }" var="member">

<tr>
	<td>${member.userno }</td>
	<td>${member.userid }</td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
<%-- 	<td>${member.visitor }</td> --%>
	<td>${member.create_date }</td>
</tr>
</c:forEach>
</table>
<c:import url="/WEB-INF/views/admin/layout/paging.jsp"/>

</div>
<%-- <c:import url="/WEB-INF/views/layout/footer.jsp"/> --%>
</body>
</html>