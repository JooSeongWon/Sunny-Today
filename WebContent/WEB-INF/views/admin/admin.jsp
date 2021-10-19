<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>오늘도 맑음 -뭐입지?</title>
    
    <link href="/resources/css/admin_main.css" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//비밀번호 인증
	$("#btn").click(function(){
		
		$.ajax({
			url:"/admin/check",
			type:"post",
			data:{
				userid : $("#userid").val() ,
				userpw : $("#userpw").val() 
			},
			dataType : 'json' 
			, success:function(data){
					console.log(data)
					if( data == 1 ){
						alert("비밀번호가 일치하지 않습니다 :(") 
					} else if ( data == 2 ) {
						alert("비밀번호를 입력해주세요 :(") 
					} else if( data == 3 ){
						alert("비밀번호를 등록해주세요 :( !!" )
							var url = "/mypage/password"
							$(location).attr('href',url);
					} else {
						alert("관리자페이지로 이동합니다" )
							var url = "/admin/set"
							$(location).attr('href',url);
					}
				},
				error: function() {
				console.log("에러 발생");
			}
			
		})
	})
});
</script>
    
</head>
<body>


<div class="mypage-container">

<div class="mypage">
	
	<div class="profile-container">
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;오늘도 맑음</h1></div>
	<hr>
	<div style="text-align: left;" ><span>&nbsp;&nbsp;&nbsp;관리자페이지</span></div>
	<div class="profile_form">
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item" >비밀번호</td>
			<td class="profile_item" ><input type="password" name="userpw" id="userpw" class="profile-setting" /></td>
		</tr>
	</table>
	<div><input type="text" style="visibility: hidden;" ></div>
	<div><button id="btn" class="buttonClass">인증하기</button></div>
	<div><input type="hidden" name="userid" id="userid" value="${member.userid }"></div>
	</div>
	</div>
</div>
</div>
</body>
</html>