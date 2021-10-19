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
    	$("#btnSend").click(function() {
    		$(location).attr("href", "/message/write");
    	}); 
    	
    	$("#btnDelete").click(function() {
    		if( confirm("쪽지를 삭제하시겠습니까?") ) {
    			$("#messageList").submit();    			
//     			$(location).attr("href", "/message/delete?no="+$(this).attr('value'));
    		}
    	});
    	
    	//체크 박스
    	$('.check-all').click(function(){
    		$('.check').prop('checked', this.checked);
    	})		
    });
    
    </script>
    
    <link rel="stylesheet" href="${cssPath }/paging_style.css"> <!-- 한번 가서 보여주세요 맘에 안드네요 잠만요닙[넵 -->
        
    <style type="text/css">
    table {
    	margin: 10px auto;
    	width: 80%;
		border-collapse: collapse;
		font-weight: lighter;
		color: #333333;
    }
    td, th {
		border-top: 1px solid var(--color-grey);
		border-bottom: 1px solid var(--color-grey);
		text-align: center;
	}
	td {
		font-weight: 100;	
	}
	th {
		background-color: #e2e2e2;
	}
	#btnBox {
		text-align: right;
		margin: 10px auto;
		padding: 10px 153px 10px 20px;
	}
	select {
		margin-right: 154px;
		margin-top: 10px;
	}
	#btnDelete {
		width: 90px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #616161;
		border: 1px solid #616161;
	}
	#btnSend {
		width: 90px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #7ba5c1;
		border: 1px solid #7ba5c1;
	}
	hr {
     	border: 0px;
     	background-color: #d7eafa;
     	width: 80px; 
    }
    h2 {
		margin: 0 auto;
		text-align: center;
		color: #333333;
	}
	select {
		width: 100px;
		height: 30px;
		float: right;
	}	
	.clearbox {
		clear: both;
		width: 0;
		height: 0;
		overflow: hidden;
	}
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<%-- 받은 쪽지함 --%>
<div id="message">

	<form action="<%=request.getContextPath() %>/message" method="post" id="message">
		<div>
			<br>
			<select onchange="if(this.value === '받은 쪽지함') location.href='<%=request.getContextPath() %>/message'; else location.href = '<%=request.getContextPath() %>/messagesend'">
				<option value="받은 쪽지함" selected>받은 쪽지함</option>
				<option value="보낸 쪽지함" >보낸 쪽지함</option>
			</select>
		</div>		
				
		<br>
		<div class="clearbox"></div>
		<hr size="5" noshade>
		<h2>받은 쪽지함</h2>
		<br>
	</form>
	
	<form action="<%=request.getContextPath() %>/message/delete" method="post" id="messageList">
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" name="check-all" class="check-all" /></th>
					<th>번호</th>
					<th>제목</th>
					<th>보낸 사람</th>
					<th>날짜</th>
				</tr>
			</thead>

			<% int i =0; %> 
			<c:forEach items="${messageList }" var="message">
			<tr>
				<td><input type="checkbox" name="delno<%=i %>" value="${message.message_no }" class="check"/></td>		
				<td><%=i+1 %></td>
				<td>
					<a href="<%=request.getContextPath() %>/message/view?message_no=${message.message_no }">
					${message.title }
					</a>
				</td>
				<td>${message.fromNick }</td>
				<td>${message.post_date }</td>
			</tr>
			<% i++; %>
			</c:forEach>
		</table>
	</form>
		<div id="btnBox">
		<button type="button" id="btnDelete">삭제</button>&nbsp;&nbsp;<button type="button" id="btnSend">쪽지 쓰기</button>
		</div>
</div>

<div id="paging">
	<c:import url="/WEB-INF/views/user/layout/message_paging.jsp" />		
</div>

<%--footer--%>

<c:import url="../layout/footer.jsp"/>
</body>
</html>
