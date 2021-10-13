<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#search").submit()
	});

	$("#messagewrite").click(function(){
		$(location).attr("href", "/admin/message/write");
	});

	$("#eventwrite").click(function(){
		$(location).attr("href", "/admin/message/write");
	});
	
	$("#setmessageBtn").click(function(){
		alert($(this).attr('value'));
	});

	$("#delMessageBtn").click(function(){
		$("#delMessage").submit()
	});
});
</script>

<title>쪽지이벤트</title>

<div id="body" class="container">

<h1>쪽지 이벤트</h1>
<hr>

<div class="col-md-11">

<div class="row" >

<div class="col-md-8">
	<form action="<%=request.getContextPath() %>/admin/message/event" id="search" method="get" class="form-group">
		<label>&nbsp;제목 검색
			<input class="search-query" type="text" name="search" placeholder="search"/>
		</label>
		<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	</form>
</div>

<div class="col-md-4">
	<div class="form-group text-right">
		<button class="btn btn-default" id="Eventwrite" type="button">이벤트등록</button>
		<button class="btn btn-info" id="messagewrite" type="button">쪽지등록</button>
	</div>
</div>

</div>



<form action="<%=request.getContextPath() %>/admin/message/event" id="message">
<table class="table">
<tr>
	<th></th>
	<th>No.</th>
	<th>이벤트 이름</th>
	<th>제목</th>
	<th></th>

</tr>
<c:forEach items="${list }" var="messageEvent">
<tr>
	<td></td>
	<td>${messageEvent.message_e_no }</td>
	<td>${messageEvent.name }</td>
	<td>${messageEvent.title }</td>
	<td class="text-right">
		<input type="hidden" name="messageno" value="${messageEvent.message_e_no }" >
		<button class="btn btn-default" id="setmessageBtn" name="btn" value="up">수정</button>
		<button class="btn btn-danger" id="delmessageBtn" name="btn" value="del">삭제</button>
	</td>
</tr>
</c:forEach>
</table>
</form>

<c:import url="/WEB-INF/views/admin/layout/messageEvent_paging.jsp"/>
</div>

</div>
</body>
</html>