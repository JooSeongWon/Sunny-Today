$(document).ready(function() {
    	
    	//비밀번호 인증
    	$("#btn").click(function(){
    		
    		$.ajax({
    			url:"/mypage/password/check",
    			type:"post",
    			data:{
    				userid : $("#userid").val() ,
    				userpw : $("#userpw").val() 
    			},
    			dataType : 'json' 
    			, success:function(data){
    					console.log(data)
    					if( data == 1 ){
    						showModal("오늘도 맑음", "비밀번호가 일치하지 않습니다 :(") 
    					} else if ( data == 0 ) {
    						showModal("오늘도 맑음", "비밀번호를 입력해주세요 :(") 
						} else {
    						showModal("오늘도 맑음", "약관 페이지로 이동합니다" , function() {
    							var url = "/leaveid"
    							$(location).attr('href',url);
							})
    					}
    				},
    				error: function() {
    				console.log("에러 발생");
    			}
    			
   		})
   	})
});