<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	int postno = Integer.parseInt(String.valueOf(request.getAttribute("postno")));
	boolean res = (boolean) request.getAttribute("res");
%>

<script>
	<%if(res==true) {%>
		location.replace("/board/detail?postno=<%=postno %>");
	<%} else {%>
		showModal("오늘도 맑음", "댓글 작성이 실패하였습니다 :( 지속적인 문제 발생시 관리자에게 문의해주세요!") 
		location.replace("/board/detail?postno=<%=postno %>");
	<%} %>

</script>

</head>
<body>
</body>
</html>