<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
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

<h1>게시판 수정</h1>
<hr style="border: inset 3px black;">
					
<form action="<%=request.getContextPath()%>/admin/board/update" method="post">
<input type="hidden" name="board_no" value="${updateBoard.board_no }" />
<table class="table table-bordered">
<tr>
	<td class="col-xs-2">카테고리명</td><td><input type="text" name="title" value="${updateBoard.title}"></td>
</tr>

<tr>
	<td rowspan="5">권한</td>
</tr>
<tr>
	<td>목록보기
		<select id="viewList" name="list_grant">
		<option value="N" 
			<c:if test="${updateBoard.list_grant eq 'N'}">
				<option value="N" selected>전체</option>
			</c:if>
		>전체
		</option>
		<option value="M" 
			<c:if test="${updateBoard.list_grant eq 'M'}">
				<option value="M" selected>회원</option>
			</c:if>
		>회원
		</option>
		<option value="A" 
			<c:if test="${updateBoard.list_grant eq 'A'}">
				<option value="A" selected>관리자</option>
			</c:if>
		>관리자
		</option>		
		</select>
	</td>
</tr>

<tr>
	<td>내용보기
		<select id="viewContent" name="read_grant">
		<option value="N" 
			<c:if test="${updateBoard.read_grant eq 'N'}">
				<option value="N" selected>전체</option>
			</c:if>
		>전체
		</option>
		<option value="M" 
			<c:if test="${updateBoard.read_grant eq 'M'}">
				<option value="M" selected>회원</option>
			</c:if>
		>회원
		</option>
		<option value="A" 
			<c:if test="${updateBoard.read_grant eq 'A'}">
				<option value="A" selected>관리자</option>
			</c:if>
		>관리자
		</option>		
<%-- 			<option value="${updateBoard.read_grant}" >전체</option> --%>
<%-- 			<option value="${updateBoard.read_grant}" >회원</option> --%>
<%-- 			<option value="${updateBoard.read_grant}" >관리자</option> --%>
		</select>
	</td>
</tr>

<tr>
	<td>글쓰기
		<select id="ContentWrite" name="write_grant">
		<option value="M" 
			<c:if test="${updateBoard.write_grant eq 'M'}">
				<option value="M" selected>회원</option>
			</c:if>
		>회원
		</option>
		<option value="A" 
			<c:if test="${updateBoard.write_grant eq 'A'}">
				<option value="A" selected>관리자</option>
			</c:if>
		>관리자
		</option>			
<%-- 			<option value="${updateBoard.write_grant}" >회원</option> --%>
<%-- 			<option value="${updateBoard.write_grant}" >관리자</option> --%>
		</select>
	</td>
</tr>

<tr>
	<td>댓글쓰기
		<select id="commentWrite" name="comments_grant">
		<option value="M" 
			<c:if test="${updateBoard.comments_grant eq 'M'}">
				<option value="M" selected>회원</option>
			</c:if>
		>회원
		</option>
		<option value="A" 
			<c:if test="${updateBoard.comments_grant eq 'A'}">
				<option value="A" selected>관리자</option>
			</c:if>
		>관리자
		</option>				
<%-- 			<option value="${updateBoard.comments_grant}" >회원</option> --%>
<%-- 			<option value="${updateBoard.comments_grant}" >관리자</option> --%>
		</select>
	</td>
</tr>

<tr>
	<td>추천 기능</td>
	<td>
<!-- 	[].forEach.call(document.querySelectorAll('[name='like']') , e => if(e.value == 'Y') e.checked = true) -->
<c:choose>
<c:when test="${updateBoard.like eq 'Y'}">
		<input type="radio" name="like" value="Y" checked>사용
		<input type="radio" name="like" value="N">미사용
</c:when>
<c:otherwise>
		<input type="radio" name="like" value="Y">사용
		<input type="radio" name="like" value="N" checked>미사용
</c:otherwise>
</c:choose>
	</td>
</tr>
<tr>
	<td>게시물 보기</td>
	<td>
	<c:choose>
<c:when test="${updateBoard.show eq 'Y'}">
		<input type="radio" name="show" value="Y" checked>사용
		<input type="radio" name="show" value="N">미사용
</c:when>
<c:otherwise>
		<input type="radio" name="show" value="Y">사용
		<input type="radio" name="show" value="N" checked>미사용
</c:otherwise>
</c:choose>
<%-- 		<input type="radio" name="show" value="${updateBoard.show}">사용 --%>
<%-- 		<input type="radio" name="show" value="${updateBoard.show}">미사용 --%>
	</td>
</tr>

<tr>
	<td>제목 글자수</td>
	<td><input type="number" name="title_length" maxlength="20" value="${updateBoard.title_length}"></td>
</tr>


</table>

<div class="text-center">
	<button type="button" id="btnOk" class="btn btn-info">확인</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>

</form>

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />