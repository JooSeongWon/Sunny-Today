'use strict';

$(document).ready(function(){
	$("#admin_set").click(function(){	
		console.log("admin_set clicked");
	 	console.log("-----------------------------------------------------")	
		console.log("admin_set_icon1", $("#admin_set_icon2").css("display"));
 		console.log("admin_set_icon2", $("#admin_set_icon1").css("display"));
		
		if($("#admin_set_icon1").css("display") == "none"){   
		  	$('#admin_set_icon1').css("display", "inline");
			$('#admin_set_icon2').css("display", "none");
			
			$('#admin_setting').css("display", "none");
			$('#message_enroll').css("display", "none");
			$('#message_send').css("display", "none");
	
		}else {  
		 	jQuery('#admin_set_icon1').css("display", "none");
		   	jQuery('#admin_set_icon2').css("display", "inline");

			$('#admin_setting').css("display", "inline");
			$('#message_enroll').css("display", "inline");
			$('#message_send').css("display", "inline");
		} 
	});
	
	$("#member").click(function(){
		console.log("admin_set clicked");
 		console.log("-----------------------------------------------------")	
		console.log("member_icon1", $("#member_icon2").css("display"));
 		console.log("member_icon2", $("#member_icon1").css("display"));
		
		if($("#member_icon1").css("display") == "none"){   
		  	$('#member_icon1').css("display", "inline");
			$('#member_icon2').css("display", "none");
			
			$('#member_list').css("display", "none");
			$('#member_question').css("display", "none");
			$('#member_report').css("display", "none");
			$('#purnish_list').css("display", "none");
		  	
		}else {  
		 	$('#member_icon1').css("display", "none");
			$('#member_icon2').css("display", "inline");
			
			$('#member_list').css("display", "inline");
			$('#member_question').css("display", "inline");
			$('#member_report').css("display", "inline");
			$('#purnish_list').css("display", "inline");
			
		} 
	});
	
	$("#markting").click(function(){
		console.log("markting clicked");
		console.log("-----------------------------------------------------")
 		console.log("markting_icon1", $("#markting_icon2").css("display"));
 		console.log("markting_icon2", $("#markting_icon1").css("display"));
		
		if($("#markting_icon1").css("display") == "none"){   
		  	jQuery('#markting_icon1').css("display", "inline");
		  	jQuery('#markting_icon2').css("display", "none");
	
			$('#conn_analysis').css("display", "none");
			$('#member_statisics').css("display", "none");
		}else {  
		 	jQuery('#markting_icon1').css("display", "none");
		   	jQuery('#markting_icon2').css("display", "inline");
	
			$('#conn_analysis').css("display", "inline");
			$('#member_statisics').css("display", "inline");
		} 
	});
	
	$("#post").click(function(){
		console.log("post clicked");
		console.log("-----------------------------------------------------")
 		console.log("post_icon1", $("#post_icon2").css("display"));
 		console.log("post_icon2", $("#post_icon1").css("display"));
		
		if($("#post_icon1").css("display") == "none"){   
		  	jQuery('#post_icon1').css("display", "inline");
		  	jQuery('#post_icon2').css("display", "none");
			
			$('#board_menage').css("display", "none");
			$('#total_board_setting').css("display", "none");
			$('#total_post_menage').css("display", "none");
			$('#total_comments_menage').css("display", "none");
			$('#board_statisics').css("display", "none");
			
		}else {  
		 	jQuery('#post_icon1').css("display", "none");
		   	jQuery('#post_icon2').css("display", "inline");

			$('#board_menage').css("display", "inline");
			$('#total_board_setting').css("display", "inline");
			$('#total_post_menage').css("display", "inline");
			$('#total_comments_menage').css("display", "inline");
			$('#board_statisics').css("display", "inline");
		} 
	});
	
	$("#calneder").click(function(){
		console.log("calneder clicked");
		console.log("-----------------------------------------------------")
 		console.log("calneder_icon1", $("#calneder_icon2").css("display"));
 		console.log("calneder_icon2", $("#calneder_icon1").css("display"));
		
		if($("#calneder_icon1").css("display") == "none"){   
		  	jQuery('#calneder_icon1').css("display", "inline-block");
		  	jQuery('#calneder_icon2').css("display", "none");
	
			$('#board_menage').css("display", "none");
						
		}else {
			//이동 트리거  
		 	jQuery('#calneder_icon1').css("display", "none");
		   	jQuery('#calneder_icon2').css("display", "inline-block");
		} 
	})
	
	$("#fashion").click(function(){
		console.log("-----------------------------------------------------")
		console.log("fashion clicked");
 		console.log("fashion_icon1", $("#fashion_icon2").css("display"));
 		console.log("fashion_icon2", $("#fashion_icon1").css("display"));
		
		if($("#fashion_icon1").css("display") == "none"){   
		  	jQuery('#fashion_icon1').css("display", "inline");
		  	jQuery('#fashion_icon2').css("display", "none");
		}else {  
			//이동 트리거
		 	jQuery('#fashion_icon1').css("display", "none");
		   	jQuery('#fashion_icon2').css("display", "inline");
		} 
	})
	
	// 페이지 이동 -> AJAX로 하면 자연스러울거 같네요
	//-> 첫페이지를 헤더로 잡고 div하나 만들어서 적용해야 하겠네요
	$('#member_list').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/member/list");
	})
	
	$('#member_question').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/member/question");
	})
	
	$('#member_report').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/member/report");
	})
	
	$('#purnish_list').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/purnish/list");
	})
	$('#conn_analysis').click(function(){
		console.log("conn_analysis clicked");
		$(location).attr("href", "/conn/analysis");		
	})
	$('#member_statisics').click(function(){
		console.log("member_statisics clicked");
		$(location).attr("href", "/member/statisics");
	})
});