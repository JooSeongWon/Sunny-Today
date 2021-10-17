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
    <script src="${jsPath}/httpRequest.js"></script>

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
		<div id="detailTitle">${detailBoard.title }</div>
		<hr>
		<div>	
		<span id="detailNick">작성자: ${nick }</span>
		<span id="btnlist">	
			<a href="/board/report?post_no=${detailBoard.post_no }">
			<button id="btnReport">신고</button>
			</a>
			<c:if test="${loginMember.userno eq detailBoard.user_no }">
			<a href="/board/update?postno=${detailBoard.post_no }">
			<button id="btnUpdate">수정</button>
			</a>
			<a href="/board/delete?postno=${detailBoard.post_no }">
			<button id="btnDelete">삭제</button>
			</a>
			</c:if>
		</span>
		</div>
		
		<c:if test="${not empty detailFile }">
		<div id="preview">
			<img src="/upload/${detailFile.url }">
		</div>
		</c:if>
		<div id="detailContent"> ${detailBoard.content } </div>
		
		<div id="likeList">
		<span id="like">추천</span>
		<span id="disLike">반대</span>		
		</div>	

		<button id="btnList">뒤로가기</button>
		<hr>
		
		<form action="<%=request.getContextPath() %>/board/comments/insert" method="get">
			<input type="hidden" name="postno" id="postno" value="${detailBoard.post_no }" />
			<div>
				<input type="checkbox" name="onlyWriter" id="onlyWriter"> 
				<label for="onlyWriter">작성자만 보기</label>	
			</div>
			<div>
				<span>${loginMember.nick }</span>
				<input type="text" name="commentsContent" id="commentsContent" placeholder="댓글을 작성해보세요" />
				<input type="button" id="btnCommentsInsert" value="댓글입력"/>
			</div>
		</form>
		<hr>
		
		<c:if test="${empty comments }">
		<h3>댓글이 없습니다, 댓글을 작성해보세요!</h3>
		</c:if>
		
		<c:if test="${not empty comments }">
				<table>
			<c:forEach items="${comments }" var="comments">
					<tr id="CommentsAdd">
					<td>${comments.member }</td>
					<td>${comments.comments.content }</td>
					<c:if test="${loginMember.userno eq comments.comments.user_no }">
					<td>수정</td>
					<td>삭제</td>
					</c:if>
					<c:if test="${loginMember.userno ne comments.comments.user_no }">
					<td>신고</td>
					</c:if>
					</tr>		
			</c:forEach>
				</table>
		</c:if>
		
		
	
</div>


<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
