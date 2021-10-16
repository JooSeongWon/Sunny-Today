<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#search").submit()
	});

	$("#btnAllDel").click(function(){
// 		 $("input[name='delno[]']:checked").each(function(){
// 			var check = $(this).val();
// 			console.log(check);
// 		});
		if( confirm("쪽지를 삭제하시겠습니까?") ) {	
			$("#Alldel").submit()
		}
	});

	$("#messagewrite").click(function(){
		$(location).attr("href", "/admin/message/event/write");
	});

	$("#eventwrite").click(function(){
		$(location).attr("href", "/admin/message/event/title");
	});
	
	$("button[name=btnUpdate]").click(function() {
		console.log("btn")
		$(location).attr("href", "/admin/message/update?no="+$(this).attr('value'));
	});

	$("button[name=btnDelete]").click(function() {
		if( confirm("쪽지를 삭제하시겠습니까?") ) {
			$(location).attr("href", "/admin/message/delete?no="+$(this).attr('value'));
		}
	});
	
	$('.check-all').click(function(){
		$('.check').prop('checked', this.checked);
	})
	
});
</script>

<title>쪽지이벤트</title>

<div id="body" class="container">

<h1>쪽지 이벤트</h1>
<hr>

<div class="col-md-11">

<div class="row" >

<div class="col-md-6">
	<form action="<%=request.getContextPath() %>/admin/message/event" id="search" method="get" class="form-group">
		<label>&nbsp;제목 검색
			<input class="search-query" type="text" name="search" placeholder="search"/>
		</label>
		<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	</form>
</div>

<div class="col-md-6" >
	<div class="form-group text-right">
		<button class="btn btn-default" id="eventwrite" type="button">이벤트등록</button>
		<button class="btn btn-info" id="messagewrite" type="button">쪽지등록</button>
	</div>
</div>

</div>


<form action="<%=request.getContextPath() %>/admin/message/delete" id="Alldel" method="post">
<table class="table">
<tr>
	<th><input type="checkbox" name="check-all" class="check-all"></th>
	<th>No.</th>
	<th>이벤트 이름</th>
	<th>제목</th>
	<th class="text-right"><button class="btn btn-danger" id="btnAllDel" type="button">삭제</button></th>

</tr>
<c:forEach items="${list }" var="messageEvent">
<tr>
	<td><input type="checkbox" name="delno[]" value="${messageEvent.message_e_no }" class="check"></td>
	<td>${messageEvent.message_e_no }</td>
	<td>${messageEvent.name }</td>
	<td><a href="<%=request.getContextPath() %>/admin/message/event/view?no=${messageEvent.message_e_no }">${messageEvent.title }</td>
	<td class="text-right">
		<button type="button" class="btn btn-default" name="btnUpdate" value="${messageEvent.message_e_no }">수정</button>
		<button type="button" class="btn btn-danger" name="btnDelete" value="${messageEvent.message_e_no }">삭제</button>
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