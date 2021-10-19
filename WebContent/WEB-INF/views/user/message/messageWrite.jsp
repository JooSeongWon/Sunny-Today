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
        
        //자동완성이 없으니까 자신이 없네요 맞게쓰는건지 틀리게 쓰는건지 일단 돌려보죠 넵
        const too = document.querySelector('#too');
        const content = document.querySelector('#content');
        const msgTitle = document.querySelector('#msgTitle');
      const btnSend = document.querySelector('#btnSend');

      btnSend.addEventListener('click', () => $.ajax({
          type: 'POST',
          url: contextPath + 'message/write',
          data: { too: too.value, content: content.value, msgTitle: msgTitle.value },
          dataType: 'json',
          success: data => {if(data.result)showModal('성공', '쪽지를 발송했습니다.', () => location.href = '/message'); else showModal('실패', data.msg);},
          error: () => showModal('연결 오류', '서버와 정상적으로 통신하지 못했습니다.')
       }));
        
        
  
    });
//     function inputLengthCheck(eventInput){
//     	var inputText = $(eventInput).val();
//     	var inputMaxLength = $(eventInput).prop("maxlength");

//     	var j = 0;
//     	var count = 0;

//     	for(var i = 0;i < inputText.length;i++) { 
// 	    	val = escape(inputText.charAt(i)).length; 
	    	
// 	    	if(val == 6){
// 	    			j++;
// 	    	}
// 	    		j++;
	
// 	    	if(j <= inputMaxLength){
// 				count++;
// 	    	}
//     	}
//     	if(j > inputMaxLength){
// 	    	$(eventInput).val(inputText.substr(0, count));
//     	}
//     }
    </script>
    
    <style type="text/css">
    #container {
    	margin: auto;
    	text-align: center;
    }
    #msgTitle {
    	width: 530px;
    	height: 30px;
    	margin: 10px;
    }
    #content {
		width: 530px;
		height: 280px;
		resize: none;
	}
	#too {
		width: 430px;
		height: 30px;
	}
	#content_cnt {
		margin: 0 auto;
		margin-left: 465px;
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
	h2 {
		margin: 0 auto;
		color: #333333;
	}    
	#write-form {
		margin-top: 40px;
	}
	#btnSend {
		width: 60px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #616161;
		border: 1px solid #616161;
	}
	#btnCancel {
		width: 60px;
		height: 30px;
		border-radius: 3px;
		color: white;
		background-color: #7ba5c1;
		border: 1px solid #7ba5c1;
	}
    </style>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="container">
<form id="message-send" action="<%=request.getContextPath() %>/message/write" method="post">

<br><br><br><br>

<hr size="5" noshade>
<h2>쪽지 쓰기</h2>
<br><br>
	<div id="square" style="background-color: #eaeff8;">
		<div id="write-form">		
			<div id="fromm">받는 사람&nbsp;&nbsp;ㅣ&nbsp;&nbsp;<input type="text" id="too" name="too" placeholder="받는 사람" required /></div>					
			<div><input type="text" id="msgTitle" name="msgTitle" placeholder="제목" maxlength="50" required/></div>	
			<div><textarea name="content" id="content" placeholder="내용" required></textarea></div>			
			<div id="content_cnt">0 / 500자</div>					
		</div>
		
		<div id="btnBox">
			<button type="button" id="btnSend">보내기</button>
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
