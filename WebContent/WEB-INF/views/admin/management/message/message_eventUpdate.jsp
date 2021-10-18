<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<!-- 스마트에디터2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>

<script type="text/javascript">
//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영한다
function submitContents(elClickedObj) {
	
	//에디터의 내용을 #content에 반영해준다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch (e) {}
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents( $("#btnUpdate") )
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
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

<h1>쪽지등록</h1>
<hr>

<div class="col-md-11">


<form action="<%=request.getContextPath() %>/admin/message/update" method="post">
<table class="table table-bordered">
<tr>
<td class="active">분류명</td>
<td colspan="2">
<select name="event" class="form-control">
    <option value="${message.event_no }" selected="selected" >${message.name }</option>
<c:forEach items="${event }" var="e">	
    <option value="${e.event_no }">${e.name }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td class="active">쪽지 제목</td>
<td colspan="2"><input type="text" name="title" class="form-control" value="${message.title }"></td>
</tr>

<tr>
<td class="active" colspan="2"></td>
</tr>

<tr>
<tr>
<td class="active">본문</td>
<td><textarea id="content" name="content">${message.content }</textarea></td>
</tr>

</table>
	<input type="hidden" name="message_e_no" value="${message.message_e_no }" >
</form>

<div class="text-center">
	<button type="button" id="btnUpdate" class="btn btn-info">등록</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

</div>
</div>

<!-- <textarea>태그에 스마트에디터2 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 	oAppRef: oEditors,
 	elPlaceHolder: "content",
 	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
 	fCreator: "createSEditor2"
});
</script>


</body>
</html>