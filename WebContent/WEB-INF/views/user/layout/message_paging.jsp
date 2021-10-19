<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
   <ul class="pagination">
   <%-- 이전 페이징 리스트로 이동 --%>
   <c:choose>
   <c:when test="${paging.startPage ne 1 }">
      <li><a href="/message?curPage=${paging.startPage - paging.pageCount }"><i class="fas fa-chevron-left"></i></a></li>
   </c:when>
   <c:when test="${paging.startPage eq 1 }">
      <li class="disabled"><a><i class="fas fa-chevron-left"></i></a></li>
   </c:when>
   </c:choose>

   <%-- 페이징 리스트 --%>
   <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
   <c:if test="${paging.curPage eq i }">
      <li class="active"><a href="/message?curPage=${i }">${i }</a></li>
   </c:if>
   <c:if test="${paging.curPage ne i }">
      <li><a href="/message?curPage=${i }">${i }</a></li>
   </c:if>
   </c:forEach>

   <%-- 다음 페이징 리스트로 이동 --%>
   <c:choose>
   <c:when test="${paging.endPage ne paging.totalPage }">
      <li><a href="/message?curPage=${paging.startPage + paging.pageCount }"><i class="fas fa-chevron-right"></i></a></li>
   </c:when>
   <c:when test="${paging.endPage eq paging.totalPage }">
      <li class="disabled"><a><i class="fas fa-chevron-right"></i></a></li>
   </c:when>
   </c:choose>
   </ul>
</div>