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
	<ul class="mypage_list">
		<a href="<%=request.getContextPath() %>/mypage">
			<li class="mypage_item" >프로필 수정</li>
		</a>
		<a href="<%=request.getContextPath() %>/mypage/taste">
			<li class="mypage_item" >추가정보수정</li>
		</a>
		<a href="<%=request.getContextPath() %>/mypage/pw">
			<li class="mypage_item" >비밀번호 변경</li>
		</a>
		<a href="<%=request.getContextPath() %>/mypage/del">
			<li class="mypage_item" >회원탈퇴</li>
		</a>
	</ul>
	
	<div class="profile-container">
	
	<form action="/mypage" method="post" class="profile_form">
	<table class="profile_table">
		<tr class="profile_list">
			<td colspan="2" class="profile_item">
				<div class="profile-img">프로필사진</div>
			</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >닉네임</td>
			<td class="profile_item" ><input type="text" class="profile-setting" name="nick"/></td>
			<td class="profile_item" ><input type="button" id="btn-check" value="중복검사"/></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >전화번호</td>
			<td class="profile_item" ><input type="text" class="profile-setting" name="phone"/></td>
			<td class="profile_item" >
			<label class="switch">
				<input type="checkbox" class="btn-toggle" id="btn-private-phone"/>
				<span class="slider round"></span>
			</label>
			</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >생년월일</td>
			<td class="profile_item" ><input type="text" class="profile-setting" name="birth"/></td>
			<td class="profile_item" >
				<label class="switch">
				<input type="checkbox" class="btn-toggle" id="btn-private-birth"/>
				<span class="slider round"></span>
			</label>
			</td>
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