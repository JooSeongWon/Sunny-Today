<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeBtn').click(function(){
		console.log("writeBtn clicked");
// 		$('#sendWrite').submit();
	})
	$('#backBtn').click(function(){
		console.log("backBtn clicked");
		$(location).attr("href", "/admin/member/question");
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
<h1>1:1문의</h1>
<hr>
<div class="text-center">
	<h2>문의글 세부 사항</h2>
</div>

<table class="table table-bordered">

<tr>
	<td class="info">작성자</td>
	<td>${question.id }</td>
	<td class="info">작성일</td>
	<td>${question.write_date }</td>
</tr>

<tr>
	<td class="info">제목</td>
	<td colspan="3">${question.title }</td>
</tr>
<tr>
	<td class="info">내용</td>
	<td colspan="3">${question.content }</td>
</tr>
<tr>
	<td class="info">관리자명</td>
	<td>현재 접속 중인 관리자명</td>
	<td class="info">답변 일자</td>
	<td>${question.answer_date }</td>
</tr>
<tr>
	<td class="info">답변</td>
	<td colspan="3"><label>${question.answer }</label></td>
</tr>
</table>
<br>
<div class="text-center">
	<a href="<%=request.getContextPath() %>/admin/answer/write?question_no=${question.question_no }"><button class="btn btn-primary" type="button" id="writeBtn">작성</button></a>
	<button class="btn bnt-danger" type="button" id="backBtn">뒤로</button>
</div>

</div>
</body>
</html>