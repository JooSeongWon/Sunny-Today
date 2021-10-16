<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<td class="active">쪽지 제목</td>
<c:forEach items="${elist }" var="message">
<td colspan="4"><input type="text" name="title" class="form-control" value="${message.title }"></td>
</c:forEach>