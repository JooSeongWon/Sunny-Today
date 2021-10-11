<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/user/layout/header.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
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


					<select id="viewlist" name="viewlist">
	    				<option value="viewlist" selected="selected">전체</option>
					</select>
					
<table class="table table-bordered">
<tr>
	<td class="col-xs-2">카테고리명</td>
	<td><input type="text"></td>
</tr>

<tr>
	<td rowspan="5">권한</td>
</tr>

<tr>
	<td>목록보기
		<select id="viewtlis" name="viewlist">
			<option value="viewlist" selected="selected">전체</option>
		</select>
	</td>
</tr>

<tr>
	<td>내용보기
		<select id="viewlist" name="viewlist">
			<option value="viewlist" selected="selected">전체</option>
		</select>
	</td>
</tr>

<tr>
	<td>글쓰기
		<select id="viewlist" name="viewlist">
			<option value="viewlist" selected="selected">전체</option>
		</select>
	</td>
</tr>

<tr>
	<td>댓글쓰기
		<select id="viewlist" name="viewlist">
			<option value="viewlist" selected="selected">전체</option>
		</select>
	</td>
</tr>

<tr>
	<td>추천 기능</td>
	<td>
		<input type="radio">사용 &nbsp;&nbsp; <input type="radio">미사용
	</td>
</tr>

<tr>
	<td>댓글 허용</td>
	<td>
		<input type="radio">허용 &nbsp;&nbsp; <input type="radio">비허용
	</td>
</tr>

<tr>
	<td>제목 글자수</td>
	<td><input type="text"></td>
</tr>

</table>

<div class="text-center">
	<button type="button" id="btnOk" class="btn btn-info">확인</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
<c:import url="/WEB-INF/views/user/layout/footer.jsp" />