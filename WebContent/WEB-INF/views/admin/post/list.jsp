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
       width:720px;
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
			<input type="date" height="10"> ~
      		<input type="date">
      		<button>오늘</button>
      		<button>어제</button>
      		<button>일주일</button>
      		<button>한달</button>
      		
		</td>

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

<%-- ${postList}  --%>
<%-- ${postList[0].post} --%>
<%-- ${postList[1].post} --%>

<%-- ${postList } --%>
<%-- ${ postList[0].board.title} --%>
<%-- ${postList}  --%>
<%-- ${posrList[0].post.write_date } --%>

<%-- ${allList.post_no } --%>
<%-- ${allList[0]} --%>
<%-- ${allList[0].post_no} --%>
<%-- ${allList[1].post_no} --%>
<%-- ${allList[1].post.post_no} --%>
<%-- ${post } --%>
<%-- ${board } --%>
<%-- ${allList[0].title } --%>

<%-- ${allList.post.title } --%>

<%-- ${allList } --%>
<form action="Admin/post/list" method="post">
<c:forEach items="${allList }" var="item">
<tr>
	<td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td>
	<td><a href="/SunnyToday/admin/post/view?=${item.post.post_no }">${item.post.post_no }</a></td>
	<td>${item.board.title}</td>
	<td>${item.post.title }</td>
	<td>${item.member.nick }</td>
	<td>${item.post.write_date }</td>

	<td>	
		<button type="button" class="btn btn-danger btn-sm btnDelete" data-postNo="${post.post_no}">삭제</button>
	</td>
</tr>
</c:forEach>
</form>
</table>

<c:import url="/WEB-INF/views/admin/post/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />