<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>



<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -문의 작성</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/question.css" rel="stylesheet">
    <script src="${jsPath}/question_script.js"></script>


</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="questionDetailTitle">
<hr>
<h3>문의사항</h3>
</div>

<div id="questionDetailContent">
<div id="questionDetailcolor">
<form action="<%=request.getContextPath() %>/question/update" method="post">
	<input type="hidden" name="questionno" value="${param.questionno }" />
	<div>
		<table id="questionUpdateTable">
		<tr class="questionUpdateWidth">
			<td>제목</td>
			<td><input type="text" id="questionUpdateText" name="title" value="${questionUpdate.title }"/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td id="questionUpdateNick">${nick }</td>
			<td></td>
		</tr>
		<tr>
		<td></td>
			<td colspan="2">
			<textarea id="updateContent" name="updateContent"/>${questionUpdate.content }</textarea>
			<div id="updateText_cnt">(0 / 150)</div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td id="btnUpdateOktd"><button id="btnUpdateOk">문의접수</button></td>
			<td id="btnUpdateCanceltd"><button id="btnUpdateCancel">취소</button></td>
		</tr>
		</table>
	</div>
</form>
</div>
</div>


<c:import url="../layout/footer.jsp"/>
</body>
</html>