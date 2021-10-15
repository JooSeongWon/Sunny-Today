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
    	
    	//쪽지 쓰기 버튼 누르면 이동
    	$("#btnMsgWrite").click(function() {
    		location.href="/message/send";
    	});    	
    });
    </script>
    
    <style type="text/css">
    hr {
    	margin: o;
    	padding: 0;
    	border: 0px;
    	background-color: #d7eafa;
    	width: 80px;
    }
    div#messageList {
    	text-align: center;
    }
    #paging {
    	text-align: center;
    }
    table {
		border-collapse: collapse;
		text-align: center;
		line-height: 1.5;
		margin: 10px;
		font-regular: 18px;
		width: 1300px;
		margin: 0 auto;
    }
	thead {
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
		background: #d9d6d2;
		font-weight: 200;
	}
	th {
		border-bottom: 1px solid #ccc;
		font-weight: 100;
	}
	#btnBox {
		float: right;
	}
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>


<div id="messageList">
<form id="message-List" action="<%=request.getContextPath() %>/message" method="post">

	<select style="height: 30px; width: 110px;" style="text-align-last: center;">
		<option value="받은 쪽지함">받은 쪽지함</option>
		<option value="보낸 쪽지함">보낸 쪽지함</option>
	</select>

<hr size="5" noshade>
<h2>쪽지함</h2>

<table>
	<thead>
		<tr>
			<th style="width: 5%"><input type="checkbox" /></th>
			<th style="width: 10%">번호</th>
			<th style="width: 35%">제목</th>
			<th style="width: 20%">보낸 사람</th>
			<th style="width: 30%">날짜</th>
		</tr>
	</thead>

<%-- <c:forEach items="${messageList }" var="message"> --%>
<!-- <tr> -->
	
<%-- 	<td>${message.message_no }</td> --%>
<!-- 	<td> -->
<%-- 		<a href="/message/view?message_no=${message.message_no }"> --%>
<%-- 		${message.title } --%>
<!-- 		</a> -->
<!-- 	</td> -->
<%-- 	<td>${message.userid }</td> --%>
<%-- 	<td>${message.writeDate }</td> --%>
<!-- </tr> -->
<%-- </c:forEach> --%>
	<tbody>
		<tr style="text-align: center;">
			<th><input type="checkbox" /></th>
			<th>1</th>
			<th>title</th>
			<th>admin1</th>
			<th>2021-10-15 02:25</th>
		</tr>
		<tr style="text-align: center;">
			<th><input type="checkbox" /></th>
			<th>1</th>
			<th>title</th>
			<th>admin1</th>
			<th>2021-10-15 02:25</th>
		</tr>
		<tr style="text-align: center;">
			<th><input type="checkbox" /></th>
			<th>1</th>
			<th>title</th>
			<th>admin1</th>
			<th>2021-10-15 02:25</th>
		</tr>
		<tr style="text-align: center;">
			<th><input type="checkbox" /></th>
			<th>1</th>
			<th>title</th>
			<th>admin1</th>
			<th>2021-10-15 02:25</th>
		</tr>
	</tbody>
</table>

<div id="btnBox">
	<button id="btnDelete" class="btn btn-primary">삭제</button>
	<button id="btnMsgWrite" class="btn btn-primary">쪽지 작성</button>
</div>

</form>
</div>

<div id="paging">
	<c:import url="../layout/paging.jsp" />
</div>


<%--footer--%>

<c:import url="../layout/footer.jsp"/>
</body>
</html>
