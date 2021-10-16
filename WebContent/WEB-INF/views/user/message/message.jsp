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
  	if(${empty userno}) {
 		window.alert("로그인이 필요합니다!")
 		window.location.assign("<%= request.getContextPath() %>/")  
  	};  	
    $(document).ready(function() {
    	
    	//쪽지 쓰기 버튼 누르면 이동
    	$("#btnMsgWrite").click(function() {
    		location.href="/message/send";
    	}); 
    	
    	$("btnDelete").click(function() {
    		if( confirm("쪽지를 삭제하시겠습니까?") ) {
    			$(location).attr("href", "/message/delete?message_no=${viewMessage.message_no }")
    		}
    	});

		//최상단 체크박스 클릭
		$("#checkAll").click(function(){
			//클릭되었으면
			if($("#checkAll").prop("checked")){
			//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[id=chk]").prop("checked",true);
			//클릭이 안되있으면
			}else{
			//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[id=chk]").prop("checked",false);
			}
		})   	
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
	#btnBox {
		text-align: right;
		margin: auto;
	}
    </style>
    <link href="${cssPath}/message_main.css" rel="stylesheet">
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<%-- 받은 쪽지함 --%>
<div id="message">
	<form id="message-List" action="<%=request.getContextPath() %>/message/send" method="post">
		<div id="message_sendReceive">
			<select style="height: 30px; width: 100px; float: right;">
				<option value="받은 쪽지함">받은 쪽지함</option>
				<option value="보낸 쪽지함">보낸 쪽지함</option>
			</select>
		</div>
		
		<hr size="5" noshade>
		<h2 style="color: #616161; text-align: center;">쪽지함</h2>
		
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th><input id="checkAll" type="checkbox" /></th>
					<th>번호</th>
					<th>제목</th>
					<th>보낸 사람</th>
					<th>날짜</th>
				</tr>
			</thead>
		
			<c:forEach items="${messageList }" var="message">
			<tr>
				<td><input type="checkbox" id="chk" /></td>				
				<td>${message.rnum }</td>
				<td>
					<a href="/message/view?message_no=${message.message_no }">
					${message.title }
					</a>
				</td>
				<td>${message.fromm }</td>
				<td>${message.post_date }</td>
			</tr>
			</c:forEach>
		</table>
		
		<div id="btnBox">
		<button id="btnDelete">삭제</button>&nbsp;&nbsp;<button id="btnSend">쪽지 쓰기</button>
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
