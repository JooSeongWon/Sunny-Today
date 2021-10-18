$(document).ready(function() {
	
	//비밀번호 변경
	$("#btn").click(function(){
		
		$.ajax({
			url:"/mypage/password",
			type:"post",
			data:{
				newPassword:$("#newPassword").val() ,
				password:$("#password").val() ,
				passwordcheck:$("#passwordcheck").val() 
			},
			dataType : 'json' 
			, success:function(data){
					console.log(data)
					if( data == 0 ){
						showModal("오늘도 맑음", "비밀번호가 유효하지 않습니다") 
					} else if( data == 2 ) {
						showModal("오늘도 맑음", "비밀번호가 일치하지 않습니다 :(") 
					} else {
						showModal("오늘도 맑음", "비밀번호가 변경되었습니다 :)")
					}
				},
				error: function() {
				console.log("에러 발생");
			}
			
		})
	});

});