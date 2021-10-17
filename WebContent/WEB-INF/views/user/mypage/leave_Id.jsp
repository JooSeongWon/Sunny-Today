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

<script type="text/javascript">
    $(document).ready(function(){
    	$("#btn-check").click(function() {
			console.log($("#btn-check").prop('checked') )
		})
    	
    	$("#btnsubmit").click(function(){
    		if($("#btn-check").prop('checked') == false){
    			showModal("오늘도 맑음", "이용약관을 확인해주세요" )
    			return;
    		} else{
    		$("#form").submit();
    		}
    		
	    });
    });
</script>

<style type="text/css">
#content {
	width: 98%;
}

textarea {
	height: 157px;
    width: 600px;
    resize:none;
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
	<div class="profile-container">
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;회원탈퇴</h1></div>
	<hr>
	<div style="text-align: left;" ><span>&nbsp;&nbsp;약관동의</span></div>
	<form action="/leaveid" method="post" class="profile_form" enctype="multipart/form-data" >
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item"><textarea readonly id="read"></textarea></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" ><input type="checkbox" id="btn-check" class="buttonClass"/> 동의</td>
		</tr>
	</table>
		<div><button type="button" id="btnsubmit" class="buttonClass">탈퇴하기</button></div>
		<div><input type="text" style="visibility: hidden;" ></div>
	</form>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>