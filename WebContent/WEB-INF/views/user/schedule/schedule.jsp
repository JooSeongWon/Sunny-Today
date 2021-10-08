<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%

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

    
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<br><br><br>

<form name="frm" method="post" action="<%=request.getContextPath() %>/schedule">
<input type="text" name="year" size="3" />년
<input type="text" name="month" size="3" />월
<input type="submit" value="달력보기" />
</form>

<br>

<table>
	<caption>
	<%=y %>년 <%=m+1 %>월
	</caption>
	<tr id="dayWeek">
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
	<%
	
	int d = 1;
	int s = 1;
	int nextMonthNo = 1;
	
	for(int i=0; i<6; i++) {
		
		out.print("<tr>");
		
		for(int j=0; j<7; j++) {
			
			String color="#555555";
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
				out.print("<td style='color: " + color + "'>"+ d + "</td>");
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
