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
    <link href="${cssPath}/boardmain.css" rel="stylesheet">
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript">
	//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영한다
	function submitContents(elClickedObj){
		
		//에디터의 내용을 #content에 반영해준다
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
		try {
			//<form>태그의 submit을 수행한다
			elClickedObj.form.submit();
		} catch(e) {}
	}
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#btnWrite").click(function() {
			//스마트에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
			submitContents( $("#btnWrite") )
			$("form").submit();
		})	
	})
	
	</script>

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
	
<div>
	<form action="<%=request.getContextPath() %>/board/write" method="post" enctype="multipart/form-data">
		<h1>제목</h1>
		<hr>
		<div class="select">
			<select name="select">
				<option value="title" >제목</option>
				<option value="content">본문</option>
				<option value="nick">작성자</option>
			</select>
		</div>
<!-- 		<div class="file"> -->
<!-- 		첨부 : <input type="file" name="file" /><br><br>	 -->
<!-- 		</div> -->
		
<!-- 		<div id="preview"></div> -->
		<div>
		<textarea class="editor" name="content" id="content"/></textarea>
		</div>

		<button id="btnWrite">작성</button>
	</form>
</div>



<!-- <textarea>태그에 스마트에디터2 적용하는 스크립트(반드시<textarea>보다 아래에 적어야 함) -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});
</script>







<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
