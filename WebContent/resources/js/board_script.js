$(document).ready(function() {
	
	//파일 preview
	$("#upload").change(function( e ) {
		
		var files = e.target.files;
		
		//이미지만 처리할 수 있도록 적용
		if( !files[0].type.includes("image") ) {
			alert("이미지가 아닙니다")
			
			e.target.value = null;
						
			return false;
		}
		
		var reader = new FileReader();
		
		reader.onload = function( e ) {
			console.log( e )
			console.log( e.target.result )
			
			$("#preview").html(
					$("<img>").attr({
						"src": e.target.result
						, "width" : 300
						, "height" : 200
					}))			
		}
		reader.readAsDataURL( files[0] )		
	})
	
	
	//게시글 작성 클릭	
	$(".btnWrite").click(function() {	
		location.href="/board/write";
	});
	
	//게시글 작성 저장버튼 동작
	$(".btnWriteSubmit").click(function() {	
		$("form").submit();
	});
	
	//게시글 작성 취소버튼 동작
	$(".btnWriteCancel").click(function() {
		history.go(-1);
	});
	
	//게시글 상세보기 신고버튼 동작
	$('#btnReport').click(function() {
//		location.href="board/report";
	});
	
	//게시글 상세보기 수정버튼 동작
	$(".btnUpdate").click(function() {
		console.log("click")
		$(location).attr("href", "/board/update?postno=${detailBoard.post_no }");
	});
	
	//게시글 상세보기 삭제버튼 동작
	$(".btnDelete").click(function() {
		$(location).attr("href", "/board/update?postno=${detailBoard.post_no }");
	});
//		showModal(게시글을 삭제하시겠습니까?, 
//			삭제한 게시글은 복구할 수 없습니다, 
//			$(location).attr("href", "/board/delete?postno=${detailBoard.post_no }), 
//			$(location).attr("href", "/board/detail?postno=${detailBoard.post_no }));
//	);
	
	//게시글 상세보기 뒤로가기버튼 동작
	$("#btnList").click(function() {
		history.go(-1);
	});
	
	//게시글 수정버튼 동작
	$("#btnUpdateOk").click(function() {
		$("form").submit();
	});
	
	//게시글 수정취소버튼 동작
	$("#btnUpdateCancel").click(function() {
		history.go(-1);
	});
	
	
	
	//댓글 js -----------------------------------------------------------
	$("#btnCommentsInsert").click(function() {
		
		//요청 URL
		var url = "/board/comments/insert"
		
		//요청 파라미터
		var data = "&postno=" + $("#postno").val()
			+ "&onlyWriter=" + $("#onlyWriter").val()
			+ "&commentsContent=" + $("#commentsContent").val();
		console.log("data", data)
		
		sendRequest("get", url, data, callback)
		
		return false;
	});
	
	
	
})

function callback() {
	if(httpRequest.readyState == 4) {
		if(httpRequest.status == 200) {
			console.log("정상적인 AJAX 요청/응답 성공")
			
			printData();
		} else {
			console.log("AJAX 요청/응답 실패")
		}
	}
}

function printData() {
	console.log("printData() called")
	comments.innerHTML = httpRequest.responseText
}
