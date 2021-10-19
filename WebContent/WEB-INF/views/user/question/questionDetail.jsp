<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -문의 작성</title>

    <%--페이지별 css/ js--%>
    <link href="${cssPath}/question.css" rel="stylesheet">
    <script src="${jsPath}/question_script.js"></script>


</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<div id="questionDetailTitle">
    <hr>
    <h3>문의상세</h3>
</div>


<div id="questionDetailContent">
    <div id="questionDetailcolor">
        <table id="questionDetailTable">
            <tr class="questionDetailWidth">
                <td>제목</td>
                <td>${questionDetail.title }</td>
                <td>
                    <a href="<%=request.getContextPath() %>/question/update?questionno=${questionDetail.question_no }">
                        <button id="btnUpdate">수정</button>
                    </a>
                </td>
                <td>
                    <a href="<%=request.getContextPath() %>/question/delete?questionno=${questionDetail.question_no }">
                        <button id="btnDelete">삭제</button>
                    </a>
                </td>
            </tr>
            <tr id="questionNick">
                <td>작성자</td>
                <td colspan="3">${nick }</td>
            </tr>
            <tr>
                <td colspan="4" class="questionText" id="questionContentText">${questionDetail.content }</td>
            </tr>
            <tr id="questionBack">
                <td colspan="4">
                    <a href="<%=request.getContextPath() %>/question/list">
                        <button id="backList">목록으로</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td colspan="4" id="questionAnswer">답변</td>
            </tr>
            <tr>
                <c:if test="${empty questionDetail.answer }">
                    <td colspan="4" class="questionText">아직 답변을 받지 못했습니다, 잠시만 기다려주세요!</td>
                </c:if>
                <c:if test="${not empty questionDetail.answer }">
                    <td colspan="4" class="questionText">${questionDetail.answer}</td>
                </c:if>
            </tr>
        </table>
    </div>
</div>


<c:import url="../layout/footer.jsp"/>
</body>
</html>