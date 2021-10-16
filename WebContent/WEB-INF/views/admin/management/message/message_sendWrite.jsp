<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>

<<<<<<< HEAD
<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$("#search").submit()
	});
	
	$('.check-all').click(function(){
		$('.check').prop('checked', this.checked);
	})
	
	$("#send").click(function(){
		$("#Alldel").submit()
	});
	
});
</script>

=======
<!-- 스마트에디터2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="UTF-8"></script>
<!-- ajax -->
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영한다
function submitContents(elClickedObj) {
	
	//에디터의 내용을 #content에 반영해준다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		//<form>태그의 submit을 수행한다
		elClickedObj.form.submit();
	} catch (e) {}
}
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#btnSend").click(function(){
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents( $("#btnSend") )
		
		$("form").submit();
	});
	
	$("#event").change(function() {
	    var url = "/admin/message/event/ajax"
	    $.ajax({
	    	type: "post"
	    	, url: "/admin/message/event/ajax"
	    	, data: {
	    		no: $(this).val()
	    	}
	    ,  dataType:"html" //응답 데이터 형식
	    ,  success: function( res ) {
 			$("#resultset").remove()
	    	$("#result").html( res )
	    }
		, error: function() {
			console.log("에러 발생")
		}
	    	
	    })
		
	}); //$("#event").change
	
}); // $(document)
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
<title>쪽지 발송</title>

<div id="body" class="container">

<h1>쪽지 발송</h1>
<hr>

<div class="col-md-11">
<<<<<<< HEAD


<div>
	<form action="<%=request.getContextPath() %>/admin/message" id="search" method="get">
		<label for="search">&nbsp;회원 검색&nbsp;</label>
		<select name="select">
			<option value="id">아이디</option>
			<option value="nick">닉네임</option>
			<option value="email">이메일</option>
		</select>
		<input class="search-query" type="text" name="search" placeholder="search"/>
	<button class="btn btn-default" id="searchBtn" type="button">검색</button>
	</form>
</div>

<form action="<%=request.getContextPath() %>/admin/set" id="allsearch" method="get" class="form-group">
<table class="table">
<tr>
<th colspan="3"><span>&nbsp; 총 회원수 : ${paging.totalCount }</span></th>
<th colspan="3" class="text-right">
	<input type="hidden" name="search" value=""/>
	<button class="btn btn-default" id="viewBtn" type="button">쪽지발송</button>
</th>
</tr>
<tr>
	<th><input type="checkbox" name="check-all" class="check-all"></th>
	<th>No.</th>
=======
<form action="/admin/message/write" method="post">


<c:choose>
<c:when test="${empty list }">
<div>전체발송 :)</div>
<div>총 회원수 :${totalcount } </div>
</c:when>
<c:when test="${not empty list }">
<div>선택발송 :)</div>
<div>선택 회원수 : ${totalcount }</div>
<table class="table" >
<tr>
	<th>NO.</th>
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
	<th>아이디</th>
	<th>닉네임</th>
	<th>이메일</th>
	<th>가입일</th>
</tr>
<c:forEach items="${list }" var="member">
<<<<<<< HEAD
<tr>
	<td><input type="checkbox" name="no[]" value="${member.userno }" class="check"></td>
=======
	<input type="hidden" name="no[]" value="${member.userno }">
<tr>
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
	<td>${member.userno }</td>
	<td>${member.userid }</td>
	<td>${member.nick }</td>
	<td>${member.email }</td>
	<td>${member.create_date }</td>
</tr>
</c:forEach>
</table>
<<<<<<< HEAD
</form>

<c:import url="/WEB-INF/views/admin/layout/message_paging.jsp"/>
</div>

=======
</c:when>
</c:choose>

<table class="table">
<tr>
<td class="active">분류명</td>
<td>
<select name="event" class="form-control" id="event" >
	<option value="" disabled selected hidden>기본값입니다.</option>
<c:forEach items="${event }" var="e">
    <option value="${e.event_no }">${e.name }</option>
</c:forEach>
</select>
</td>
<td class="active">이벤트제목</td>
<td>
	<div id="result">
	<select id="resultset" name="message" class="form-control">
		<option value="" disabled selected hidden>기본값입니다.</option>
		<c:forEach items="${elist }" var="message">
    	<option value="${message.message_e_no }">${message.title }</option>
		</c:forEach>
	</select>
	</div>
</td>
</tr>
<tr id="messagetitle">
<td class="active">쪽지 제목</td>
<td colspan="4"><input type="text" name="title" class="form-control"></td>
</tr>
<tr>
<td class="active" colspan="4"></td>
</tr>
<tr id="messagecontent">
<td class="active">본문</td>
<td colspan="4"><textarea id="content" name="content"></textarea></td>
</tr>
</table>
</form>

	<div class="text-center">	
		<button type="button" id="btnSend" class="btn btn-info">발송</button>
	</div>

</div>

<!-- <textarea>태그에 스마트에디터2 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 	oAppRef: oEditors,
 	elPlaceHolder: "content",
 	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
 	fCreator: "createSEditor2"
});
</script>

>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
</body>
</html>