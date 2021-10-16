<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<td class="active">본문</td>
<c:forEach items="${elist }" var="message">
<td colspan="4"><textarea id="content" name="content">${message.content }</textarea></td>
</c:forEach>

<!-- <textarea>태그에 스마트에디터2 적용하는 스크립트 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 	oAppRef: oEditors,
 	elPlaceHolder: "content",
 	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
 	fCreator: "createSEditor2"
});
</script>
