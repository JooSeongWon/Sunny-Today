<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="xyz.sunnytoday.dto.Schedule"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%

List<Schedule> scheduleList = (List) request.getAttribute("scheduleList");

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

// for(int i = 0; i < scheduleList.size(); i++) {
// 	Date str = scheduleList.get(i).getSchedule_date();
	
// }

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
    
		<% if(request.getAttribute("user_no_ok") == "N") { %>
		
		window.alert("로그인이 필요합니다!")
		window.location.assign("<%= request.getContextPath() %>/")
		
		<% } %>
    
    </script>
    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<br><br><br>


<br>

<table>
	<caption>
		<form id="frm" method="post" action="<%=request.getContextPath() %>/schedule" style="width: 525px;">
			<a href="<%=request.getContextPath() %>/schedule?year=<%=b_y %>&month=<%=b_m %>" class="fas fa-angle-left"></a>
			
			<input type="number" id="year" name="year" max="2100" min="2000" value="<%=y %>" />년 
			<input type="number" id="month" name="month" max="12" min="1" value="<%=m+1 %>" />월
			<input type="submit" style="display: none;" />
			
			<a href="<%=request.getContextPath() %>/schedule?year=<%=n_y %>&month=<%=n_m %>" class="fas fa-angle-right"></a>
		</form>
	</caption>
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
				out.print("<td style='color: " + color + "' class='" + d + "'>"+ d + "</td>");
				String day = y + "-" + (m+1) + "-" + d;
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				Date to = fm.parse(day);
				
				for(int k = 0; k < scheduleList.size(); k++) {
					Date schedule_day = scheduleList.get(k).getSchedule_date();
					
					out.print(schedule_day + "<br>");
					out.print(to + "<br>");
					if(schedule_day == to) {
						out.print("성공");
					}
					
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
</table>

<br>

<div class="scheduleBnt">
<form action="<%= request.getContextPath() %>/schedule/inert" method="post">

<input type="submit" id="btnLeft" name="scheduleInsert" value="일정입력" />

</form>
</div>

<div class="scheduleBnt">
<form action="<%= request.getContextPath() %>/schedule/delete" method="post">

<input type="submit" id="btnRight" name="scheduleDelete" value="일정삭제" />

</form>
</div>



<br><br><br><br><br><br>


<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
