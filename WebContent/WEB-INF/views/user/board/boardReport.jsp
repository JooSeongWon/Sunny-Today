<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../layout/head_meta.jsp"/>
<title>오늘도 맑음 - 게시판 신고</title>
<style type="text/css">
.menu-left {
	display: inline-block;
	width:30%;
}
.main-board {
	display: inline-block;
	width:68%;
}
#sendBtn {
	background: #4FF;
    border-radius: 6px;
	border: none;    
    color: white;
}
#sendBtn:hover {
	border: 2px solid #CCC;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
	$('sendBtn').click(function(){
		console.log("sendBtn clicked");
		$('form').submit();
	})
	
	$('cancelBtn').click(function(){
		console.log("cancelBtn");
		history.go(-1);
	})
	$('#').is(":checked"){
		
	}
});
</script>
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>
<div>
<div class="How_was_your_day">
	<h2>신고</h2>
	해당 게시글을 신고하는 이유를 작성하세요
</div>
<hr>
	

<div class="menu-left">
	<div><h2>카테고리</h2></div>
	<div><a href="/board/main">전체 글</a></div>
	<div><a href="/board/list/daily">일상룩</a></div>
	<div><a href="/board/list/buy">지름 게시판</a></div>
	<div><a href="/board/list/share">정보공유</a></div>
	<div><a href="/board/list/asking">질문 응답</a></div>
	<div><a href="/board/list/mine">내가 쓴 글</a></div>
	
</div>

<c:forEach items="${list }" var="map">
${map.p.title }
</c:forEach>
<section class="main-board">
<c:forEach items="${list }" var="map">

<form action="/board/report" method="post">

<h5>신고하기</h5>
<h3>제목: ${map.p.title }</h3>
<h3>작성자: ${map.m.userid }</h3>

<h5>신고 대상 선택</h5>
<input type="radio" name="report_type" id="post" value="post" checked="checked"> <label for="post">게시물</label>
<input type="radio" name="report_type" id="comments" value="comments"> <label for="comment">댓글</label>

<h5>사유선택</h5>
<input type="radio" name="report_reason" value="advertisement" id="ad"> <label for="ad"> 부적절한 홍보 댓글/게시글</label><br>
<input type="radio" name="report_reason" value="pornography" id="porn"> <label for="porn"> 음란성 또는 청소년에게 부적합한 내용</label><br>
<input type="radio" name="report_reason" value="defamation" id="defam"> <label for="defam"> 명예훼손/사생황 침 해 및 저작권 침해</label> <br>
<input type="radio" name="report_reason" value="etc" id="etc"> <label for="etc"> 기타</label><br><br>

<h5>세부 사항 작성</h5>
<textarea name="report_detail" placeholder="세부 사항을 적어주세요"></textarea>

<br>
<button type="button" id="sendBtn">전송</button>
<button type="button" id="cancelBtn">취소</button>
<br><br>
<input type="hidden" name="comment_no" value="${map.c.comments_no }">

<input type="hidden" name="post_no" value="${map.p.post_no }">
<input type="hidden" name="user_no" value="${userno }"/>
<input type="hidden" name="target_no" value="${map.m.userid }">


</form>
</c:forEach>

</section>
</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>

</body>
</html>