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
    	
    	//목록 버튼 누르면 뒤로가기
    	$("#btnList").click(function() {
//     		location.href = "/message";
    	});
    	
    	$("#btnReply").click(function() {
    		location.href = "/message/write";
    	});
    	
    	$("#btnDelete").click(function() {
    		if( confirm("쪽지를 삭제하시겠습니까?") ) {
    			$(location).attr("href", "/message/delete?message_no=${viewMessage.message_no }");
    		}
    	});
    	
    });
    </script>
    
    <style type="text/css">
	#content {
		width: 530px;
		height: 280px;
		resize: none;
		pointer-events: none;
		margin-bottom: 20px;
	}
    #container {  
    	text-align: center;
    	
    }
    #square {
    	height: 500px;
    	width: 600px;
    	position: relative;
    	margin: auto;
    	border-radius: 15px;
    	padding-top: 5px;
    }
    hr {
    	margin: 0 auto;
    	padding: 0;
    	border: 0px;
    	background-color: #d7eafa;
    	width: 80px;
    }
    #fromm-title {
    	margin-top: 20px;
    	margin-bottom: 10px;
    	margin-left: 40px;
    	color: #333333;
    }
	table {
		width: 525px;
		text-align: left;
		font-weight: 100;
		text-align: left;
	}
	th, td {
		cell-spacing: 10px;
		font-weight: 100;
	}
	h2 {
		margin: 0 auto;
		color: #333333;
	}
	#write-form {
		margin-top: 20px;
	}

	#btnList {
		width: 50px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #616161;
		border: 1px solid #616161;
	}
	#btnReply {
		width: 50px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #7ba5c1;
		border: 1px solid #7ba5c1;
	}
	#btnDelete {
		width: 50px;
		height: 30px;
		border-radius: 3px;
		color: white;	
		background-color: #dbb2b2;
		border: 1px solid #dbb2b2;
	}
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="container">

<br><br><br><br>

<hr size="5" noshade>
<h2>쪽지 상세</h2>
<br><br>
	<div id="square" style="background-color: #eaeff8;">
		<div id="write-form">
			<table id="fromm-title">
				<tr>
					<td class="info">보낸 사람 ㅣ</td><td width="430px;">${viewMessage.fromNick } 님</td>
				</tr>
				<tr>
					<td class="info">받는 사람 ㅣ</td><td>${viewMessage.tooNick } 님</td>
				</tr>		
				<tr>
					<td class="info">보낸 시간 ㅣ</td><td>${viewMessage.post_date }</td>
				</tr>
				<tr>
					<td class="info">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 목 ㅣ</td><td>${viewMessage.title }</td>
				</tr>							
			</table>		
			<div><textarea name="content" id="content" readonly>${viewMessage.content }</textarea></div>				
			<div id="btnBox">
				<c:if test="${sessionScope.nick ne viewMessage.fromNick}"><button id="btnReply">답장</button></c:if>
				<button id="btnList" onclick="history.back()">목록</button>
				<button id="btnDelete">삭제</button>
			</div>				
		</div>					
	</div>
</div>

<br><br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
