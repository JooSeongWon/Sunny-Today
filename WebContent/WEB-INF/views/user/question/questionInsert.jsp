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
    <link href="${cssPath}/board.css" rel="stylesheet">
    <script src="${jsPath}/question_script.js"></script>


</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div>
<hr>
<h1>문의사항</h1>
</div>

<form action="<%=request.getContextPath() %>/question/insert" method="post">
	<div>
		<table>
		<tr>
			<td>제목 <input type="text" name="title" placeholder="제목을 입력하세요"/></td>
		</tr>
		<tr>
			<td>작성자</td>
		</tr>
		<tr>
			<td>
			<textarea name="content" placeholder="내용을 입력하세요"/></textarea>
			</td>
		</tr>
		<tr>
			<td><button id="btnWriteSubmit">문의접수</button></td>
			<td><button id="btnWriteCancel">취소</button></td>
		</tr>
		</table>
	</div>
</form>



<c:import url="../layout/footer.jsp"/>
</body>
</html>