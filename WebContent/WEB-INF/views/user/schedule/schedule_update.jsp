<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    
    <link href="${cssPath}/schedule_write.css" rel="stylesheet">
    
    <script type="text/javascript">
    
	//잘못된 접근시 메인 일정으로 이동
	if(<c:out value='${schedule.latitude}'/> === 0) {
		window.location.href = '<%=request.getContextPath() %>/schedule';
	}
    
	$(document).ready(function() {
		
		document.getElementById('submitBtn').onclick = function() {
			
	    	var check = document.getElementById('latitude').value;
	    	
		    if(check == "0") {
		    	window.alert('지도를 클릭해주세요!');
		    } else {
		    	document.getElementById('submit').click();
		    }
			
		}
	})
	
	$(document).ready(function() {
    	document.getElementById('area').addEventListener('keydown', function(event) {
    		
    		if(event.keyCode === 13 ) {
    			event.preventDefault();
    			
				// 장소 검색 객체를 생성합니다
				var ps = new kakao.maps.services.Places(); 

				// 키워드로 장소를 검색합니다
				ps.keywordSearch(document.getElementById('area').value, placesSearchCB); 

				// 키워드 검색 완료 시 호출되는 콜백함수 입니다
				function placesSearchCB (data, status, pagination) {
					if (status === kakao.maps.services.Status.OK) {

						// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
						// LatLngBounds 객체에 좌표를 추가합니다
						var bounds = new kakao.maps.LatLngBounds();

						for (var i=0; i<data.length; i++) {
							bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
						}       

						// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
						map.setBounds(bounds);
					
					}
				}
    		}
		}, true);
	})
	
	$(document).ready (function() {
		$('input[target]').keydown(function() {
			if(event.keyCode === 13) {
				event.preventDefault();
			}
		})
	})
	
	$(document).ready (function() {
			
		var count = <%=friendList.size() %>;
			
		$('.btnAdd').click (function() {
				
			if(count < 6) {
				$('.addInput').append (
					'<p class="addP"><button type="button" class="btnRemove">-</button> \
					<input type="text" name="friend" target="target" size="7px" maxlength="5" placeholder="친구" required="required" /><br></p>'
				); //input 추가
				
				$('input[target]').keydown(function() {
					if(event.keyCode === 13) {
						event.preventDefault();
					}
				})
				
				count++;
			} else {
				alert("친구 목록은 6명을 초과할 수 없습니다!");
			}
			
			$('.btnRemove').off('click').on('click', function() {
				
											// 여기서 this는 '.btnRemove'
				$(this).parent().remove();	// <p> 삭제
				$(this).next().remove();	// <input> 삭제
				$(this).next().remove();	// <br>
				$(this).remove();			// 버튼삭제
					
				count--;
			})
		})
	})
	
	$(document).ready (function() {
			
		var count2 = <%=materialList.size() %>;
			
		$('.btnAdd2').click (function() {
				
			if(count2 < 6) {
				$('.addInput2').append (
					'<p class="addP"><button type="button" class="btnRemove2">-</button> \
					<input type="text" name="material" target="target" size="7px" maxlength="5" placeholder="준비물" required="required" /><br><p>'
				); //input 추가
				
				$('input[target]').keydown(function() {
					if(event.keyCode === 13) {
						event.preventDefault();
					}
				})
					
				count2++;
			} else {
				alert("준비물은 6개를 초과할 수 없습니다!");
			}
			
			$('.btnRemove2').off('click').on('click', function() {
				
											// 여기서 this는 '.btnRemove'
				$(this).parent().remove();	// <p> 삭제
				$(this).next().remove();	// <input> 삭제
				$(this).next().remove();	// <br>
				$(this).remove();			// 버튼삭제
					
				count2--;
			})
		})
	})
	
    </script>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>


<br><br><br><br><br>


<form action="<%=request.getContextPath() %>/schedule/update" method="post" id="frm">

<div id="main" >
	
	<div id="side_left">
		
		<!-- form 값으로 보내는 scheduleCheckbox 값  -->
		<input type="hidden" id="scheduleCheckbox" name="scheduleCheckbox" value="${schedule.schedule_date }" />
	
		<!-- form 값으로 보내는 위도, 경도 값 -->
		<input type="hidden" id="latitude" name="latitude" value="0" />
		<input type="hidden" id="longitude" name="longitude" value="" />
		
		<!-- form 값으로 보내는 지역정보 값 -->
		<input type="hidden" id="r1r2" name="r1r2" value="" />
		
		<!-- 장소 보여주기  -->
		<input type="text" id="area" name="area" value="" placeholder="일정 장소를 검색 후 해당 지역을 클릭하세요" />
		
		<div class="side_left_box">
		
			<div id="map" style="width:100%; height:100%;"></div>
				
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52a25407c42669c765fd37f3cdb8b0bf&libraries=services"></script>
				
				<script>
				
 					var container = document.getElementById('map');
 					var options = {
 						center: new kakao.maps.LatLng(<c:out value='${schedule.latitude}'/>, <c:out value='${schedule.longitude}'/>),
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
 					   	document.getElementById('latitude').value = latlng.getLat();
 					   	document.getElementById('longitude').value = latlng.getLng();
						
 					   	var geocoder = new kakao.maps.services.Geocoder();
 					   	
 					 	var callback1 = function(result, status) {
 					 		
 					    	if (status === kakao.maps.services.Status.OK) {

 					        	document.getElementById('r1r2').value = result[0].address_name;
 					        	
 					      	}
 					    	
 						};
 						
 						var coord = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng());
 						
 						var callback2 = function(result, status) {
 							
 						    if (status === kakao.maps.services.Status.OK) {
 						    	
 						        document.getElementById('area').value = result[0].address.address_name;
 						        
 						    }
 						};

 					  	geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback1);
 					  	geocoder.coord2Address(coord.getLng(), coord.getLat(), callback2);
 					   	
 					});
 					
				</script>
				
		</div>
		
	</div>
	
	<div id="mid_content">
	
	    <div id="mid_content_box">
	    
	    	<input type="text" id="schedule_date" name="schedule_date" value="${schedule.schedule_date }" readonly="readonly"/><br>
	    	<input type="text" id="schedule_title" name="schedule_title" value="${schedule.title }" target="target" maxlength="15" placeholder="일정 이름을 입력해주세요" required="required" /><br>
	    	<textarea id="schedule_content" name="schedule_content" name="schedule_content" placeholder="일정 내용을 입력해주세요" maxlength="660" required="required">${schedule.content }</textarea>
	    	
	    	<button type="submit" id="submit" style="display: none;"></button>
			<button type="button" id="submitBtn" class="btn">수정</button>
	   		<button type="button" id="cancelBtn" class="btn" onclick="location.href = '<%=request.getContextPath() %>/schedule/view?date=${schedule.schedule_date }'">취소</button>
	   		
	    </div>
	   		
	</div>
	
	<div id="side_rigth">
	
		<div class="fixTop">
		
	    	<p><button type="button" class="btnAdd2">+</button> 준비물</p>
		
		</div>
	
	    <div class="side_rigth_box">
	    	
			<div class="addInput2">
			
			<%
			
			for(int i=0; i<materialList.size(); i++) {
				String material_name = materialList.get(i).getName();
				
				out.print("<p class='addP'><button type='button' class='btnRemove2'>-</button>&nbsp;"
						+ "<input type='text' name='material' target='target' size='7px' maxlength='6' placeholder='친구' value='" 
						+  material_name
						+"' required='required' /><br></p>");
				
			}
			
			%>
			
			</div>
	    
	    </div>
	    
	    <div class="fixTop">
	    	
	    	<p><button type="button" class="btnAdd">+</button> 함께하는 친구</p>
	    	
	    </div>
	    
	    <div class="side_rigth_box">
			
			<div class="addInput">
				
			<%
			
			for(int i=0; i<friendList.size(); i++) {
				String friend_name = friendList.get(i).getName();
				
				out.print("<p class='addP'><button type='button' class='btnRemove'>-</button>&nbsp;" 
						+ "<input type='text' name='friend' target='target' size='7px' maxlength='6' placeholder='친구' value='" 
						+  friend_name
						+"' required='required' /><br></p>");
				
			}
			
			%>
			
			</div>
	    	
	    </div>
	    
	    <div class="side_rigth_box">
	    
	    	<textarea id="schedule_memo" name="schedule_memo" maxlength="60" placeholder="간단한 메모를 입력해주세요" >${schedule.memo }</textarea>
	    	
		</div>
		
	</div>

</div>


</form>


<br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
