<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.*"%>
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
    	$("#btnsubmit").click(function(){
    		var url = "<%=request.getContextPath() %>/main"
    		$(location).attr('href',url);
    	  		
	    });
    });
</script>

</head>
<body>

<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="mypage-container">


<div class="mypage">
	<div class="profile-container">
	<div style="text-align: left;" ><h1>&nbsp;&nbsp;탈퇴완료</h1></div>
	<hr>
	<div style="text-align: left;" ><span></span></div>
	<form action="<%=request.getContextPath() %>/leaveid" method="post" class="profile_form"  enctype="multipart/form-data" >
	<table class="profile_table">
		<tr class="profile_list">
			<td class="profile_item"><h3>회원탈퇴가 완료되었습니다</h3></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" ><p>오늘도맑음을 이용해주시고 사랑해주셔서 감사합니다.</p></td>
		</tr>
		<tr class="profile_list">
			<td class="profile_item" ><p>더욱 더 노력하고 발전하는 오늘도맑음이 되겠습니다.</p></td>
		</tr>
	</table>
		<div><button type="button" id="btnsubmit" class="buttonClass">확인</button></div>
		<div><input type="text" style="visibility: hidden;" ></div>
	</form>
	</div>
</div>
</div>
<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>