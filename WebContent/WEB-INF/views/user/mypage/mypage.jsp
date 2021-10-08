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
</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div>
	<div class="mypagebar">
		<a href="#">
			<div>프로필 수정</div>
		</a>
		<a href="#">
			<div>추가정보 수정</div>
		</a>
		<a href="#">
			<div>비밀번호 변경</div>
		</a>
		<a href="#">
			<div>회원 탈퇴</div>
		</a>
	</div>
	<div class="mypage-setting">
	<form action="/mypage" method="post">
	<table class="profile-table">
		<tbody>
			<tr>
				<td colspan="2">
					<div class="profile-img">프로필사진</div>
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" class="profile-setting" name="nick"/></td>
				<td><input type="button" class="btn-check" value="중복검사"/></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" class="profile-setting" name="nick"/></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" class="profile-setting" name="nick"/></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" class="profile-setting" name="nick"/></td>
			</tr>
			
		</tbody>
	</table>
	</form>
	
	
	</div>
</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>