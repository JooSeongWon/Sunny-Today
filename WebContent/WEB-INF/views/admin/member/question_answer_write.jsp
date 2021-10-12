<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeBtn').click(function(){
		console.log("writeBtn clicked");
		$('#sendPost').submit();
	})
	
	$('#backBtn').click(function(){
		console.log("backBtn clicked")
		$(location).attr("href", "/admin/member/question")
	})
})
</script>
<div id="body" class="container">
<h1>답변 작성</h1>
<hr>
<form action="<%=request.getContextPath() %>/admin/answer/write" id="sendPost" method="post">

<table class="table table-bordered" >

<tr>
	<td class="info" style="text-align: center">작성자</td>
	<td>${question.id }</td>
	<td class="info" style="text-align: center">작성일</td>
	<td>${question.write_date }</td>
</tr>

<tr>
	<td class="info" style="text-align: center">제목</td>
	<td colspan="3">${question.title }</td>
</tr>
<tr>
	<td class="info" style="text-align: center">내용</td>
	<td colspan="3">${question.content }</td>
</tr>
<tr>
	<td class="info" style="text-align: center">관리자명</td>
	<td>현재 접속 중인 관리자명</td>
	<td class="info" style="text-align: center">답변 일자</td>
	<td>현재시각으로 자동 설정됩니다.</td>
</tr>
<tr>
	<td class="info"><br><br><br><div class="text-center">답변</div></td>
	<td colspan="3"><textarea class="form-control h-25" rows="6" id="answer" name="answer"></textarea></td>
</tr>
</table>
<br>
<div class="text-center">
	<button class="btn btn-primary" type="button" id="writeBtn">작성</button>
	<button class="btn bnt-danger" type="button" id="backBtn">뒤로</button>
</div>
<input type="hidden" name="question_no" value="${question.question_no }"/>
</form>

</div>
</body>
</html>