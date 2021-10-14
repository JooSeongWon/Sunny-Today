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
			$(location).attr("href", `/SunnyToday/admin/post/delete?post_no=\${$(this).attr("data-postNo")}`);
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

#btnSearch {
        width:150px;
        margin:auto;
        display:block;
}
#searchTb {
        width:600px;
        margin:auto;
}
</style>

<h1>게시물 관리</h1>

<hr style="border: inset 3px black;">


<!-- <div style="background: #BDBDBD;"> -->
<table id="searchTb" class="table table-bordered">

<tr>
	<td class="col-xs-3">게시판별</td>
		<td>
			<select id="search" name="search">
    			<option value="category_name" selected="selected">게시판별</option>
			</select>
		</td>
</tr>
<tr>
	<td class="col-xs-3">등록일</td>
		<td>
			시작일~종료일 오늘 어제 1주일 1개월
		</td>
</tr>
<tr>
	<td class="col-xs-3">키워드검색</td>
		<td>
			<select id="search" name="search">
    			<option value="category_name" selected="selected">키워드</option>
			</select>
			<input type="text">
		</td>
</tr>

</table>
<button type="button" id="btnSearch" class="btn btn-default">검색하기</button>
<br>


<div align="right" >
<button type="button" id="btnPostUp" >+ 게시글 등록</button>
</div>

<button type="button" id="btnCheck">선택 삭제</button>

<table class="table table-hover">
<tr>
		<th><input type="checkbox" id= "checkAll" class="checkAll" name="checkAll" value="checkAll" ></th>
	<th>No.</th>
	<th>카테고리</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>삭제</th>
</tr>
	
<form action="Admin/post/list" method="post">
<c:forEach items="${postList }" var="post">
<tr>
	<td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td>
	<td>${post.post_no}</td> 
	<td>${post.category }</td>
	<td>${post.title }</td>
	<td>${post.user_no}</td>
	<td>${post.write_date }</td>
	<td>	
		<button type="button" class="btn btn-danger btn-sm btnDelete" data-postNo="${post.post_no}">삭제</button>
	</td>
</tr>
</c:forEach>
</form>
</table>


<c:import url="/WEB-INF/views/admin/post/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />