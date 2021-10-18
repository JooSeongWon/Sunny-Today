<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<!-- 스마트에디터2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});

	$("#btnUpdate").click(function() {
		$(location).attr("href", "<%=request.getContextPath() %>/admin/message/update?no=${message.message_e_no }");
	});
	
	$("#btnDelete").click(function() {
		if( confirm("쪽지를 삭제하시겠습니까?") ) {
			$(location).attr("href", "<%=request.getContextPath() %>/admin/message/delete?no=${message.message_e_no }");
		}
	});
	
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<title>쪽지작성</title>

<div id="body" class="container">

<h1>쪽지view</h1>
<hr>

<div class="col-md-11">


<table class="table table-bordered">
<tr>
<td class="active">분류명</td>
<td colspan="2">
${message.name }
</td>
</tr>

<tr>
<td class="active">쪽지 제목</td>
<td colspan="2">${message.title }</td>
</tr>

<tr>
<td class="active" colspan="2"></td>
</tr>

<tr>
<tr>
<td class="active">본문</td>
<td colspan="2"> ${message.content } </td>
</tr>

</table>

<div class="text-center">	
	<button type="button" id="btnCancel" class="btn btn-primary">목록</button>
	<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
	<button type="button" id="btnDelete" class="btn btn-danger">삭제</button>
</div>

</div>
</div>

</body>
</html>