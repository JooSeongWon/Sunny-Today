<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/user/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	//게시판 검색버튼 동작
	$("#btnSearch").click(function() {
		$(location).attr("href", "/board/update?boardno=${viewBoard.boardno }");
	});
	
	//게시판 추가버튼 동작
	$("#btnBoardUp").click(function() {
		$(location).attr("href", "/board/update?boardno=${viewBoard.boardno }");
	});
	
	//체크된 게시판 삭제 동작
	$("#btnCheck").click(function() {
		
	});
	
	//전체 체크박스 동작
	$(".checkAll").click(function() {
		$(".checkBoard").prop("checked", this.checked );
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/board/update?boardno=${viewBoard.boardno }");
	});
	
	//삭제버튼 동작
	$("#btnDelete").click(function() {
		if( confirm("게시판을 삭제하시겠습니까?") ) {
			$(location).attr("href", "/board/delete?boardno=${viewBoard.boardno }");
		}
	});
	
});

</script>

<style>

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
총 게시판 수 :
<br>


<div align="right" >
<button type="button" id="btnBoardUp" >+ 게시판 추가</button>
</div>

<button type="button" id="btnCheck">선택 삭제</button>

<table class="table table-hover">
<tr>
	<th><input type="checkbox" class="checkAll" name="checkAll" value="checkAll" ></th>
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
	<td><input type="checkbox" class="checkBoard" name="checkBoard" ></td>
	<td>${board.board_no }</td> <%--No. --%> 
	<td>${board.title }</td> <%--게시판명(카테고리이름) --%>
	<td>게시글 수</td>
	<td>${board.list_grant }</td> <%--목록보기 권한--%>
	<td>${board.read_grant }</td> <%--게시글 읽기 권한 --%>
	<td>${board.write_grant }</td> <%-- 게시글 쓰기 권한--%>
	<td>${board.comments_grant }</td><%--댓글쓰기 권한--%>
	<td>
		<button type="button" id="btnUpdate" class="btn btn-info btn-sm">수정</button>
		<button type="button" id="btnDelete" class="btn btn-danger btn-sm">삭제</button>
	</td>
</tr>
</c:forEach>
</table>


<c:import url="/WEB-INF/views/user/layout/paging.jsp" />

<c:import url="/WEB-INF/views/user/layout/footer.jsp" />