<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		$("#titleEvent").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		$(location).attr('href', '<%=request.getContextPath() %>/admin/message/event')
	});

	//삭제 버튼 동작
	$("#btnDel").click(function() {
		var E = $("#event").val();
		if( E == null ){
			alert("삭제할 이벤트를 선택해주세요")
			return
		} else {
			$("#delEvent").submit();
		}
	});
	
});
</script>


<title>이벤트작성</title>

<div id="body" class="container">

<h1>이벤트등록</h1>
<hr>

<div class="col-md-11">


<form action="<%=request.getContextPath() %>/admin/message/event/title" id="titleEvent" method="post">
<table class="table table-bordered">
<tr>
<td class="active">분류명</td>
<td colspan="2"><input type="text" name="event" class="form-control"></td>
</tr>
</table>
</form>

<form action="<%=request.getContextPath() %>/admin/message/event/view" id="delEvent" method="post">
<table class="table table-bordered">
<tr>
<td class="active">분류명</td>
<td colspan="2">
<select name="event" class="form-control" id="event" >
	<option value="" disabled selected hidden>기본값입니다.</option>
<c:forEach items="${event }" var="e">
    <option value="${e.event_no }">${e.name }</option>
</c:forEach>
</select>
</td>
</tr>
</table>
</form>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">등록</button>
	<button type="button" id="btnDel" class="btn btn-danger">삭제</button>
	<button type="button" id="btnCancel" class="btn btn-default">취소</button>
</div>

</div>
</div>

</body>
</html>