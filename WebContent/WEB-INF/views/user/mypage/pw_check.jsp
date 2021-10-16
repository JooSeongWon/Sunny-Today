<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <%--페이지별 css/ js--%>
<<<<<<< HEAD
    <link href="${cssPath}/mypage_style.css" rel="stylesheet">
    <script src="${jsPath}/mypage_script.js"></script>
=======
    <link href="${cssPath}/mypage2_style.css" rel="stylesheet">
    
    <script type="text/javascript">
    $(document).ready(function(){
    	$("#btn").click(function(){
    		$("#check").submit();
	    });
    });
    </script>
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
    
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">

<div class="mypage">
	
	<div class="profile-container">
<<<<<<< HEAD
	
	<form action="/password/check" method="post" class="profile_form">
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item" >아이디</td>
			<td class="profile_item" >${loginmember.userid }</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >비밀번호</td>
			<td class="profile_item" ><input type="text" class="profile-setting" id="nick" name="nick" value="${loginmember.nick }"/></td>
		</tr>
		<tr class="profile_list">
			<td colspan="2" ><button id="btn">확인</button></td>
		</tr>
	</table>
=======
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;비밀번호 인증</h1></div>
	<hr>
	<div style="text-align: left;" ><span>&nbsp;&nbsp;비밀번호를 인증해주세요</span></div>
	<form action="/mypage/password/check" method="post" id="check" class="profile_form">
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item" >아이디</td>
			<td class="profile_item" ><input type="text" name="userId" value="${member.userid }" disabled="disabled"></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >비밀번호</td>
			<td class="profile_item" ><input type="password" name="userPw" class="profile-setting" id="nick" name="nick" value="${loginmember.nick }"/></td>
		</tr>
	</table>
	<div><button id="btn" class="buttonClass">인증하기</button></div>
	<div><input type="text" style="visibility: hidden;" ></div>
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
	</form>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>