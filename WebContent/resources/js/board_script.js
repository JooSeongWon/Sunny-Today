$(document).ready(function() {
	
	//파일 preview
	$("#upload").change(function( e ) {
		
		var files = e.target.files;
		
		//이미지만 처리할 수 있도록 적용
		if( !files[0].type.includes("image") ) {
			alert("이미지가 아닙니다")
			
			//선택한 파일 해제하기
			e.target.value = null;
			
			//이벤트 처리 중단시키기
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
		console.log("click")
		location.href="/board/write";
	});
	
	//게시글 저장버튼 동작
	$(".btnWriteSubmit").click(function() {	
		$("form").submit();
	});
	
	//게시글 취소버튼 동작
	$(".btnWriteCancel").click(function() {
		history.go(-1);
	});
	
		
})


