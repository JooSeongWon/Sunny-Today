/**
 * 
 */

$(document).ready(function() {
	$("#btn-check").click(function(){
		console.log("#btn-check")
	});

	//핸드폰비공개 버튼 동작
	$("#btn-private-phone").click(function(){
		if( $(this).prop('checked')){
			console.log($(this).prop('checked'))
		} else {
			console.log($(this).prop('checked'))
		}
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