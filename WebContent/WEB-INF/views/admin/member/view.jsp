<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#backBtn").click(function(){
		$(location).attr("href", "/member/list");
	})
})
</script>
<div id="body">
<title>회원 정보</title>
<h1>회원 정보</h1>
<hr>

<table class="table table-bordered" style="width:98%; font-size:1.5em;">
<tr>
	<td class="info">아이디</td>
	<td colspan="2">${member.userid }</td>
	<td class="info">성별</td>
	<c:choose>
		<c:when test="${member.gender eq 'M' }">
			<td colspan="2">남성</td>
		</c:when>
		
		<c:when test="${member.gender eq 'F' }">
			<td colspan="2">여성</td>
		</c:when>
		
		<c:when test="${member.gender eq 'A' }">
			<td colspan="2">성별 미선택</td>
		</c:when>
	</c:choose>
</tr>

<tr>
	<td class="info">닉네임</td>
	<td colspan="2">${member.nick }</td>
	<td class="info">생년월일</td>
	<td colspan="2">${member.birth }</td>
</tr>
<tr>
	<td class="info">이메일</td>
	<td colspan="4">${member.email }</td>
</tr>
<tr>
</tr>
<tr>
	<td class="info" rowspan="2">회원 정보</td>
	<td colspan="4" rowspan="2">세부 정보 모음....</td>
</tr>
</table>
<div class="text-center">
<button type="button" class="btn btn-primary" id="backBtn">목록으로</button>
</div>
</div>
</body>
</html>