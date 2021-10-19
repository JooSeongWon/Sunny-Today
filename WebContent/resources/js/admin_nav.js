'use strict';

$(document).ready(function(){
	$("#admin_set").click(function(){	
		console.log("admin_set clicked");
	 	
		if($("#admin_set_icon1").css("display") == "none"){   
		  	$('#admin_set_icon1').css("display", "inline");
			$('#admin_set_icon2').css("display", "none");
			
			$("#admin_set_group").css("display", "none");

		}else {  
		 	jQuery('#admin_set_icon1').css("display", "none");
		   	jQuery('#admin_set_icon2').css("display", "inline");
			$("#admin_set_group").css("display", "inline");
		} 
	});
	
	$("#member").click(function(){
		console.log("admin_set clicked");
 		
		if($("#member_icon1").css("display") == "none"){   
		  	$('#member_icon1').css("display", "inline");
			$('#member_icon2').css("display", "none");
			$('#memeber_group').css("display", "none");
	
		}else {  
		 	$('#member_icon1').css("display", "none");
			$('#member_icon2').css("display", "inline");			
			$('#memeber_group').css("display", "inline");		
		} 
	});
	
	$("#markting").click(function(){
		console.log("markting clicked");
		
		if($("#markting_icon1").css("display") == "none"){   
		  	jQuery('#markting_icon1').css("display", "inline");
		  	jQuery('#markting_icon2').css("display", "none");
			$('#marketing_group').css("display", "none");
		}else {  
		 	jQuery('#markting_icon1').css("display", "none");
		   	jQuery('#markting_icon2').css("display", "inline");
			$('#marketing_group').css("display", "inline");
		} 
	});
	
	$("#post").click(function(){
		console.log("post clicked");
		
		if($("#post_icon1").css("display") == "none"){   
		  	jQuery('#post_icon1').css("display", "inline");
		  	jQuery('#post_icon2').css("display", "none");
			$('#post_group').css("display", "none");
			
		}else {  
		 	jQuery('#post_icon1').css("display", "none");
		   	jQuery('#post_icon2').css("display", "inline");
			$('#post_group').css("display", "inline");
		} 
	});
	
	$("#calneder").click(function(){
		console.log("calneder clicked");
		
		if($("#calneder_icon1").css("display") == "none"){   
		  	jQuery('#calneder_icon1').css("display", "inline-block");
		  	jQuery('#calneder_icon2').css("display", "none");
						
		}else {
			//이동 트리거 넣어주세요 
		 	jQuery('#calneder_icon1').css("display", "none");
		   	jQuery('#calneder_icon2').css("display", "inline-block");
		} 
	})
	
	$("#fashion").click(function(){
		console.log("fashion clicked");
 		
		if($("#fashion_icon1").css("display") == "none"){   
		  	jQuery('#fashion_icon1').css("display", "inline");
		  	jQuery('#fashion_icon2').css("display", "none");
		}else {  
			//이동 트리거 넣어주세요
		 	jQuery('#fashion_icon1').css("display", "none");
		   	jQuery('#fashion_icon2').css("display", "inline");
		} 
	})
	
	$('#admin_setting').click(function(){
		console.log("admin_setting clicked");
		$(location).attr("href", "/admin/set");
	})
	
	$('#message_event').click(function(){
		console.log("message_evnet clicked");
		$(location).attr("href", "/admin/message/event")
	})
	
	$('#message_send').click(function(){
		console.log("message_send clicked");
		$(location).attr("href", "/admin/message")
	})
	
	$('#member_list').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/admin/member/list");
	})
	
	$('#member_question').click(function(){
		console.log("member_question clicked");
		$(location).attr("href", "/admin/member/question");

	})
	
	$('#member_report').click(function(){
		console.log("member_report clicked");
		$(location).attr("href", "/admin/member/report");
	})
	
	$('#purnish_list').click(function(){
		console.log("purnish_list clicked");
		$(location).attr("href", "/admin/purnish/list");
	})
	
//	$('#conn_analysis').click(function(){
//		console.log("conn_analysis clicked");
//		$(location).attr("href", "/admin/conn/analysis");		
//	})
//	$('#member_statisics').click(function(){
//		console.log("member_statisics clicked");
//		$(location).attr("href", "/admin/member/statisics");
//	})
//	
	$('#board_menage').click(function(){
		console.log("board_menage clicked");
		$(location).attr("href", "/admin/board/list");
	})
	
	$('#total_board_setting').click(function(){
		console.log("total_board_setting clicked");
		$(location).attr("href", "/admin/board/update");
	})
	
	$('#total_post_menage').click(function(){
		console.log("total_post_menage clicked");
		$(location).attr("href", "/admin/post/list")
	})
	
	$('#board_statisics').click(function(){
		console.log("board_statisics clicked");
		$(location).attr("href", "/admin/post/write")
	})	
});

function resonsefunc(){
	//응답 처리 코드
	console.log("responsefunc() called");

	if(httpRequest.readyState == 4){//DONE, 응답 완료
		if(httpRequest.status == 200){// 200 OK, 정상응답
			console.log("AJAX 정상 응답");
			console.log(httpRequest.responseText);
			body.innerHTML = httpRequest.responseText;
		}else{
			console.log("AJAX 요청/응답 에러");
		}
	}
}