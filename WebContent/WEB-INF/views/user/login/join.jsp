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

<div>

<h1>정보입력</h1>
<h5>(*)은 필수 입력입니다.</h5>

<form action="<%=request.getContextPath() %>/memeber/join" method="post">

	<div class="form-group">
	
		<label>아이디*<input type="text" id="userid" name="userid" placeholder="4자 ~ 20자 사이 영문자/숫자"/></label>	
		<label>비밀번호*<input type="password" class="userpw" name="userpw" /></label>
		<label>비밀번호 확인*<input type="password" class="userpw" name="userpw" /></label>		
		<label>이메일 주소*<input type="text" class="email" name="email" /> @
			<select>
				<option value="">직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="nate.com">nate.com</option>
				<option value="hanmail.com">hanmail.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="daum.net">daum.net</option>	
			</select>
		</label>
		
		<div class="line"></div>
		
		<label>닉네임*<input type="text" id="nick" name="nick" /></label>
		<label>전화번호
			<select>
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="012">012</option>
				<option value="031">031</option>
			</select>
			 - <input type="text" id="phone" name="phone" /> - <input type="text" id="phone" name="phone" />
		</label>
		
		<div class="line"></div>
		
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
