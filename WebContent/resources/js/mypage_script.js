/**
 * 
 */

$(document).ready(function() {
	$("#btn-check").click(function(){
		console.log("#btn-check")
		console.log($("#nick").val())
		
		$.ajax({
			url:"/mypage/check",
			type:"post",
			data:{
				nick:$("#nick").val()
			},
				success:function(data){
					console.log(data);
					
					if(data == 0){
						alert("사용 가능한 닉네임 입니다.");
					} else if(data == 1 ) {
						alert("이미 사용 중인 닉네임 입니다.");
						$("#nick").select();
					} else {
						alert("닉네임을 입력해주세요")
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
		} else {
			$('#btn-private-phone').val('N');
		}
		
		console.log($('#btn-private-phone').val())
		
			$.ajax({
			url:"/mypage/check",
			type:"post",
			data:{
				phone: $('#btn-private-phone').val()
			},
				success:function(data){
					console.log(data);
					
					if(data == 0){
						console.log($('#btn-private-phone').val())
						console.log("공개")
					} else if(data == -10 ) {
						this.checked = false;
						console.log($('#btn-private-phone').val())
						console.log("비공개")
					}
				},
				error: function() {
				console.log("에러 발생");
			}
			
		})

	});
	
	//생일비공개 버튼 동작
	$("#btn-private-birth").click(function(){
		
		if( $(this).prop('checked')){
			console.log("true")
		} else {
			console.log("flase")
		}
	});
	
});