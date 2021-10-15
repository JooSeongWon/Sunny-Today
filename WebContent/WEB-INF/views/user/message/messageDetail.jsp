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
    	
    	//취소 버튼 누르면 뒤로가기
    	$("#btnCancel").click(function() {
    		location.href = "/message";
    	});
    	
    	$("#btnReply").click(function() {
    		location.href = "/message/send";
    	});
    });
    </script>
    
    <style type="text/css">
	#content {
		width: 530px;
		height: 280px;
		resize: none;
	}
    #container {
    	margin: auto;
    	text-align: center;
    }
    .line {
    	height: 10px;
    	color: #eaeff8;
    }
    #square {
    	height: 500px;
    	width: 600px;
    	position: relative;
    	margin: auto;
    	border-radius: 15px;
    }
    div#write-form {
    	position: absolute;
    	top: 50%;
    	left: 50%;
    	transform: translate(-50%, -50%);
    }
    #dtnSend {
    	background-color: #7ba5c1;
    	color: white;
    }
    #fromm-title {
    	bolder: 1px;
    	
    }
    hr {
    	margin: o;
    	padding: 0;
    	border: 0px;
    	background-color: #d7eafa;
    	width: 80px;
    }
    #btnBox {
    	align-items: flex-end;
    }
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="container">
<form id="message-detail" action="<%=request.getContextPath() %>/message/view" method="post">


<br><br><br><br>

<hr size="5" noshade>
<h2>쪽지 상세</h2>
<br><br>

	<div id="square" style="background-color: #eaeff8;">
		<div id="write-form">
			<table id="fromm-title">
				<tr>
					<td class="info">보낸 사람</td><td colspan="3">${viewMessage.fromm }</td>
				</tr>		
				<tr>
					<td class="info">제목</td><td colspan="3">${viewMessage.title }</td>
				</tr>			
			</table>
		
			<div><textarea name="content" id="content" placeholder="내용" required></textarea></div>
					
		</div>
				
	<div id="btnBox">
		<button type="button" id="btnReply">답장</button>
		<button type="button" id="btnCancel">취소</button>
	</div>
		
	</div>



</form>
</div>

<br><br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
