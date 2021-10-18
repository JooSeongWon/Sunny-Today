$(document).ready(function() {
	
	//파일 preview
	$("#upload").change(function( e ) {
		
		var files = e.target.files;
		
		//이미지만 처리할 수 있도록 적용
		if( !files[0].type.includes("image") ) {
			showModal("오늘도 맑음", "이미지를 올려주세요 :)")
			
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
		location.href="/question/insert";
	});
	
	//게시글 작성 저장버튼 동작
	$("#btnWriteSubmit").click(function() {	
		$("form").submit();
	});
	
	//게시글 작성 취소버튼 동작
	$("#btnWriteCancel").click(function() {
		history.go(-1);
	});

	//게시글 상세보기 삭제버튼 동작
	$("#btnDelete").click(function() {
		showModal("오늘도 맑음", "정말로 삭제하시겠어요?", function(){
			return ture;
//			location.href="/question/delete?questionno="+ "questionno";
		}, function(){
			return false;
		})
	});
	
	//게시글 수정취소버튼 동작
	$("#btnUpdateCancel").click(function() {
		history.go(-1);
	});
	
	$(".notMtcLogin").click(function(){
		showModal("오늘도 맑음", "작성자 본인만 볼 수 있는 게시글입니다 :)")
		
		return false;
	})
	
	$(".notLogin").click(function(){
		console.log("clicked")
		showModal("오늘도 맑음", "로그인 후 사용하실 수 있습니다 :)")
		
		return false;
	})
	
	$("#notLoginbtnWrite").click(function(){
		showModal("오늘도 맑음", "로그인 후 사용하실 수 있습니다 :)")
		
		return false;
	})
	
	$('#content').on('keyup', function() {
        $('#text_cnt').html("("+$(this).val().length+" / 150)");
 
        if($(this).val().length > 150) {
            $(this).val($(this).val().substring(0, 150));
            $('#text_cnt').html("(150 / 150)");
        }
    });


})