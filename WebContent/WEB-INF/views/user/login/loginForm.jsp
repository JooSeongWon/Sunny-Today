<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -로그인</title>
    <script type="text/javascript">
    $(document).ready(function() {
    	//페이지 첫 접속시 입력창으로 포커스 이동
    	$("input").eq(0).focus();
    	
    	//패스워드 입력 창에서 엔터 입력시 form submit
    	$("input").eq(1).keydown(function(key) {
    		if(key.keyCode == 13) {
    			$(this).parents("form").submit();
    		}
    	})
    	
    	//로그인 버튼 클릭시 form submit
    	$("#btnLogin").click(function() {
    		$(this).parents("form").submit();
    	})
    });
    </script>
    
    <style type="text/css">
    h1 {
		text-align: center;
	}
    #userid, #userpw {
    	width: 300px;
    	height: 32px;
    }
    button {
    	width: 300px;
    	height: 32px;
    }   
    form#login-form {
    	max-width: 300px;
    	display: flex;
    	flex-flow: column;
    	margin: 30px auto;
    }
    .form-group {
    	max-width: 300px;
    	display: flex;
    	justify-content: space-between;
    	margin-bottom: 20px;
    }
    .form-group a, .form-group label {
    	font-size: var(--font-micro);
    }
    input {
    	margin-top: 5px;
    }
    button {
    	margin-top: 3px;
    }
    .login_form__title {
    	display: flex;
    	justify-content: space-between;
    }
    .login_form__title a{
    	position: relative;
    	top: 60px;
    	height: 30px;
    }
    .line {
    	height: 1px;
    	background-color: black;
    }
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div>

<form id="login-form" action="<%=request.getContextPath() %>/member/login" method="post">

	<div class="login-form__header">
		<div class="login_form__title">
			<h1 style="color: rgb(94, 94, 94);">회원 로그인</h1>
			<a href="<%=request.getContextPath() %>/member/join">회원가입</a>
		</div>
		
		<div class="line"></div>
	</div>
	
	<input type="text" id="userid" name="userid" placeholder="아이디" />
	<input type="password" id="userpw" name="userpw" placeholder="비밀번호" />
	
	<div class="form-group">
		<label><input type="checkbox" id="loginMaintain" />로그인 유지</label>
		
		<div>
			<a href="<%=request.getContextPath() %>/find/id">아이디 찾기</a> ㅣ
			<a href="<%=request.getContextPath() %>/find/password">비밀번호 찾기</a>
		</div>
	</div>
	
	<button type="button" id="btnLogin">로그인</button>
	<button type="button" id="btnNaverLogin" style="background-color: rgb(46, 204, 113);">Naver</button>
	<button type="button" id="btnGoogleLogin" style="background-color: rgb(89, 181, 244);">Google</button>

</form>

</div>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
