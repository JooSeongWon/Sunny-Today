<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
	
// 	var target = document.getElementById("authority");
//     var select = target.options[target.selectedIndex].value);     // 옵션 text 값
    
//     if(select == all ){
//     	alert(this.value);
//     } else if (select == member){
//     	M
//     } else(select == admin) {
//     	A
//     }
    
	
	//확인버튼 동작
	$("#btnOk").click(function() {
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
});
</script>

<h1>게시판 추가</h1>
<hr style="border: inset 3px black;">


					
<form action="<%=request.getContextPath()%>/admin/board/write" method="post" >
			
<table class="table table-bordered">
<tr>
	<td class="col-xs-2">카테고리명</td><td><input type="text" name="title"></td>
</tr>

<tr>
	<td rowspan="5">권한</td>
</tr>
<tr>
	<td>목록보기
<!-- 		<select id="viewList" name="authority"> -->
		<select id="viewList" name="list_grant">
			<option value="N" selected="selected">전체</option>
			<option value="M" >회원</option>
			<option value="A" >관리자</option>
		</select>
	</td>
</tr>

<tr>
	<td>내용보기
<!-- 		<select id="viewContent" name="authority"> -->
		<select id="viewContent" name="read_grant">
			<option value="N" selected="selected">전체</option>
			<option value="M" >회원</option>
			<option value="A" >관리자</option>
		</select>
	</td>
</tr>

<tr>
	<td>글쓰기
<!-- 		<select id="ContentWrite" name="authority"> -->
		<select id="ContentWrite" name="write_grant">
			<option value="M" selected="selected">회원</option>
			<option value="A" >관리자</option>
		</select>
	</td>
</tr>

<tr>
	<td>댓글쓰기
<!-- 		<select id="commentWrite" name="authority"> -->
		<select id="commentWrite" name="comments_grant">
			<option value="M" selected="selected">회원</option>
			<option value="A" >관리자</option>
		</select>
	</td>
</tr>

<!-- <tr> -->
<!-- 	<td>추천 기능</td> -->
<!-- 	<td> -->
<!-- 		<input type="radio">사용 &nbsp;&nbsp; <input type="radio">미사용 -->
<!-- 	</td> -->
<!-- </tr> -->

<!-- <tr> -->
<!-- 	<td>댓글 허용</td> -->
<!-- 	<td> -->
<!-- 		<input type="radio">허용 &nbsp;&nbsp; <input type="radio">비허용 -->
<!-- 	</td> -->
<!-- </tr> -->

<!-- <tr> -->
<!-- 	<td>제목 글자수</td> -->
<!-- 	<td><input type="text" maxlength="20" value="text1"></td> -->
<!-- </tr> -->


</table>

<div class="text-center">
	<button type="button" id="btnOk" class="btn btn-info">확인</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

</form>

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />