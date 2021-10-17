<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <%--페이지별 css/ js--%>
    <link href="${cssPath}/mypage2_style.css" rel="stylesheet">

<script type="text/javascript">
    $(document).ready(function(){
    	$("#btn-check").click(function() {
			console.log($("#btn-check").prop('checked') )
		})
    	
    	$("#btnsubmit").click(function(){
    		if($("#btn-check").prop('checked') == false){
    			showModal("오늘도 맑음", "이용약관을 확인해주세요" )
    			return;
    		} else if($("#btn-check").prop('checked') == true){
    		$("form").submit();
    		}
    		
	    });
    });
</script>

<style type="text/css">
#content {
	width: 98%;
}

textarea {
	height: 157px;
    width: 600px;
    resize:none;
}
</style>
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">


<div class="mypage">
	<div class="profile-container">
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;회원탈퇴</h1></div>
	<hr>
	<div style="text-align: left;" ><span>&nbsp;&nbsp;약관동의</span></div>
	<form action="/leaveid" method="post" class="profile_form"  enctype="multipart/form-data" >
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item"><textarea readonly id="read"> 제 1 장 총칙

제 1 조 (목적)
본 약관은 ○○○이 운영하는 웹 사이트 (http://xxx.xxx.xxx)의 제반 서비스의 이용조건 및 절차에 관한 사항 및 기타 필요한 사항을 규정함을 목적으로 한다.

제 2 조 (용어의 정의)
본 약관에서 사용하는 용어는 다음과 같이 정의한다.
①회원 : 기본 회원 정보를 입력하였고, 회사와 서비스 이용계약을 체결하여 아이디를 부여받은 개인
②아이디(ID) : 회원식별과 회원의 서비스 이용을 위해 회원이 선정하고 회사가 승인하는 문자와 숫자의 조합
③비밀번호(Password) : 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합
④해지 : 회사 또는 회원에 의한 이용계약의 종료</textarea></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" ><input type="checkbox" id="btn-check" class="buttonClass"/> 동의</td>
		</tr>
	</table>
		<div><button type="button" id="btnsubmit" class="buttonClass">탈퇴하기</button></div>
		<div><input type="text" style="visibility: hidden;" ></div>
	</form>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>