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
		   	jQuery('#fashion_icon2').css("display", "block");
		} 
	})
	
	// 페이지 이동 -> AJAX로 하면 자연스러울거 같네요
	//-> 첫페이지를 헤더로 잡고 div하나 만들어서 적용해야 하겠네요
	$('#member_list').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/admin/member/list");
	})
	
	$('#member_question').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/admin/member/question");
	})
	
	$('#member_report').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/admin/member/report");
	})
	
	$('#purnish_list').click(function(){
		console.log("member_list clicked");
		$(location).attr("href", "/admin/purnish/list");
	})
	$('#conn_analysis').click(function(){
		console.log("conn_analysis clicked");
		$(location).attr("href", "/admin/conn/analysis");		
	})
	$('#member_statisics').click(function(){
		console.log("member_statisics clicked");
		$(location).attr("href", "/admin/member/statisics");
	})
});