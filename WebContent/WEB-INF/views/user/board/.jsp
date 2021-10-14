<%@page import="xyz.sunnytoday.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -전체 카테고리</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/board.css" rel="stylesheet">
    <script src="${jsPath}/board_script.js"></script>

</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="How_was_your_day">
	<h2></h2>
	당신의 이야기를 들려주세요 :)
</div>
<hr>

<div class="detailarea">
		<div id="detailTitle">제목</div>
		<hr>
		<div>
		<p id="detailNick">작성자: 작성자nick</p>
		<p>	
			<button id="btnReport">신고</button>
			<button id="btnUpdate">수정</button>
			<button id="btnDelete">삭제</button>
		</p>
		</div>
			
		<div id="preview"></div>
		<div> content </div>
		
		<div>
		<p>추천</p>
		<p>반대</p>		
		</div>	

		<button id="btnList">목록으로</button>
		<hr>
	
</div>


<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
