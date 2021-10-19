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

<style type="text/css">

.input-file-button{
  padding: 5px;
  background-color:var(--color-sky);
  color: white;
  cursor: pointer;
  border-radius:42px;
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

	<form action="<%=request.getContextPath() %>/mypage/profile" method="post" class="profile_form" enctype="multipart/form-data" >
	<table class="profile_table">
		<tr class="profile_list">
			<td colspan="3" class="profile_item">
						<input type="file" id="fileupload" name="imageSelector" accept="image/jpeg, image/jpg, image/png" multiple  >
				<div class="profile-img" id="profile-img">
				<img src="/upload/${profile.url }" class="thumb">
				</div>
			</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" colspan="3" >
					<div class="updatefile">
					<label class="input-file-button" for="fileupload">
  									사진선택
					</label>
					</div>
			</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >닉네임</td>
			<td class="profile_item" ><input type="text" class="profile-setting" id="nick" name="nick" value="${member.nick }"/></td>
			<td class="profile_item" ><input type="button" id="btn-check" class="buttonClass" value="중복검사"/></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >이메일</td>
			<td class="profile_item" ><input type="text" class="profile-setting" 
						name="email" disabled="disabled" value="${member.email }"/></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >전화번호</td>
			<td class="profile_item" ><input type="text" name="phone" class="profile-setting" value="${member.phone }"/></td>
			<td class="profile_item" >
			<label class="switch">
				<c:choose>
				<c:when test="${member.phone_open == 'Y' }">
					<input type="checkbox" class="btn-toggle" id="btn-private-phone" name="phon_open" checked="checked" value="${member.phone_open }"/>
				</c:when>
				<c:when test="${member.phone_open == 'N' }">
					<input type="checkbox" class="btn-toggle" id="btn-private-phone" name="phon_open" value="${member.phone_open }"/>
				</c:when>
				</c:choose>
				<span class="slider round"></span>
			</label>
			</td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" >생년월일</td>
			<td class="profile_item" ><input type="date" class="profile-setting" name="birth" value="${member.birth }"/></td>
			<td class="profile_item" >
				<label class="switch">
				<c:choose>
				<c:when test="${member.birth_open == 'Y' }">
					<input type="checkbox" class="btn-toggle" id="btn-private-birth" name="birth_open" checked="checked" value="${member.birth_open }"/>
				</c:when>
				<c:when test="${member.birth_open == 'N' }">
					<input type="checkbox" class="btn-toggle" id="btn-private-birth" name="birth_open" value="${member.birth_open }"/>
				</c:when>
				</c:choose>
				<span class="slider round"></span>
			</label>
			</td>
		</tr>
		<tr>
			<td colspan="3" ><button id="btnsubmit" class="buttonClass">수정</button></td>
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