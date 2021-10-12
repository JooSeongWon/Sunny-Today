<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#backBtn').click(function(){
		console.log("backBtn clicked");
		$(location).attr("href", "/admin/member/list");
	})
})
</script>
<style type="text/css">
#body .table {
    table-layout: fixed;
    
/*     white-space: pre-wrap; */
/*     word-break: break-all; */
}
</style>
<div id="body" class="container">
<h1>회원 목록</h1>
<hr>
<div class="text-center">
	<h2>회원 정보</h2>
</div>
<br>
<table class="table table-bordered">

<tr>
	<td class="info" style="text-align: center">아이디</td>
	<td>${member.userid }</td>
	<td class="info" style="text-align: center">성별</td>
	<c:choose>
		<c:when test="${member.gender eq 'F' }">
			<td>여성</td>
		</c:when>
		<c:when test="${member.gender eq 'M' }">
			<td>남성</td>
		</c:when>
		<c:when test="${member.gender eq 'A' }">
			<td>성별 미선택</td>
		</c:when>
	</c:choose>
</tr>

<tr>
	<td class="info" style="text-align: center">닉네임</td>
	<td>${member.nick }</td>
	<td class="info" style="text-align: center">생년월일</td>
	<td>${member.birth }</td>
</tr>
<tr>
	<td class="info" style="text-align: center">이메일</td>
	<td colspan="3">${member.email }</td>
</tr>
<tr>
	<td class="info"><br>
	<div class="text-center" style="text-align: center">회원 정보</div></td>
	<td colspan="3">
		<input type="checkbox" value="cold" id="cold"/>&nbsp;<label for="cold">추위를 많이 탐</label>&nbsp;&nbsp;
		<input type="checkbox" value="hot" id="hot"/>&nbsp;<label for="hot">더위를 많이 탐</label><br>
		<input type="checkbox" value="slim" id="slim"/>&nbsp;<label for="slim">슬림핏 선호</label>&nbsp;&nbsp;&nbsp;
		<input type="checkbox" value="boxy" id="boxy"/>&nbsp;<label for="boxy">박시핏 선호</label>&nbsp;&nbsp;&nbsp;
		<input type="checkbox" value="undefind_favor" id="undefind_favor"/>&nbsp;<label for="undefind_favor">undefind favor</label><br>
		<input type="checkbox" value="street" id="street"/>&nbsp;<label for="street">스트릿</label>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" value="dandy" id="dandy"/>&nbsp;<label for="dandy">댄디</label>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" value="casual" id="casual"/>&nbsp;<label for="casual">캐주얼</label><br>
	</td>
</tr>
</table>

<div class="text-center">
	<button type="button" id="backBtn" class="btn btn-primary">목록</button>
</div>
</div>
</body>
</html>