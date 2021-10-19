<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
<script type="text/javascript">
$(document).ready(function() {
	
	//검색버튼 동작
	$("#searchBtn").click(function(){
		$("#search").submit()
	});
	
	//게시물 추가버튼 동작
	$("#btnPostUp").click(function() {
		$(location).attr("href", "/admin/post/write");
	});
	
	
// 	//전체 체크박스 동작
// 	$(".checkAll").click(function() {
// 		$(".checkBoard").prop("checked", this.checked );
// 	});

	// 삭제버튼 동작
// 	$(".btnDelete").click(function(e) {
// 		if( confirm("게시판을 삭제하시겠습니까?") ) {
// 			$(location).attr("href", `/admin/post/delete?post_no=\${$(this).attr("data-postNo")}`);
// 		}
// 	});
	
	$("button[name=btnDelete]").click(function() {
		if( confirm("게시판을 삭제하시겠습니까?") ) {
			$(location).attr("href", "/admin/post/delete?post_no="+$(this).attr('value'));
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

#searchBtn {
        width:150px;
        margin:auto;
        display:block;
}
#searchTb {
       width:720px;
        margin:auto;
}
</style>

<h1>게시물 관리</h1>

<hr style="border: inset 3px black;">


<!-- <div style="background: #BDBDBD;"> -->
<table id="searchTb" class="table table-bordered">
<form action="<%=request.getContextPath() %>/admin/post/list" id="search" method="get" class="form-group">
<!-- <tr> -->
<!-- 	<td class="col-xs-3">게시판별</td> -->
<!-- 		<td> -->
<!-- 			<select id="category" name="select"> -->
<!--     			<option value="daily_clothes" >일상룩</option> -->
<!--     			<option value="buying" >지름 게시판</option> -->
<!--     			<option value="sharingInfo" >정보공유</option> -->
<!--     			<option value="qna" >질문 응답</option> -->
<!-- 			</select> -->
<!-- 		</td> -->
<!-- </tr> -->
<!-- <tr> -->
<!-- 	<td class="col-xs-3">등록일</td> -->
<!-- 		<td> -->
<!-- 			<input type="date" name="write_date"> ~ -->
<!--       		<input type="date"> -->
<!--       		<button>오늘</button> -->
<!--       		<button>어제</button> -->
<!--       		<button>일주일</button> -->
<!--       		<button>한달</button>	 -->
      		
<!-- 		</td> -->

<!-- </td> -->
<!-- </tr> -->

<tr>
	<td class="col-xs-3">키워드검색</td>
		<td>
			<select id="keword" name="keword">
    			<option value="title" >제목</option>
    			<option value="nick" >닉네임</option>
			</select>
			<input type="text" name="search">
		</td>
</tr>
</table>
</form>
<button type="button" id="searchBtn" class="btn btn-default">검색하기</button>
<br>


<div align="right" >
<!-- <button type="button" id="btnPostUp" >+ 게시글 등록</button> -->
</div>

<!-- <button type="button" id="btnCheck">선택 삭제</button> -->

<table class="table table-hover">
<tr>
<!-- 		<th><input type="checkbox" id= "checkAll" class="checkAll" name="checkAll" value="checkAll" ></th> -->
	<th>No.</th>
	<th>카테고리</th>
	<th>제목</th>
	<th>닉네임</th>
	<th>작성일</th>
	<th>삭제</th>
</tr>

<form action="Admin/post/view" method="get">
<c:forEach items="${list }" var="item">
<input type="hidden" name="postno" id="postno" value="${item.post.post_no }" />
<tr>
<!-- 	<td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td> -->
	<td><a href="/admin/post/view?post_no=${item.post.post_no }">${item.post.post_no }</a></td>
	<td>${item.board.title}</td>
	<td>${item.post.title }</td>
	<td>${item.member.nick }</td>
	<td>${item.post.write_date }</td>

	<td>	
<%-- 		<button type="button" class="btn btn-danger btn-sm btnDelete" data-postNo="${item.post.post_no}">삭제</button> --%>
		<button type="button" class="btn btn-danger btn-sm btnDelete" name="btnDelete" value="${item.post.post_no}">삭제</button>
	</td>
</tr>
</c:forEach>
</form>
</table>

<c:import url="/WEB-INF/views/admin/post/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />