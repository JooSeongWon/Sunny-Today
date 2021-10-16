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
    	
        $('#content').on('keyup', function() {
            $('#content_cnt').html($(this).val().length+" / 500자");
     
            if($(this).val().length > 500) {
                $(this).val($(this).val().substring(0, 500));
                $('#content_cnt').html("500 / 500자");
            }
        });
    });
    </script>
    
    <style type="text/css">
    #msgTitle {
    	width: 450px;
    	height: 30px;
    	margin: 10px;
    }
	#content {
		width: 450px;
		height: 280px;
		resize: none;
	}
    #square {
    	width: 550px;
    	height: 500px;
    	position: relative;
    	margin: auto;
    	border-radius: 15px;
    }
    #container {
    	margin: auto;
    	text-align: center;
    }
    .line {
    	height: 10px;
    	color: #eaeff8;
    }
    div#write-form {
    	position: absolute;
    	top: 50%;
    	left: 50%;
    	transform: translate(-50%, -50%);
    }
    div#content_cnt {
    	margin: 3px;
    	padding: 5px;
    }
    #dtnSend {
    	background-color: #7ba5c1;
    	color: white;
    }
    hr {
    	margin: 0 auto;
    	padding: 0;
    	border: 0px;
    	background-color: #d7eafa;
    	width: 80px;
    }
    h2 {
    	margin: 0 auto;
    }
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="container">
<form id="message-send" action="<%=request.getContextPath() %>/message/send" method="post">

<br>
<hr size="5" noshade>
<h2>쪽지 쓰기</h2><br>

	<div id="square" style="background-color: #eaeff8;">
		<div id="write-form">
		
			<div id="fromm">받는 사람&nbsp;&nbsp;|&nbsp;&nbsp;<input type="text" name="too" placeholder="받는 사람" required /></div>
					
			<div><input type="text" id="msgTitle" name="msgTitle" placeholder="제목" required/></div>
			
			<div><textarea name="content" id="content" placeholder="내용" required></textarea></div>
			
			<div id="content_cnt" style="float: right;">0 / 500자</div>	
				
		</div>
		
		<div id="btnBox">
			<button type="submit" id="btnSend">보내기</button>
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
