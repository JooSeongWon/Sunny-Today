<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <style type="text/css">
    
	    div#side_left{
		width:450px;
		height:700px;
		background-color:yellow;
		float:left;
		margin:15px;
		}
		#side_left_box{
		width:95%;
		height:45%;
		background-color:blue;
		float:left;
		margin:10px;
		}
		#mid_content{
		width:400px;
		height:400px;
		background-color:green;
		float:left;
		margin:5px;
		}
		#side_content_box{
		display: inline;
		background-color:white;
		margin:5px;
		}
		#side_rigth{
		width:200px;
		height:400px;
		background-color:red;
		float:left;
		margin:5px;
		}
		#side_rigth_box{
		width:95%;
		height:45%;
		background-color:skyblue;
		margin:5px;
		margin-top:95%;
		}
    
    </style>
    
    <script type="text/javascript">
    
    	document.addEventListener('keydown', function(event) {
			
    		if(event.keyCode === 13 ) {
    			event.preventDefault();
    		}
    		
		}, true);
    
    </script>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>





<form action="<%=request.getContextPath() %>/schedule/write" method="post" id="frm">
<div style="width: 1500px; height: 750px; background-color: #EAEFF8; margin: auto;" >
	
	<div id = "side_left">
	
		<!-- form 값으로 보내는 위도, 경도 값 -->
		<input type="hidden" id="latitude" name="latitude" value="" />
		<input type="hidden" id="longitude" name="longitude" value="" />
		
		<!-- form 값으로 보내는 지역정보 값 -->
		<input type="hidden" id="r1r2" name="r1r2" value="" />
		<input type="text" id="area" name="area" value="" />
		
		<!-- 장소 보여주기  -->
		
		<div id = "side_left_box">
			<div id="map" style="width:100%; height:100%;"></div>
				
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52a25407c42669c765fd37f3cdb8b0bf&libraries=services"></script>
				<script>
				
 					var container = document.getElementById('map');
 					var options = {
 						center: new kakao.maps.LatLng(37.49799093779544, 127.02754223910571),
 						level: 3
 					};
 					
 					var map = new kakao.maps.Map(container, options); // 지도를 생성합니다
 					
 					
 					
 					// 지도를 클릭한 위치에 표출할 마커입니다
 					var marker = new kakao.maps.Marker({ 
 					    // 지도 중심좌표에 마커를 생성합니다 
 					    position: map.getCenter() 
 					}); 
 					
 					// 지도에 마커를 표시합니다
 					marker.setMap(map);
 					
 					

 					

 					// 지도에 클릭 이벤트를 등록합니다
 					// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
 					kakao.maps.event.addListener(map, 'click', function(mouseEvent) { 
 					    
 					    // 클릭한 위도, 경도 정보를 가져옵니다 
 					    var latlng = mouseEvent.latLng; 
 					    
 					    // 마커 위치를 클릭한 위치로 옮깁니다
 					    marker.setPosition(latlng);
 					    
 					    // 보낼 form 값 설정
 					   	document.getElementById("latitude").value = latlng.getLat();
 					   	document.getElementById("longitude").value = latlng.getLng();
						
 					   	var geocoder = new kakao.maps.services.Geocoder();
 					   	
 					 	var callback1 = function(result, status) {
 					 		
 					    	if (status === kakao.maps.services.Status.OK) {

 					        	console.log('지역 명칭 : ' + result[0].address_name);
 					        	
 					      	}
 					    	
 						};
 						
 						var coord = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng());
 						
 						var callback2 = function(result, status) {
 							
 						    if (status === kakao.maps.services.Status.OK) {
 						    	
 						        console.log('그런 너를 마주칠까 ' + result[0].address.address_name + '을 못가');
 						        
 						    }
 						};

 					  	geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback1);
 					  	geocoder.coord2Address(coord.getLng(), coord.getLat(), callback2);
 					   	
 					});
 					
				</script>
				

				
				
		</div>
		
		<div id = "side_left_box">사이드바 왼쪽 아래</div>
	</div>
	
	<div id = "mid_content">
	    <div id = "side_content_box">첫번째 공간</div>
	    <div id = "side_content_box">두번째 공간</div>
	</div>
	
	<div id = "side_rigth">사이드바 오른쪽
	    <div id = "side_rigth_box">사이드바 오른쪽 아래</div>
	</div>

</div>

</form>


<br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
