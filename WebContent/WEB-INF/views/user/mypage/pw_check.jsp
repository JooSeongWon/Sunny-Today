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
    <link href="${cssPath}/mypage_style.css" rel="stylesheet">
    <script src="${jsPath}/mypage_script.js"></script>
    
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">

<div class="mypage">
	
	<div class="profile-container">
	
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
	</form>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>