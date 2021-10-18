<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">

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

#delete{
       position: relative;
        left: 690px;
        top: 20px;
}
</style>

<h1>게시물 통합 관리</h1>

<hr style="border: inset 3px black;">


<!-- <div style="background: #BDBDBD;"> -->
<%-- ${viewPost[0].title} --%>
<table id="tb"class="table table-bordered">
<form action="<%=request.getContextPath() %>/admin/post/view" method="get">
<input type="hidden" name="postno" id="postno" value="${viewPost.post_no }" />
<tr>
	<td class="col-xs-2" height="50px">게시물 제목</td>
	<td>${viewPost.title }</td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">게시글 작성자</td>
	<td>${nick }</td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">작성된 날짜</td>
	<td>${viewPost.write_date }</td>
</tr>
<tr>
	<td class="col-xs-2" height="260px">내용</td>
	<td><textarea cols="90" rows="16" readonly>${viewPost.content }</textarea></td>
</tr>
<tr>
	<td class="col-xs-2" height="50px">업로드된 파일</td>
	<td></td>
</tr>	

<%-- </c:forEach> --%>
</table>

<%-- <button type="button" id="update" class="btn btn-info btn-sm btnUpdate" data-boardNo="${board.board_no}">수정</button> --%>
<button type="button" id="delete" class="btn btn-danger btn-sm btnCancel" data-boardNo="${board.board_no}">삭제</button>
</form>












<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />