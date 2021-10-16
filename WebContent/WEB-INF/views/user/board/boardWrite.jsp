<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>



<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -게시글 작성</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/board.css" rel="stylesheet">
    <script src="${jsPath}/board_script.js"></script>


</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div class="How_was_your_day">
	<h2>글 작성</h2>
	당신의 이야기를 들려주세요 :)
</div>
<hr>
	
<div class="writearea">
	<form action="<%=request.getContextPath() %>/board/write" method="post" enctype="multipart/form-data">
		<input type="text" name="title" id="writeTitle" placeholder="제목을 입력하세요"/>
		<hr>
		<div>
			<span>
				<select id="detailSelect" name="select">
					<option value="daily">일상룩</option>
					<option value="buy">지름 게시판</option>
					<option value="share">정보공유</option>
					<option value="asking">질문 응답</option>
				</select>
			</span>
		<span class="filebox">
		<label for="upload">사진추가</label>
		<input type="file" name="file" id="upload" accept="image/gif, image/jpeg, image/png"  /><br><br>	
		</span>
		</div>
		
		<div id="preview"></div>
		<div>
		<textarea name="content" id="writeContent" placeholder="내용을 입력하세요"/></textarea>
		</div>
		
		<hr>

		<button id="btnWriteSubmit">저장</button>
		<button id="btnWriteCancel">취소</button>
	</form>
</div>



<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
