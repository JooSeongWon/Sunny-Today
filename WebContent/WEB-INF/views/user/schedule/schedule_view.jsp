<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="xyz.sunnytoday.dto.Material"%>
<%@ page import="xyz.sunnytoday.dto.Friend"%>
<%@ page import="java.util.List"%>
<% 

List<Material> materialList = (List) request.getAttribute("material"); 
List<Friend> friendList = (List) request.getAttribute("friend"); 

%>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <link href="${cssPath}/schedule_view.css" rel="stylesheet">


    
    <script type="text/javascript">
    	
    	//잘못된 접근시 메인 일정으로 이동
		if(<c:out value='${schedule.latitude}'/> === 0) {
			window.location.href = '<%=request.getContextPath() %>/schedule';
		}
    
    </script>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>


<br><br><br><br><br>


<div id="main">
	
	<div id="side_left">
		
		<div class="top_left_box">
			<!-- 날짜, 장소 보여주기  -->
			<span>일정 날짜 : ${schedule.schedule_date }</span><br>
			<span id=address></span>
		</div>
	
		<div class="side_left_box">
		
			<div id="map" style="width:100%; height:100%;"></div>
				
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52a25407c42669c765fd37f3cdb8b0bf&libraries=services"></script>
				
				<script>
				
 					var container = document.getElementById('map');
 					var options = {
 						center: new kakao.maps.LatLng(<c:out value='${schedule.latitude}'/>, <c:out value='${schedule.longitude}'/>),
 						draggable: false,
 						level: 3
 					};
 					
 					var map = new kakao.maps.Map(container, options); // 지도를 생성합니다
 					
 					var marker = new kakao.maps.Marker({ 
 					    // 지도 중심좌표에 마커를 생성합니다 
 					    position: map.getCenter() 
 					}); 
 					
 					// 지도에 마커를 표시합니다
 					marker.setMap(map);
 					
 					var iwContent = '<div style="padding:5px;">일정 장소<br><a href="https://map.kakao.com/link/map/일정장소,'
 									+ <c:out value='${schedule.latitude}'/> 
 									+ ',' 
 									+ <c:out value='${schedule.longitude}'/> 
 									+ '" style="color:blue" target="_blank">큰지도보기</a> / <a href="https://map.kakao.com/link/to/일정장소,'+ 
 									+ <c:out value='${schedule.latitude}'/>  
 									+ ',' 
 									+ <c:out value='${schedule.longitude}'/> 
 									+ '" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
 				    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

 					// 인포윈도우를 생성합니다
 					var infowindow = new kakao.maps.InfoWindow({
 					    position : iwPosition, 
 					    content : iwContent 
 					});
 				  
 					// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
 					infowindow.open(map, marker);
 					
 					// 좌표 값에 해당하는 구 주소와 도로명 주소 정보 요청
 					var geocoder = new kakao.maps.services.Geocoder();

 					var coord = new kakao.maps.LatLng(<c:out value='${schedule.latitude}'/>, <c:out value='${schedule.longitude}'/> );
 					var callback = function(result, status) {
 					    if (status === kakao.maps.services.Status.OK) {
 					        document.getElementById('address').innerHTML = '일정 장소 : ' + result[0].address.address_name;
 					    }
 					};

 					geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
 					
				</script>
				
		</div>
		
	</div>
	
	<div id="mid_content">
	
		<div class="top_content_box">
			
			${schedule.title }
			
		</div>
	
	    <div id="schedule_content">
	    
	    	${schedule.content }
	    	
	    </div>
	    
	    <div id="schedule_weather">
	    	
		    <div id="weather">
		    	
				<%
				
					if(request.getAttribute("weather") == "맑음") {
						out.print("맑음");
					} else {
						out.print("맑지 않음");
					}
				
				
				%>
		    	
		    </div>
		    	
		    	
		    <div id="clothes">
		    	
		    	<img alt="썸네일" src="/upload/${thumbnail }">
		    	
		    </div>
		    	
	    	<div style="width: 100%; height: 50px;">
	    	
		    	날씨 / 옷<br>
		    	
		    	일정 날씨 ${weather } / 강수 확률 ${rain }% / 온도 ${temperature }
		    	
	    	</div>
	    
	    </div>
	    
	   		<button type="button" id="goList" class="btn" onclick="location.href='<%=request.getContextPath() %>/schedule'">목록으로</button>
	   		
	</div>
	
	<div id="side_rigth">
	
		<div class="top_rigth_box">
			
			<!-- 수정 및 삭제 버튼  -->
			<table style="float: right;">
				<tr>
					<td>
						<form method="get" name="form">
							<input type="hidden" id="hiddenDate" name="scheduleCheckbox" value="${schedule.schedule_date }" />
							<button type="submit" id="updateBtn" class="btn" onclick="form.action='<%=request.getContextPath() %>/schedule/update'">수정</button>
						</form>
					</td>
					
					<td>
						<form method="post" name="form">
							<input type="hidden" id="hiddenDate" name="scheduleCheckbox" value="${schedule.schedule_date }" />
							<button type="submit" id="deleteBtn" class="btn" onclick="form.action='<%=request.getContextPath() %>/schedule/delete'">삭제</button>
						</form>
					</td>
				</tr>			
			</table>
			
		</div>
		
	    	<p style="font-weight: bold;">함께하는 친구</p>
	    	
	    <div class="side_rigth_box">
	    
			<%
			
			for(int i=0; i<friendList.size(); i++) {
				String friend_name = friendList.get(i).getName();
				
				out.print("<p class='addP'><i class='fas fa-user-circle' style='color: var(--color-dark-grey);'></i>&nbsp" + friend_name + "</p>");
				
			}
			
			%>
	    
	    </div>
	    
	    	<p style="font-weight: bold;">준비물</p>
	    	
	    <div class="side_rigth_box">
	    
	    	
			<%
			
			for(int i=0; i<materialList.size(); i++) {
				String material_name = materialList.get(i).getName();
				
				out.print("<p class='addP'>" + material_name + "</p>");
				
			}
			
			%>
		</div>
		
			<div id="schedule_memo">
			
				${schedule.memo }
				
			</div>
		
	</div>

</div>

<br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
