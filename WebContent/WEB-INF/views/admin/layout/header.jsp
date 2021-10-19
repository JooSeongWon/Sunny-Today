<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/httpRequest.js"></script>
<!-- jquery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 폰트 어썸 -> 이거 폰트어썸 회원가입해서 따로 구해야 적용되요 정보에서 CDN받아오는게 있더라고여 그거 복사하세요 -->
<link rel="stylesheet" 
href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" 
integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" 
crossorigin="anonymous">

<style type="text/css">

#header {
	float:left;
	position:fixed;
	width:25%;
	background: black;
	color: white;
	height:100%;
}

#body {
	float:right;
	width:73%;	
}

#admin_set_icon2, #member_icon2, #markting_icon2, #post_icon2, 
#calneder_icon2, #fashion_icon2 {
	display:none;
}

#admin_set_group, #memeber_group, #marketing_group, #post_group {
	display:none
}

tr {
	user-select: none;
	cursor: default;
}

</style>
<script type="text/javascript" 
src="/resources/js/admin_nav.js"></script>

</head>
<body>
<div id="header">

<table class="table">
<tr>
	<th style="font-size:2.0em"><i class="fas fa-sun" style="color:red;"></i><a href="<%=request.getContextPath() %>/admin/set" style="color:white;">오늘도 맑음</a></th>
</tr>

<tr>
	<td style="font-size:1.5em" id="admin_set">
	<i class="far fa-caret-square-down" id="admin_set_icon1"></i>
	<i class="fas fa-caret-square-down" id="admin_set_icon2"></i>
	통합 관리</td>
</tr>

<tr id="admin_set_group">
	<td style="font-size:1em">
		<ul style="list-style: none;">
			<li id="admin_setting">관리자설정</li>
			<li id="message_event">메세지 이벤트 등록</li>
			<li id="message_send">메세지발송</li>
		</ul>
	</td>
</tr>

<tr>
	<td style="font-size:1.5em" id="member">
	<i class="far fa-caret-square-down" id="member_icon1"></i>
	<i class="fas fa-caret-square-down" id="member_icon2"></i>
	회원관리</td>
</tr>

<tr id="memeber_group">
	<td style="font-size:1em">
		<ul style="list-style: none;">
			<li id="member_list">회원 목록</li>
			<li id="member_question">1:1 문의 관리</li>
			<li id="member_report">신고관리</li>
			<li id="purnish_list">제재회원</li>
		</ul>
	</td>
</tr>

<!-- 
<tr>
	<td style="font-size:1.5em" id="markting">
	<i class="far fa-caret-square-down" id="markting_icon1"></i>
	<i class="fas fa-caret-square-down" id="markting_icon2"></i>
	마케팅 분석</td>
</tr>

<tr id="marketing_group">
	<td style="font-size:1em">
		<ul style="list-style: none;">
			<li id="conn_analysis">접속자 분석</li>
			<li id="member_statisics">회원 통계</li>
		</ul>
	</td>
</tr>
 -->
<tr>
	<td style="font-size:1.5em" id="post">
	<i class="far fa-caret-square-down" id="post_icon1"></i>
	<i class="fas fa-caret-square-down" id="post_icon2"></i>
	게시판 관리</td>
</tr>

<tr id="post_group">
	<td style="font-size:1em">
		<ul style="list-style: none;">
			<li id="board_menage">게시판 관리</li>
			<li id="total_board_setting">게시판 전체 설정</li>
			<li id="total_post_menage">게시물 통합 관리</li>
<!-- 			<li id="total_comments_menage">댓글 통합 관리</li> -->
			<li id="board_statisics">공지/이벤트 등록</li>

		</ul>
	</td>
</tr>

</table>

</div>