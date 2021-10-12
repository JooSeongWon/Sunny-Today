<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
	
	//게시판 검색버튼 동작
	$("#btnSearch").click(function() {
		
	});
	
	//게시판 추가버튼 동작
	$("#btnBoardUp").click(function() {
		$(location).attr("href", "/SunnyToday/admin/board/write");
// 		$(location).attr("href", "/SunnyToday/admin/board/write?boardno=${viewBoard.board_no }");
	});
	
	//체크된 게시판
// 	$("#btnCheck").click(function() {
// 		if ($("input:checkbox[id='checkAll']").prop("checked")){
// 			$("input[type=checkbox]").prop("checked", true);			
// 		} else {
// 			$("input[type=checkbox]").prop("checked", false);			
// 		}
// 	});
	
	//선택 삭제
// 	$(".btnCheck").click(function() {
// 	});
	
	$("#check").click(function(){
	if($("#check").is(":checked")){
		
	$('#Arthur').css("visibility", "hidden"); 
	
	} else {
		
	$('#Arthur').css("display", "inline"); 
		
	}

	});
	
	//전체 체크박스 동작
	$(".checkAll").click(function() {
		$(".checkBoard").prop("checked", this.checked );
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/SunnyToday/admin/board/update?boardno=${viewBoard.board_no }");
	});
	
	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("게시판을 삭제하시겠습니까?") ) {
			$(location).attr("href", "/admin/board/delete?boardno=${viewBoard.boardno }");
		}
	});
	
});

</script>

<style>

/* .checkBoard:checked i { */
/*  visibility: visible; */
/* } */

/* i { */
/* visibility: hidden; */
/* } */

/* #check:checked ~ * .down { */
/*   visibility: visible; */
/* } */


#btnCheck {
	background-color: black;
	color: white;
}

#btnBoardUp {
 text-align: right;
}

</style>

<h1>게시판 관리</h1>

<hr style="border: inset 3px black;">

<div style="background: #BDBDBD;">

<h2>게시판 검색</h2>

<select id="search" name="search">
    <option value="category_name" selected="selected">카테고리명</option>
</select>
<input type="text">
<button type="button" id="btnSearch" class="btn btn-default">검색</button>
<br>

</div>
<h3>총 게시판 수 :<c:out value="${boardCount}" /></h3>
<br>


<div align="right" >
<button type="button" id="btnBoardUp" >+ 게시판 추가</button>
</div>

<button type="button" id="btnCheck">선택 삭제</button>

<table class="table table-hover">
<tr>
	<th></th>
	<th><input type="checkbox" id= "checkAll" class="checkAll" name="checkAll" value="checkAll" ></th>
	<th>No.</th>
	<th>게시판명</th>
	<th>게시글 수</th>
	<th>목록보기</th>
	<th>내용보기</th>
	<th>글쓰기</th>
	<th>댓글쓰기</th>
	<th>수정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;삭제</th>
</tr>

<c:forEach items="${boardList }" var="board">
<tr>
	
	<td id="Arthur"><i class="fas fa-arrow-up"></i><i class="fas fa-arrow-down"></i></td>
	<td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td>
	<td>${board.board_no }</td> <%--No. --%> 
	<td>${board.title }</td> 
	<td><c:out value="${titleCount}" /></td>
	<td>
		<c:choose>
			<c:when test="${board.list_grant eq 'N'}">전체</c:when>
			<c:when test="${board.list_grant eq 'A'}">회원</c:when>
			<c:when test="${board.list_grant eq 'M'}">관리자</c:when>
		</c:choose>
	</td>
	<td>
		<c:choose>
			<c:when test="${board.read_grant eq 'N'}">전체</c:when>
			<c:when test="${board.read_grant eq 'A'}">회원</c:when>
			<c:when test="${board.read_grant eq 'M'}">관리자</c:when>
		</c:choose>
	</td>
	<td>
		<c:choose>
			<c:when test="${board.write_grant eq 'A'}">회원</c:when>
			<c:when test="${board.write_grant eq 'M'}">관리자</c:when>
		</c:choose>
	</td>
	<td>
		<c:choose>
			<c:when test="${board.comments_grant eq 'A'}">회원</c:when>
			<c:when test="${board.comments_grant eq 'M'}">관리자</c:when>
		</c:choose>
	</td>	
	<td>
		<button type="button" id="btnUpdate" class="btn btn-info btn-sm">수정</button>
		<button type="button" id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
	</td>
</tr>
</c:forEach>
</table>


<c:import url="/WEB-INF/views/user/layout/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />