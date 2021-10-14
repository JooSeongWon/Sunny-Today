<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
	
	//검색버튼 동작
	$("#btnSearch").click(function() {
		
	});
	
	//게시물 추가버튼 동작
	$("#btnPostUp").click(function() {
		$(location).attr("href", "/SunnyToday/admin/post/write");
	});
	
	
	//전체 체크박스 동작
	$(".checkAll").click(function() {
		$(".checkBoard").prop("checked", this.checked );
	});

	// 삭제버튼 동작
	$(".btnDelete").click(function(e) {
		if( confirm("게시판을 삭제하시겠습니까?") ) {
			$(location).attr("href", `/SunnyToday/admin/board/delete?board_no=\${$(this).attr("data-boardNo")}`);
		}
	});
	
});

</script>

<style>

#btnCheck {
	background-color: black;
	color: white;
}

#btnPostUp {
 text-align: right;
}

</style>

<h1>게시물 관리</h1>

<hr style="border: inset 3px black;">

<div style="background: #BDBDBD;">


<select id="search" name="search">
    <option value="category_name" selected="selected">카테고리명</option>
</select>
<input type="text">
<button type="button" id="btnSearch" class="btn btn-default">검색</button>
<br>

</div>
<br>


<div align="right" >
<button type="button" id="btnPostUp" >+ 게시글 등록</button>
</div>

<button type="button" id="btnCheck">선택 삭제</button>

<table class="table table-hover">
<tr>
		<th><input type="checkbox" id= "checkAll" class="checkAll" name="checkAll" value="checkAll" ></th>
	<th>No.</th>
	<th>위치</th>
	<th>내용</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>삭제</th>
</tr>
	
<form action="Admin/post/list" method="post">
<%-- <c:forEach items="${boardList }" var="board"> --%>
<tr>
	<td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td>
	<td>No.</td> 
	<td>위치</td>
	<td>내용</td>
	<td>작성자</td>
	<td>작성일</td>
	<td>
		<button type="button" class="btn btn-danger btn-sm btnDelete" data-boardNo="${board.board_no}">삭제</button>
	</td>
</tr>
<%-- </c:forEach> --%>
</form>
</table>


<c:import url="/WEB-INF/views/admin/post/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />