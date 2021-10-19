<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">

$(document).ready(function() {	
	
	//파일 preview
	$("#upload").change(function( e ) {
		
		var files = e.target.files;
		
		//이미지만 처리할 수 있도록 적용
		if( !files[0].type.includes("image") ) {
			alert("이미지가 아닙니다")
			
			e.target.value = null;
						
			return false;
		}
		
		var reader = new FileReader();
		
		reader.onload = function( e ) {
			console.log( e )
			console.log( e.target.result )
			
			$("#preview").html(
					$("<img>").attr({
						"src": e.target.result
						, "width" : 300
						, "height" : 200
					}))			
		}
		reader.readAsDataURL( files[0] )		
	})	
	
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

<style>
#tb {
        width:800px;
        margin:auto;
        height: 500px;
}

#btnOk {
       position: relative;
        left: 670px;
        top: 20px;

}

#btnCancel{
       position: relative;
        left: 690px;
        top: 20px;
}
</style>

<h1>게시물 통합 관리</h1>

<hr style="border: inset 3px black;">


<h1 align= "center">게시글 등록</h1>

<form action="<%=request.getContextPath()%>/admin/post/write" method="post" enctype="multipart/form-data" >

<table id="tb" class="table table-bordered">

<tr>
	<td class="col-xs-2" height="50px">게시물 제목</td>
	<td><input type="text" name="title"/></td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">카테고리</td>
	<td>
		<select id="category" name="select">
    		<option value="daily_clothes" >공지</option>
    		<option value="buying" >이벤트</option>
		</select>
	</td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">게시글 작성자</td>
	<td>${nick }</td>
</tr>
<tr>
	<td class="col-xs-2" height="260px">내용</td>
	<td><textarea cols="90" rows="16" name="content"></textarea></td>
</tr>
</form>
<tr>
	<td class="col-xs-2" height="50px">파일 첨부</td>
	<td><input type="file" id="upload" name="file" id="upload"/>
	<div id="preview"></div></td>
</tr>	

</table>

 

<button type="button" id="btnOk" class="btn btn-info btn-sm btnUpdate"  >등록</button>
<button type="button" id="btnCancel" class="btn btn-danger btn-sm btnCancel">취소</button>











<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />