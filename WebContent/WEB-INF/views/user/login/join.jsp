<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <script type="text/javascript">
	$(document).ready(function() {
		//페이지 첫 접속 시 입력창으로 포커스 이동
		$("input").eq(0).focus();
	
		//닉네임 입력 창에서 엔터 입력 시 form submit
		$("input").eq(2).keydown(function(key) {
			if(key.keyCode == 13) {
				$(this).parents("form").submit();
			}
		})
	
		//로그인 버튼 클릭 시 form submit
		$("#btnJoin").click(function() {
			$(this).parents("form").submit();
		})
		
		//취소 버튼 누르면 뒤로가기
		$("#btnCancel").click(function() {
			history.go(-1);
		})
	});
	</script>
	
	<style type="text/css">
	
	</style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div style="text-align: center;">

<h1>정보입력</h1>
<hr>

<form action="/memeber/join" method="post">

	<div class="form-group">
		<label for="userid" class="control-label">아이디</label>
		<input type="text" id="userid" name="userid" />
	</div>
	<div class="form-group">
		<label for="userpw"	class="control-label">비밀번호</label>
		<input type="text" class="userpw" name="userpw" />
	<div class="form-group">
		<label for="email" class="control-label">이메일</label>
		<input type="text" class="email" name="email" />
	</div>
	<hr>
	<div class="form-group">
		<label for="nick" class="control-label">닉네임</label>
		<input type="text" class="nick" name="nick" />
	</div>
	<div>
		<button type="button" id="btnJoin" class="btn btn-primary">가입</button>
		<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
	
</form>




</div>





<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
