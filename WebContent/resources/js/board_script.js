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
		//뒤로가기는 댓글입력하거나 수정하면 새로고침되서 안먹힐 때가 있어요
		//$(location).attr("href", /board/list); 같은 구문으로 대체해주세요
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
	$("#CommentsUpdateinTable").click(function(){
		
		var text = $(this).text();		
		
		if(text=='수정') {		
		
		var commentsNo = $(this).next().attr('value');
		var comments = $('#commentsContentinTable_'+commentsNo).text();

		$('#commentsContentinTable_'+commentsNo).html("<input type='text' id='updateComments' name='updateComments' value='"+comments+"'>");
		$(this).text('완료');
		$(this).parent().next().children('button').text('취소');
		} else if(text=='완료') {
			var commentsNo = $(this).next().attr('value');
			var newComments = $('#commentsContentinTable_'+commentsNo).children().val();
			$('input[name=newComments]').val(newComments);
			
			$(this).parent().submit();
		}
	
	});
	
	$("#CommentsDeleteinTable").click(function(){
		var text = $(this).text();
		if(text=='삭제'){
			var result = window.confirm("해당 댓글을 삭제하시겠습니까?");
			if(result==true) {
				$(this).parent().submit();
			}
		} else if(text=='취소') {
			location.replace("/board/detail?postno=${detailBoard.post_no }");
		}
		
	})
	
	//신고 js-------------------------------------
	
	$('#sendBtn').click(function(){
		console.log("sendBtn clicked");
		var detail = document.getElementById("detail").value;
		
		if( detail==="" ) {
			showModal("오늘도 맑음", "상세 이유를 작성해주세요!")
		} else {
			$('#report').submit();
		}
	})
	
	$('#cancelBtn').click(function(){
		console.log("cancelBtn");
		history.go(-1);
	})
	
	 $('#ad').on('click', function(){
      if($(this).prop('checked')){
         console.log("checked");
         document.getElementById("detail").value='';
	         document.getElementById("detail").focus();

//          $('#detail').val = "부적절한 홍보 댓글/게시글"
      }
   })
   
   $('#porn').on('click', function(){
      if($(this).prop('checked')){
         console.log("checked");
         document.getElementById("detail").value='';
	         document.getElementById("detail").focus();
//          $('#detail').val = "음란성 또는 청소년에게 부적합한 내용"
      }
   })
   
   $('#defam').on('click', function(){
      if($(this).prop('checked')){
         console.log("checked");
         document.getElementById("detail").value='';
	         document.getElementById("detail").focus();
//          $('#detail').val = "명예훼손/사생황 침해 및 저작권 침해"
      }
   })

	$('#etc').on('click', function(){
	      if($(this).prop('checked')){
	         console.log("checked");
	         document.getElementById("detail").value='';
	         document.getElementById("detail").focus();
	//          $('#detail').val = "기타"
	      }
	   })

})