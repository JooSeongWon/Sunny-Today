<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
</head>
<body>
<div>
<h1>게시판 관리</h1>
<hr style="border: inset 3px black;">

<div style="background: #BDBDBD;">

<h2>게시판 검색</h2>

<select id="search" name="search">
    <option value="english_name" selected="selected">영문명</option>
</select>

<input type="text" >
<button>검색</button>

</div>

<table>
<tr>
	<th>No.</th>
	<th>게시판명</th>
	<th>게시글 수</th>
	<th>목록보기</th>
	<th>내용보기</th>
	<th>글쓰기</th>
	<th>댓글쓰기</th>
	<th>기능</th>
</tr>

<c:forEach items="${boardList }" var="board">
<tr>
	<td>${board.board_no }</td> <%--No. --%> 
	<td>${board.title }</td> <%--게시판명(카테고리이름) --%>
<%-- 	<td>${board.게시글 수 }</td> --%>
	<td>${board.list_grant }</td> <%--목록보기 권한--%>
	<td>${board.read_grant }</td> <%--게시글 읽기 권한 --%>
	<td>${board.write_grant }</td> <%-- 게시글 쓰기 권한--%>
	<td>${board.comment_grant }</td><%--댓글쓰기 권한--%>
</tr>
</c:forEach>
</table>





</div>
</body>
</html>