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
    <link href="${cssPath}/mypage2_style.css" rel="stylesheet">
    <script src="${jsPath}/mypage_script3.js"></script>
    
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">

<div class="mypage">
	
	<div class="profile-container">
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;비밀번호 인증</h1></div>
	<hr>
	<div style="text-align: left;" ><span>&nbsp;&nbsp;비밀번호를 인증해주세요</span></div>
	<div class="profile_form">
	<table class="profile_table">
		<tr class="profile_list">
			<td colspan="2" class="profile_item">
						<input type="file" id="fileupload" name="imageSelector" accept="image/jpeg, image/jpg, image/png" multiple  >
				<div class="profile-img" id="profile-img">
				<img src="/upload/${profile.url }" class="thumb">
				</div>
			</td>
		</tr>
		<tr class="profile_list" >
			<td class="profile_item" colspan="2" >${member.nick } 님</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >아이디</td>
			<td class="profile_item" ><input type="text" name="userid" id="userid" value="${member.userid }" disabled="disabled"></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >비밀번호</td>
			<td class="profile_item" ><input type="password" name="userpw" id="userpw" class="profile-setting" /></td>
		</tr>
	</table>
	<div><button id="btn" class="buttonClass">인증하기</button></div>
	<div><input type="text" style="visibility: hidden;" ></div>
	</div>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>