<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select id="resultset" name="message" class="form-control">
		<option value="" disabled selected hidden>기본값입니다.</option>
		<c:forEach items="${elist }" var="message">
    	<option value="${message.message_e_no }">${message.title }</option>
		</c:forEach>
	</select>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#resultset").change(function() {
	var value = $(this).val()
	console.log(value)
	    $.ajax({
	    	type: "post"
	    	, url: "/admin/message/ajax"
	    	, data: {
	    		no: $(this).val()
	    	}
	    ,  dataType:"html" //응답 데이터 형식
	    ,  success: function( res ) {
 			$("#messagetitle").children('td').remove();
	    	$("#messagetitle").html( res )
	    }
		, error: function() {
			console.log("에러 발생")
		}
    	})
	   
		$.ajax({
	    	type: "get"
	    	, url: "/admin/message/ajax"
	    	, data: {
	    		no: $(this).val()
	    	}
	    ,  dataType:"html" //응답 데이터 형식
	    ,  success: function( res ) {
 			$("#messagecontent").children('td').remove();
	    	$("#messagecontent").html( res )
	    }
		, error: function() {
			console.log("에러 발생")
		}
	    		
		})
	})
})
</script>