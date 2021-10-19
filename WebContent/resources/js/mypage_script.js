$(document).ready(function() {
	
	//중복확인 버튼 동작
	$("#btn-check").click(function(){
		console.log("#btn-check")
		console.log($("#nick").val())
		
		$.ajax({
			url:"/mypage/check",
			type:"post",
			data:{
				nick:$("#nick").val()
			},
			dataType : 'json' 
			, success:function(data){
					console.log(data)
					if( data == 0 ){
						showModal("오늘도 맑음", "사용가능한 닉네임 입니다:)") 
					} else if( data == 1 ) {
						showModal("오늘도 맑음", "이미 사용 중인 닉네임 입니다 :(") 
						$("#nick").select();
					}
				},
				error: function() {
				console.log("에러 발생");
			}
			
		})
	});

	//핸드폰비공개 버튼 동작
	$("#btn-private-phone").click(function(){
		if($(this).prop('checked')){
			$('#btn-private-phone').val('Y');
			 showModal("오늘도 맑음", "번호가 공개처리 되었습니다 :)") 
		} else {
			 showModal("오늘도 맑음", "번호가 비공개처리 되었습니다 :)") 
			$('#btn-private-phone').val('N');
		}
		
		console.log($('#btn-private-phone').val())
		
			$.ajax({
			url:"/mypage/check",
			type:"post",
			data:{
				phone: $('#btn-private-phone').val()
			},
			dataType : 'json' ,
				success:function(data){
				},
				error: function() {
				console.log("에러 발생");
			}
			
		})

	});
	
	//생일비공개 버튼 동작
	$("#btn-private-birth").click(function(){
		if($(this).prop('checked')){
			$('#btn-private-birth').val('Y');
			showModal("오늘도 맑음", "생일이 공개처리 되었습니다 :)") 
		} else {
			$('#btn-private-birth').val('N');
			showModal("오늘도 맑음", "생일이 비공개처리 되었습니다 :)")
		}
		
		console.log($('#btn-private-birth').val())
		
			$.ajax({
			url:"/mypage/check",
			type:"post",
			data:{
				birth: $('#btn-private-birth').val()
			},
			dataType : 'json' ,
				success:function(data){
				},
				error: function() {
				console.log("에러 발생");
			}
		})
	});
	
	$("#btnsubmit").click(function(){
		$("#form").submit()
	
	});


	


    $('#fileupload').change(function(e){
	
        var elem = e.target.files;

        if(!elem[0].type.includes("image") ) {
	        showModal("오늘도 맑음", "이미지 파일이 아닙니다 :(")
        }else{
            var preview = document.querySelector('.thumb');
            preview.src = URL.createObjectURL(elem[0]); //파일 객체에서 이미지 데이터 가져옴.
        }
    });



});