$(document).ready(function(){
	$("#admin_set").click(function(){
		console.log("admin_set clicked");
 		console.log("admin_set_icon1", $("#admin_set_icon2").css("display"));
 		console.log("admin_set_icon2", $("#admin_set_icon1").css("display"));
		
		if($("#admin_set_icon1").css("display") == "none"){   
		  	jQuery('#admin_set_icon1').css("display", "inline");
		  	jQuery('#admin_set_icon2').css("display", "none");
		}else {  
		 	jQuery('#admin_set_icon1').css("display", "none");
		   	jQuery('#admin_set_icon2').css("display", "inline");
		} 
	});
	
	$("#member").click(function(){
		console.log("admin_set clicked");
 		console.log("member_icon1", $("#member_icon2").css("display"));
 		console.log("member_icon2", $("#member_icon1").css("display"));
		
		if($("#member_icon1").css("display") == "none"){   
		  	jQuery('#member_icon1').css("display", "inline");
		  	jQuery('#member_icon2').css("display", "none");
		}else {  
		 	jQuery('#member_icon1').css("display", "none");
		   	jQuery('#member_icon2').css("display", "inline");
		} 
	});
	
	$("#markting").click(function(){
		console.log("markting clicked");
 		console.log("markting_icon1", $("#markting_icon2").css("display"));
 		console.log("markting_icon2", $("#markting_icon1").css("display"));
		
		if($("#markting_icon1").css("display") == "none"){   
		  	jQuery('#markting_icon1').css("display", "inline");
		  	jQuery('#markting_icon2').css("display", "none");
		}else {  
		 	jQuery('#markting_icon1').css("display", "none");
		   	jQuery('#markting_icon2').css("display", "inline");
		} 
	});
	
	$("#post").click(function(){
		console.log("post clicked");
 		console.log("post_icon1", $("#post_icon2").css("display"));
 		console.log("post_icon2", $("#post_icon1").css("display"));
		
		if($("#post_icon1").css("display") == "none"){   
		  	jQuery('#post_icon1').css("display", "inline");
		  	jQuery('#post_icon2').css("display", "none");
		}else {  
		 	jQuery('#post_icon1').css("display", "none");
		   	jQuery('#post_icon2').css("display", "inline");
		} 
	});
	
	$("#calneder").click(function(){
		console.log("calneder clicked");
 		console.log("calneder_icon1", $("#calneder_icon2").css("display"));
 		console.log("calneder_icon2", $("#calneder_icon1").css("display"));
		
		if($("#calneder_icon1").css("display") == "none"){   
		  	jQuery('#calneder_icon1').css("display", "inline");
		  	jQuery('calneder_icon2').css("display", "none");
		}else {  
		 	jQuery('#calneder_icon1').css("display", "none");
		   	jQuery('#calneder_icon2').css("display", "inline");
		} 
	})
	
	$("#fashion").click(function(){
		console.log("fashion clicked");
 		console.log("fashion_icon1", $("#fashion_icon2").css("display"));
 		console.log("fashion_icon2", $("#fashion_icon1").css("display"));
		
		if($("#fashion_icon1").css("display") == "none"){   
		  	jQuery('#fashion_icon1').css("display", "inline");
		  	jQuery('#fashion_icon2').css("display", "none");
		}else {  
		 	jQuery('#fashion_icon1').css("display", "none");
		   	jQuery('#fashion_icon2').css("display", "inline");
		} 
	})
});
