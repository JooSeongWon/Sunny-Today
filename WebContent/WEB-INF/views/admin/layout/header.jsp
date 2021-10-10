<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	width:30%;
	background: black;
	color: white;
	height:100%;
}

#body {
	float:right;
	width:68%;	
}

#admin_set_icon2, #member_icon2, #markting_icon2, #post_icon2, #calneder_icon2, #fashion_icon2 {
	display:none;
}
#member_list, #purnish_list, #member_question, #member_report, 
#admin_setting, #message_enroll, #message_send, 
#conn_analysis, #member_statisics, #board_menage, #total_board_setting, 
#total_post_menage, #total_comments_menage, #board_statisics, 
#conn_analysis, #member_statisics{
	display:none;
	text-align: center;
} 
</style>
<script type="text/javascript" src="/resources/js/admin_nav.js"></script>

</head>
<body>
<div id="header">

<table class="table">
<tr>
	<th style="font-size:2.5em"><i class="fas fa-sun" style="color:red;"></i>오늘도 맑음</th>
</tr>
<!-- 경로는 잘 지정해서 보내주세요 -->
<tr>
	<td style="font-size:2em" id="admin_set">
	<i class="far fa-caret-square-down" id="admin_set_icon1"></i>
	<i class="fas fa-caret-square-down" id="admin_set_icon2"></i>
	통합 관리</td>
</tr>
<tr id="admin_setting">
	<td style="font-size:1em">관리자 설정</td>
</tr>

<tr id="message_enroll">
	<td style="font-size:1em">자동 발송 메세지 설정</td>
</tr>

<tr id="message_send">
	<td style="font-size:1em">메세지 발송</td>
</tr>

<tr>
	<td style="font-size:2em" id="member">
	<i class="far fa-caret-square-down" id="member_icon1"></i>
	<i class="fas fa-caret-square-down" id="member_icon2"></i>
	회원관리</td>
</tr>
<tr id="member_list">
	<td style="font-size:1em">회원목록</td>
</tr>

<tr id="member_question">
	<td style="font-size:1em">1:1 문의 관리</td>
</tr>

<tr id="member_report">
	<td style="font-size:1em">신고관리</td>
</tr>

<tr id="purnish_list">
	<td style="font-size:1em">제재회원</td>
</tr>

<tr>
	<td style="font-size:2em" id="markting">
	<i class="far fa-caret-square-down" id="markting_icon1"></i>
	<i class="fas fa-caret-square-down" id="markting_icon2"></i>
	마케팅 분석</td>
</tr>

<tr id="conn_analysis">
	<td style="font-size:1em">접속자 분석</td>
</tr>

<tr id="member_statisics">
	<td style="font-size:1em">회원 통계</td>
</tr>

<tr>
	<td style="font-size:2em" id="post">
	<i class="far fa-caret-square-down" id="post_icon1"></i>
	<i class="fas fa-caret-square-down" id="post_icon2"></i>
	게시판 관리</td>
</tr>
<tr id="board_menage">
	<td style="font-size:1em">게시판 관리</td>
</tr>

<tr id="total_board_setting">
	<td style="font-size:1em">게시판 전체 설정</td>
</tr>
<tr id="total_post_menage">
	<td style="font-size:1em">게시물 통합 관리</td>
</tr>

<tr id="total_comments_menage">
	<td style="font-size:1em">댓글 통합 관리</td>
</tr>

<tr id="board_statisics">
	<td style="font-size:1em">게시판 통계</td>
</tr>

<tr>
	<td style="font-size:2em" id="calneder">
	<i class="far fa-caret-square-down" id="calneder_icon1"></i>
	<i class="fas fa-caret-square-down" id="calneder_icon2"></i>
	캘린더</td>
</tr>

<tr>
	<td style="font-size:2em" id="fashion">
	<i class="far fa-caret-square-down" id="fashion_icon1"></i>
	<i class="fas fa-caret-square-down" id="fashion_icon2"></i>
	패션 추천</td>
</tr>

</table>

</div>