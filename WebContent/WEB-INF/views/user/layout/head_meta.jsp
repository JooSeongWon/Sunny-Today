<%--
  Created by IntelliJ IDEA.
  User: parkjungtae
  Date: 2021-09-29
  Time: 오후 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--리소스 경로--%>
<%
    pageContext.getRequest().setAttribute("resourcesPath", request.getContextPath() + "/resources");
    pageContext.getRequest().setAttribute("cssPath", request.getContextPath() + "/resources/css");
    pageContext.getRequest().setAttribute("jsPath", request.getContextPath() + "/resources/js");
    pageContext.getRequest().setAttribute("imgPath", request.getContextPath() + "/resources/img");
%>
<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="description" content="오늘의 날씨와 의상추천 일정기록 제공, 일상패션 커뮤니티 오늘도 맑음 입니다.">
<%-- 아이콘 --%>
<link rel="icon" type="image/png" href="${requestScope.imgPath}/favicon.png"/>
<%-- 폰트 --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<%-- fontawesome --%>
<script src="https://kit.fontawesome.com/0d232bdc2d.js" crossorigin="anonymous"></script>
<%-- common script --%>
<script>
    const contextPath = '${pageContext.request.contextPath}/';
</script>
<script src="${requestScope.jsPath}/jquery-2.2.4.min.js"></script>
<script src="${requestScope.jsPath}/common.js" defer></script>
<%-- common style --%>
<link rel="stylesheet" href="${requestScope.cssPath}/common.css">
