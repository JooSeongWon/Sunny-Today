<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="xyz.sunnytoday.dto.Schedule"%>
<%@ page import="xyz.sunnytoday.dto.File" %>
<%@ page import="xyz.sunnytoday.common.repository.Forecast" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Random" %>

<%

List<Schedule> scheduleList = (List) request.getAttribute("scheduleList");
List<Schedule> underSchedule = (List) request.getAttribute("underSchedule");
List<String> tenDay = (List) request.getAttribute("tenDay");
List<Forecast> resultForecast = (List) request.getAttribute("resultForecast");
List<File> fileList = (List) request.getAttribute("fileList");
List<Integer> fileCnt = (List) request.getAttribute("fileCnt");

Calendar weatherCal = Calendar.getInstance();
String pm = weatherCal.get(Calendar.HOUR_OF_DAY) < 6 || weatherCal.get(Calendar.HOUR_OF_DAY) >= 20 ? "moon" : "sun";

String yy = request.getParameter("year");
String mm = request.getParameter("month");

Calendar cal = Calendar.getInstance();

int y = cal.get(Calendar.YEAR);		//2021
int m = cal.get(Calendar.MONTH);	//9 (0~11)

if( yy!= null && mm != null && !yy.equals("") && !mm.equals("") ) {
	y = Integer.parseInt(yy);
	m = Integer.parseInt(mm)-1;
}
cal.set(y,m,1);

int dayOfweek = cal.get(Calendar.DAY_OF_WEEK); // (일:1 ~ 토:7)
int lastday = cal.getActualMaximum(Calendar.DATE);
int prevLastMth = new Date(y, m, 0).getDate();

// 이전 버튼을 위한 세팅
int b_y = y;
int b_m = m;

if(m == 0) {
	b_y = b_y - 1;
	b_m = 12;
}

// 다음 버튼을 위한 세팅
int n_y = y;
int n_m = m+2;

if(n_m == 13) {
	n_y = n_y + 1;
	n_m = 1;
}


%>

<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -뭐입지?</title>
    
    <%--페이지별 css/ js--%>
    <link href="${cssPath}/schedule_style.css" rel="stylesheet">
    <script src="${jsPath}/home_script.js"></script>
    
    <script type="text/javascript">
    
    	var str = '<input type="checkbox" class="scheduleCheckbox" name="scheduleCheckbox" />';
    	
		if(${empty userno}) {
			window.alert("로그인이 필요합니다!")
			window.location.assign("<%= request.getContextPath() %>/")
		}
		
		function goWrite() {
			window.location.assign("<%= request.getContextPath() %>/schedule/write")
		}
		
		function goDelete() {
			document.getElementById("btnRight").style.display = "none";
			document.getElementById("btnRightHide").style.display = "block";
			
			var sections = document.querySelectorAll('.scheduleCheckbox');
			
			for(var i=0; i<sections.length; i++) {
				var item = sections.item(i);
				item.style.display = 'block';
			}
		}
		
		function deleteFrm() {
			document.getElementById('deleteFrm').submit();
		}
		
		$(function(){
			$("#<c:out value='<%=tenDay.get(0) %>'/>").css({
				"color": "orange",
				"font-weight": "bold"
			});
		});
		
		$(function(){
			$(".schedule a").css({
				"color": "inherit"
			});
		});
		
		$(function() {
			
			if(<%=m+1 %> == '1') {
				document.getElementById("<%=y %>0101").style.color = "red";
			}
			if(<%=m+1 %> == '2') {
				document.getElementById("<%=y %>0211").style.color = "red";
				document.getElementById("<%=y %>0212").style.color = "red";
				document.getElementById("<%=y %>0213").style.color = "red";
			}
			if(<%=m+1 %> == '3') {
				document.getElementById("<%=y %>0301").style.color = "red";
			}
			if(<%=m+1 %> == '4') {
			}
			if(<%=m+1 %> == '5') {
				document.getElementById("<%=y %>0505").style.color = "red";
				document.getElementById("<%=y %>0519").style.color = "red";
			}
			if(<%=m+1 %> == '6') {
				document.getElementById("<%=y %>0606").style.color = "red";
			}
			if(<%=m+1 %> == '7') {
			}
			if(<%=m+1 %> == '8') {
				document.getElementById("<%=y %>0815").style.color = "red";
				document.getElementById("<%=y %>0816").style.color = "red";
			}
			if(<%=m+1 %> == '9') {
				document.getElementById("<%=y %>0816").style.color = "red";
			}
			if(<%=m+1 %> == '10') {
				console.log("10월")
			}
			if(<%=m+1 %> == '11') {
				console.log("11월")
			}
			if(<%=m+1 %> == '12') {
				console.log("12월")
			}
			
			
		})
		
    
    </script>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<br><br><br><br>



<table>
	<caption id="controllDay">
		<div style="width: 400px;">
			<form id="frm" method="get" action="<%=request.getContextPath() %>/schedule">
				<a href="<%=request.getContextPath() %>/schedule?year=<%=b_y %>&month=<%=b_m %>" class="fas fa-angle-left"></a>
				
					<input type="number" id="year" name="year" max="2100" min="2000" value="<%=y %>" />년 
					<input type="number" id="month" name="month" max="12" min="1" value="<%=m+1 %>" />월
					<input type="submit" style="display: none;" />
				
				<a href="<%=request.getContextPath() %>/schedule?year=<%=n_y %>&month=<%=n_m %>" class="fas fa-angle-right"></a>
			</form>
		</div>
	</caption>
	
<form id="deleteFrm" method="post" action="<%=request.getContextPath() %>/schedule/delete">
	
	<tr id="dayWeek">
		<th style="color: red;">일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th style="color: blue;">토</th>
	</tr>
	<%
	
	int d = 1;
	int s = 1;
	int nextMonthNo = 1;
	
	for(int i=0; i<6; i++) {
		
		out.print("<tr>");
		
		for(int j=0; j<7; j++) {
			
			String color="var(--color-black)";
			if(j == 6) {
				color = "blue";
			} else if(j == 0) {
				color = "red";
			}
			
			//저번달 숫자
			if(s < dayOfweek) {
				out.print("<td style='color: var(--color-light-grey)'>" + ((prevLastMth+s+1)-dayOfweek) + "</td>");
				
				s++;
				continue;
			}
			
			//이번달 숫자
			if(d <= lastday) {
				
				int count = 0;
				
				String zeroM = "0";
				String zeroD = "0";
				
				if(d >= 10) {
					zeroD = "";
				}
				
				if((m+1) >= 10) {
					zeroM = "";
				}
				
				String strDate = y + "" + zeroM + (m+1) + "" + zeroD + d;
				String sqlDate = y + "-" + zeroM + (m+1) + "-" + zeroD + d;
				
				//DB에서 가져온 일정과 일치하는 날짜 분별
				if(scheduleList != null) { 
					for(int k = 0; k < scheduleList.size(); k++) {
						Date schedule_day = scheduleList.get(k).getSchedule_date();
						
						String datePattern = "yyyyMMdd";
						SimpleDateFormat format = new SimpleDateFormat(datePattern);
						
						String schedule_Day_Str = format.format(schedule_day);
						
						if(strDate.equals(schedule_Day_Str)) {
							count++;
						}
						
					}
				}
				
				//이번달 td 생성
				if(count == 0) {
					out.print("<td style='color: " + color + "'; class='" + d + "' id='" + strDate + "'>"+ d + "</td>");
				} else if(count == 1) {
					out.print("<td style='color: " + color + "'; class='schedule' id='" + strDate + "'>" 
							+ "<input type='checkbox' class='scheduleCheckbox' name='scheduleCheckbox'" 
							+ " style='display: none;' value='" + sqlDate + "' />" 
							+ "<a href='" 
							+ request.getContextPath() 
							+ "/schedule/view?date="
							+ sqlDate 
							+"'>"+ d + "</a>" 
							+ "</td>");
					count--;
				}
				
			}
			
			//다음달 숫자
			if(d > lastday) {
				out.print("<td style='color: var(--color-light-grey)'>" + nextMonthNo +" </td>");
				
				nextMonthNo++;
			}
			
			d++;
			
		}
		out.print("</tr>");
	}
	
	%>
	
</form>
</table>

<br>

<div class="scheduleBnt" style="width: 275px; height: 100px;">

	<button type="button" onclick="goWrite()" id="btnLeft" class="btn" name="scheduleInsert">일정 입력</button>
	<button type="button" onclick="goDelete()" id="btnRight" class="btn">일정 삭제</button>
	<button type="button" onclick="deleteFrm()" id="btnRightHide" class="btn">일정 삭제</button>
	
</div>


<br><br><br><br><br><br>

<div class="underScheduleList">
	
	<%
	
	int num1 = 0; //2, 4, 6, 8
	int num2 = 0; //0, 2, 4, 6
	
	//각 일정마다 랜덤으로 이미지 출력 정수값 설정
	List<Integer> ranNum = new ArrayList<>();
		
	for(int i=0; i<underSchedule.size(); i++) {
			
		if(num1 != 0) {
			num2 += fileCnt.get(i);
		}
			
		num1 += fileCnt.get(i);
			
		ranNum.add((int) Math.floor( ( Math.random() * (num1 - num2) + num2 ) ));
		
	}
	
	
	for(int i=0; i<underSchedule.size(); i++) {
		
		out.println("<div class= 'mid_content'>");
      		
			//오늘 날짜 구별
			if(underSchedule.get(0).getSchedule_date().equals(underSchedule.get(i).getSchedule_date())) {
				out.println("<div class='underScheduleDate' style='color: white; background-color: orange; '><p>" + underSchedule.get(i).getSchedule_date() + "</p></div>");
			} else {
				out.println("<div class='underScheduleDate'><p>" + underSchedule.get(i).getSchedule_date() + "</p></div>");
			}
			
			out.println("<div class= 'side_content_box'>");
	         
				if(resultForecast.get(i).getWeather().equals("맑음")) {
					out.print("<i style='font-size: 95px;' class='fas fa-" + pm + "'></i>");
				} else if(resultForecast.get(i).getWeather().equals("구름많음")) {
					if(resultForecast.get(i).getChanceOfRain() >= 40) {
						out.print("<i style='font-size: 95px;' class='fas fa-cloud-" + pm + "-rain'></i>");
					}
				} else {
					out.print("<i style='font-size: 75px; margin-top: 15px;' class='fas fa-cloud'></i>");
				}
	         
			out.println("</div>");
	         
			out.println("<div class= 'side_content_box'>");
	         
				out.println("<img alt='썸네일' src='/upload/" + fileList.get(ranNum.get(i)).getThumbnail_url() +"'>");
	            
			out.println("</div>");
	         
			out.println("<div class= 'side_rigth_box'>");
	          
				out.println(underSchedule.get(i).getTitle());
	          
			out.println("</div>");
          
	out.println("</div>");
          
   }
   
   
   %>
	
</div>

<br><br><br><br><br><br>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
