<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	$('#executeBtn').click(function(){
		console.log("executeBtn clicked");
 		$('form').submit();
	})
	$('#backBtn').click(function(){
		console.log("backBtn clicked");
		$(location).attr("href", "/admin/member/report");
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
<h1>신고 관리</h1>
<hr>
<div class="text-center">
	<h2>신고 세부 사항</h2>
</div>
<form action="<%=request.getContextPath() %>/admin/report/execute" method="post">
<c:forEach items="${list }" var="map">

<table class="table table-bordered">

<tr>
	<td class="info">작성자</td>
	<td>${map.m.userid }</td>
	<td class="info">작성일</td>
	<td>${map.rp.report_date }</td>
</tr>

<tr>
	<td class="info">제목</td>
	<td colspan="3">${map.rpc.title }</td>
</tr>
<tr>
<c:choose>
	<c:when test="${map.rp.report_type ne 'P' }">
			<td class="info">내용</td>
			<td colspan="3">${map.cm.content }</td>
	</c:when>

	<c:when test="${map.rp.report_type eq 'P' }">
			<td class="info">내용</td>
			<td colspan="3">${map.p.content }</td>
	</c:when>
</c:choose>
</tr>

<tr>
	<td class="info">신고내용</td>
	<td colspan="3">${map.rp.detail }</td>
</tr>

<tr>
	<td class="info">제재 형태 </td>
	<td><select name="Ban_type">
			<option value="non-subject">제재 대상 아님</option>
			<option value="login">로그인</option>
			<option value="write_post">게시물 작성</option>
		</select></td>
		
	<td class="info">제재 기간</td>
	<td><select name="Ban_date">
			<option value="non">없음</option>
			<option value="1week">1주일</option>
			<option value="1month">1개월</option>
			<option value="3month">3개월</option>
			<option value="1year">1년</option>
			<option value="permanent">영구 정지</option>
		</select>
	</td>
</tr>

<c:choose>
	<c:when test="${map.rp.execute_result ne null and map.rp.execute_result ne ''  }">
	<tr>
		<td class="info">처리 결과</td>
		<td colspan="3">${map.rp.execute_result }<input type="hidden" name="memo" value="${map.rp.execute_result }"></td>
	</tr>
	</c:when>

	<c:when test="${map.rp.execute_result eq null or map.rp.execute_result eq ''  }">
	<tr>
		<td class="info">처리 결과 작성</td>
		<td colspan="3"><textarea class="form-control h-25" rows="6" name="execute_result" placeholder="insert result"></textarea></td>
	</tr>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${map.rp.memo ne null and map.rp.memo ne ''  }">
	<tr>
		<td class="info">관리자 메모</td>
		<td colspan="3">${map.rp.memo }<input type="hidden" name="memo" value="${map.rp.memo }"></td>
		
	</tr>
	</c:when>
	<c:when test="${map.rp.memo eq null or map.rp.memo eq ''  }">
	<tr>
		<td class="info">관리자 메모</td>
		<td colspan="3"><textarea class="form-control h-25" rows="6" name="memo" placeholder="insert memo"></textarea></td>
	</tr>
	</c:when>
</c:choose>

</table>

<input type="hidden" name="report_no" value="${map.rp.report_no }"/>
<input type="hidden" name="user_no" value="${map.ur.target_no }">
<input type="hidden" name="report_type" value="${map.rp.report_type }">
<input type="hidden" name="reason" value="${map.rpc.title }">

</c:forEach>
<br>
<div class="text-center">
	<button class="btn btn-primary" type="button" id="executeBtn">제재</button>
	<button class="btn bnt-danger" type="button" id="backBtn">뒤로</button>
</div>
</form>



</div>
</body>
</html>