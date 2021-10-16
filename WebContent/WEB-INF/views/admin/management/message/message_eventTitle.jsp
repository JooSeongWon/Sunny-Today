<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>


<title>이벤트작성</title>

<div id="body" class="container">

<h1>이벤트등록</h1>
<hr>

<div class="col-md-11">


<form action="/admin/message/event/title" method="post">
<table class="table table-bordered">
<tr>
<td class="active">분류명</td>
<td colspan="2"><input type="text" name="event" class="form-control"></td>
</tr>
</table>
</form>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">등록</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

</div>
</div>

</body>
</html>