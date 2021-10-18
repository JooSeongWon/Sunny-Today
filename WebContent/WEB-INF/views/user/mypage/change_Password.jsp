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
    <script src="${jsPath}/mypage_script2.js"></script>
    

    
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">

<div class="mypage">
	<ul class="mypage_list">
		<a href="<%=request.getContextPath() %>/mypage/profile">
			<li class="mypage_item" >프로필 수정</li>
		</a>
		<a href="<%=request.getContextPath() %>/mypage/password">
			<li class="mypage_item" >비밀번호 변경</li>
		</a>
		<a href="<%=request.getContextPath() %>/mypage/password/check">
			<li class="mypage_item" >회원탈퇴</li>
		</a>
	</ul>
	
	<div class="profile-container">
	
	<div class="profile_form" >
	<table class="profile_table" style="margin-top: 80px">
		<tr class="profile_list">
		<c:if test="${not empty member.password }">
			<td class="profile_item" >비밀번호 등록</td>
			<td class="profile_item" ><input type="password" class="profile-setting" name="newPassword" id="newPassword" placeholder="8~20자 이내"/></td>
		</c:if>
		</tr>
		<tr class="profile_list">
		<c:if test="${empty member.password }">
			<td class="profile_item" >비밀번호</td>
			<td class="profile_item" ><input type="password" class="profile-setting" name="password" id="password" placeholder="8~20자 이내" /></td>
		</c:if>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >비밀번호확인</td>
			<td class="profile_item" ><input type="password" class="profile-setting" name="passwordcheck" id="passwordcheck"/></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" colspan="2" ><button type="button" class="buttonClass" id="btn">비밀번호변경</button></td>
		</tr>
	</table>
	</div>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>