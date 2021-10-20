<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/admin/layout/header.jsp"/>
<div id="body" class="container">
	<script type="text/javascript">
		$(document).ready(function() {



			//
			//게시판 검색버튼 동작
			$("#btnSearch").click(function() {

			});

			//게시판 추가버튼 동작
			$("#btnBoardUp").click(function() {
				$(location).attr("href", "/admin/board/write");
			});



			//top 클릭 동작 <tb>한칸올리기
			$("#top").click(function(){
				console.log("hhh");

			});

			//전체 체크박스 동작
			$(".checkAll").click(function() {
				$(".checkBoard").prop("checked", this.checked );
			});

			//수정버튼 동작
			$(".btnUpdate").click(function(e) {
				$(location).attr("href", "/admin/board/update?board_no="+ $(this).attr("data-boardNo"));
			});

//    //수정버튼 동작
//    $(".btn btn-info btn-sm").click(function() {
//       $(location).attr("href", "/admin/board/update?board_no=${viewBoard.board_no }");
//    });


			// 삭제버튼 동작
			$(".btnDelete").click(function(e) {
				if( confirm("게시판을 삭제하시겠습니까?") ) {
					$(location).attr("href", `/admin/board/delete?board_no=\${$(this).attr("data-boardNo")}`);
				}
			});


		});

	</script>

	<style>

		.triangle {
			display: inline-block;
			border: 6px solid transparent;
		}

		.triangle--top {
			border-top-color: black;
		}

		.triangle--bottom{
			border-bottom-color: black;
		}

		/* .checkBoard:checked i { */
		/*  visibility: visible; */
		/* } */

		/*  i {  */
		/*  visibility: hidden;  */
		/*  }  */

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

		<!-- <h2>게시판 검색</h2> -->

		<!-- <select id="search" name="search"> -->
		<!--     <option value="category_name" selected="selected">카테고리명</option> -->
		<!-- </select> -->
		<!-- <input type="text"> -->
		<!-- <button type="button" id="btnSearch" class="btn btn-default">검색</button> -->
		<!-- <br> -->

	</div>
	<h3>총 게시판 수 :<c:out value="${boardCount}" /></h3>
	<br>


	<div align="right" >
		<button type="button" id="btnBoardUp" >+ 게시판 추가</button>
	</div>

	<!-- <button type="button" id="btnCheck">선택 삭제</button> -->

	<table class="table table-hover">
		<tr>
			<!--    <th></th> -->
			<!--    <th><input type="checkbox" id= "checkAll" class="checkAll" name="checkAll" value="checkAll" ></th> -->
			<th>No.</th>
			<th>게시판명</th>
			<!--    <th>게시글 수</th> -->
			<th>목록보기</th>
			<th>내용보기</th>
			<th>글쓰기</th>
			<th>댓글쓰기</th>
			<th>추천기능</th>
			<th>수정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;삭제</th>
		</tr>
		<!--     <div width=0 height=0 style="visibility:hidden"> -->
		<!--     <input type=checkbox name=accept id="accept" value=1 checked> -->
		<!--     <input type=checkbox name=accept_privacy id="accept_privacy" value=1 checked> -->
		<!--     </div> -->

		<%-- <a href="/board/view?boardno=${board.board_no }"> --%>
		<%--       <li><a href="/admin/board/list?curPage=${i }">${i }</a></li> --%>

		<form action="Admin/board/list" method="post">
			<c:forEach items="${boardList }" var="board">
				<tr>
					<!--    <td id="Arthur"><i class="fas fa-arrow-up"></i><i class="fas fa-arrow-down"></i></td> -->
					<!--    <td> -->
					<!--       <div class="triangle triangle--top" id="top"></div> -->
					<!--       <div class="triangle triangle--bottom" id="bottom"></div> -->
					<!--    </td> -->
					<!--    <td><input type="checkbox" id="check" class="checkBoard" name="checkBoard" ></td> -->
					<td>${board.board_no }</td>
					<td>${board.title }</td>
						<%--    <td><c:out value="${titleCount}" /></td> --%>
					<td>
						<c:choose>
							<c:when test="${board.list_grant eq 'N'}">전체</c:when>
							<c:when test="${board.list_grant eq 'M'}">회원</c:when>
							<c:when test="${board.list_grant eq 'A'}">관리자</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${board.read_grant eq 'N'}">전체</c:when>
							<c:when test="${board.read_grant eq 'M'}">회원</c:when>
							<c:when test="${board.read_grant eq 'A'}">관리자</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${board.write_grant eq 'M'}">회원</c:when>
							<c:when test="${board.write_grant eq 'A'}">관리자</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${board.comments_grant eq 'M'}">회원</c:when>
							<c:when test="${board.comments_grant eq 'A'}">관리자</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${board.like eq 'Y'}">허용</c:when>
							<c:when test="${board.like eq 'N'}">비허용</c:when>
						</c:choose>
					</td>

					<td>
						<button type="button" class="btn btn-info btn-sm btnUpdate" data-boardNo="${board.board_no}">수정</button>
						<button type="button" class="btn btn-danger btn-sm btnDelete" data-boardNo="${board.board_no}">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</form>
	</table>


	<c:import url="/WEB-INF/views/admin/board/paging.jsp" />

<c:import url="/WEB-INF/views/admin/layout/footer111.jsp" />