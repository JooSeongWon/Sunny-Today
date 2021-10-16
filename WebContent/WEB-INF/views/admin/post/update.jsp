<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
	//확인버튼 동작
	$("#update").click(function() {
		
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#cancel").click(function() {
		history.go(-1);
	});

});
</script>
<style>
#tb {
        width:800px;
        margin:auto;
        height: 500px;
}

#update {
       position: relative;
        left: 670px;
        top: 20px;

}

#cancel{
       position: relative;
        left: 690px;
        top: 20px;
}
</style>

<h1>게시물 통합 관리</h1>

<hr style="border: inset 3px black;">


<!-- <div style="background: #BDBDBD;"> -->
<h1 align= "center">게시글 등록</h1>

<table id="tb"class="table table-bordered">

<tr>
	<td class="col-xs-2" height="50px">게시물 제목</td>
	<td><input type="text"/></td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">카테고리</td>
	<td>
		<select>
			<option>자유게시판</option>
		</select>
	</td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">게시글 작성자</td>
	<td></td>
</tr>
<tr>
	<td class="col-xs-2" height="260px">내용</td>
	<td><textarea cols="90" rows="16"></textarea></td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">파일 첨부</td>
	<td><input type="file" /></td>
</tr>	

</table>


<button type="button" id="update" class="btn btn-info btn-sm btnUpdate" data-postNo="${post.post_no}">수정</button>
<button type="button" id="cancel" class="btn btn-danger btn-sm btnCancel" data-postNo="${post.post_no}">취소</button>

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />