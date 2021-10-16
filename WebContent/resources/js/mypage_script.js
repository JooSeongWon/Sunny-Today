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
	

	
	
});


	document.addEventListener('DOMContentLoaded', function(){
    //이미지 객체 타입으로 이미지 확장자 밸리데이션
    var validateType = function(img){
        return (['image/jpeg','image/jpg','image/png'].indexOf(img.type) > -1);
    }

    var validateName = function(fname){
        let extensions = ['jpeg','jpg','png'];
        let fparts = fname.split('.');
        let fext = '';
    
        if(fparts.length > 1){
            fext = fparts[fparts.length-1];
        }
    
        let validated = false;
        
        if(fext != ''){
            extensions.forEach(function(ext){
                if(ext == fext){
                    validated = true;
                }
            });
        }
    
        return validated;
    }

    // 파일 선택 필드에 이벤트 리스너 등록
    document.getElementById('fileupload').addEventListener('change', function(e){
        let elem = e.target;
        if(validateType(elem.files[0])){
            let preview = document.querySelector('.thumb');
            preview.src = URL.createObjectURL(elem.files[0]); //파일 객체에서 이미지 데이터 가져옴.
            document.querySelector('.dellink').style.display = 'block'; // 이미지 삭제 링크 표시
            preview.onload = function() {
                URL.revokeObjectURL(preview.src); //URL 객체 해제
            }
        }else{
        console.log('이미지 파일이 아닙니다.');
        }
    });

    document.querySelector('.dellink').addEventListener('click', function(e){
        let dellink = e.target;
        let preview = dellink.previousElementSibling;
        preview.src = ''; // 썸네일 이미지 src 데이터 해제
        document.querySelector('.dellink').style.display = 'none';
    });
 });