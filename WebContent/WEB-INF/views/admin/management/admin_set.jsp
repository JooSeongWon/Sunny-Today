<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#search").submit()
	});

	$("#viewBtn").click(function(){
		$("#allsearch").submit()
	});

	$("#searchAdminBtn").click(function(){
		$("#admin").submit()
	});

	$("#setAdminBtn").click(function(){
		var Admin = $("#admin").val;
		if( Admin = 'A'){
			alert("권한이 없습니다")
			return
		} else{
		$("#setAdmin").submit()
		}
	});

	$("#delAdminBtn").click(function(){
		var Admin = $("#admin").val;
		if( Admin = 'A'){
			alert("권한이 없습니다")
			return
		} else{
		$("#delAdmin").submit()
		}
	});
	
});
</script>

<title>관리자 설정</title>

<div id="body" class="container">

<h1>관리자 설정</h1>
<hr>

<div class="col-md-11">

<div class="row" >

<div class="col-md-6">
	<form action="<%=request.getContextPath() %>/admin/set" id="search" method="get">
		<label>&nbsp;회원 검색
		<input class="search-query" type="text" name="search" placeholder="search"/>
		</label>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	</form>
</div>

<div class="col-md-6">
	<div class="form-inline text-right">
	<form action="<%=request.getContextPath() %>/admin/set" id="admin" method="get" class="form-group">
		<label>&nbsp;관리자보기</label>
		<input type="hidden" name="search" value="adminlist"/>
		<button class="btn btn-info" id="searchAdminBtn" type="button">확인</button>
	</form>
	<form action="<%=request.getContextPath() %>/admin/set" id="allsearch" method="get" class="form-group">
		<label>&nbsp;전체 보기</label>
		<input type="hidden" name="search" value=""/>
		<button class="btn btn-default" id="viewBtn" type="button">확인</button>
	</form>
	</div>
</div>

</div>




<input type="hidden" id="admin" value="${Admin }">
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
			<td class="text-right"><button class="btn btn-default" id="setAdminBtn" type="button">관리자 권한지정</button></td>
		</form>
		</c:when>
		<c:when test="${member.admin ne 'N'}">
		<form action="<%=request.getContextPath() %>/admin/set" id="delAdmin" method="post">
			<input type="hidden" id="del"  name="userno" value="${member.userno }">
			<input type="hidden" name="val" value="del">
			<td class="text-right"><button class="btn btn-danger" id="delAdminBtn" type="button">관리자 권한삭제</button></td>
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