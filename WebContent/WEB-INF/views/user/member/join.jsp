<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <%--head meta data--%>
    <c:import url="../layout/head_meta.jsp"/>
    <%--page title--%>
    <title>오늘도 맑음 -회원가입</title>
    <%--join css--%>
    <link rel="stylesheet" href="${requestScope.cssPath}/join_style.css">
    <%--join js--%>
    <script src="${requestScope.jsPath}/jsencrypt.min.js" defer></script>
    <script src="${requestScope.jsPath}/join_script.js" defer></script>
</head>
<body>
<%--header--%>
<c:import url="../layout/header.jsp"/>
<%--navbar--%>
<c:import url="../layout/navbar.jsp"/>

<section id="join">
    <div class="join__wrap">
        <div class="title">
            <h1 class="title__check active">약관동의</h1>
            <h1 class="title__arrow"><i class="fas fa-chevron-right"></i></h1>
            <h1 class="title__input">정보입력</h1>
            <h1 class="title__arrow"><i class="fas fa-chevron-right"></i></h1>
            <h1 class="title__certification">메일인증</h1>
        </div>
        <div class="line"></div>


        <%-- 약관동의 --%>
        <div class="content active">
            <div class="check">
                <div class="check__header">
                    <div class="check__icon"><i class="far fa-check-circle"></i></div>
                    <div class="check__title">서비스 이용약관 동의 (필수)</div>
                </div>
                <div class="check__description"><p class="check__content">Lorem ipsum dolor sit amet, consectetur
                    adipisicing elit. A, animi architecto aspernatur autem culpa delectus eaque eos, est facere libero
                    maxime minus quae reiciendis repellat repudiandae rerum sunt tenetur vel. Lorem ipsum dolor sit
                    amet, consectetur adipisicing elit. Adipisci aliquam dicta et eum id, ipsum libero magni natus nulla
                    provident quibusdam repellendus sed tenetur, totam ut. Illum provident quia sapiente!</p></div>
            </div>
            <div class="check">
                <div class="check__header">
                    <div class="check__icon"><i class="far fa-check-circle"></i></div>
                    <div class="check__title">개인정보 처리방침 동의 (필수)</div>
                </div>
                <div class="check__description"><p class="check__content">Lorem ipsum dolor sit amet, consectetur
                    adipisicing elit. A, animi architecto aspernatur autem culpa delectus eaque eos, est facere libero
                    maxime minus quae reiciendis repellat repudiandae rerum sunt tenetur vel. Lorem ipsum dolor sit
                    amet, consectetur adipisicing elit. Adipisci aliquam dicta et eum id, ipsum libero magni natus nulla
                    provident quibusdam repellendus sed tenetur, totam ut. Illum provident quia sapiente!</p></div>
            </div>
            <div class="check">
                <div class="check__header">
                    <div class="check__icon"><i class="far fa-check-circle"></i></div>
                    <div class="check__title">개인정보 마케팅 활용 동의 (선택)</div>
                </div>
                <div class="check__description"><p class="check__content">Lorem ipsum dolor sit amet, consectetur
                    adipisicing elit. A, animi architecto aspernatur autem culpa delectus eaque eos, est facere libero
                    maxime minus quae reiciendis repellat repudiandae rerum sunt tenetur vel. Lorem ipsum dolor sit
                    amet, consectetur adipisicing elit. Adipisci aliquam dicta et eum id, ipsum libero magni natus nulla
                    provident quibusdam repellendus sed tenetur, totam ut. Illum provident quia sapiente!</p></div>
            </div>
            <div class="line last"></div>
            <div class="check__header">
                <div class="check__icon"><i class="far fa-check-circle"></i></div>
                <div class="check__title">전체동의</div>
            </div>
        </div>


        <%-- 정보입력 --%>
        <div class="content">
            <%-- 일반 가입유저만 입력 --%>
            <div class="origin">
                <label class="input">
                    <div class="input__title">아이디</div>
                    <input type="text" class="input__box" placeholder="4자 ~ 20자 사이의 영소문자, 숫자" tabindex="1"
                           maxlength="20">
                    <div class="input__notice">&nbsp;</div>
                </label>
                <label class="input">
                    <div class="input__title">비밀번호</div>
                    <input type="password" class="input__box" placeholder="8자 ~ 20자 사이의 영(대/소)문자 / 숫자 / 특수문자"
                           tabindex="1" maxlength="20">
                    <div class="input__notice">&nbsp;</div>
                </label>
                <label class="input">
                    <div class="input__title">비밀번호 확인</div>
                    <input type="password" class="input__box" placeholder="비밀번호를 한번 더 입력해 주세요." tabindex="1"
                           maxlength="20">
                    <div class="input__notice">&nbsp;</div>
                </label>
                <label class="input">
                    <div class="input__title">이메일 주소</div>
                    <input type="text" class="input__box" placeholder="이메일 인증이 필요하니 유효한 주소를 입력해주세요." tabindex="1"
                           maxlength="100">
                    <div class="input__notice">&nbsp;</div>
                </label>
                <div class="line"></div>
            </div>
            <div class="normal">
                <label class="input">
                    <div class="input__title">닉네임</div>
                    <input type="text" class="input__box" placeholder="2자 ~ 12자 한글 / 영문 / 숫자" tabindex="1"
                           maxlength="12">
                    <div class="input__notice">&nbsp;</div>
                </label>
                <label id="tell" class="input-horizontal">
                    <div class="input__title">전화번호</div>
                    <div class="input-horizontal__content">
                        <input type="text" maxlength="3" tabindex="1">
                        <div class="horizontal-bar"></div>
                        <input type="text" maxlength="4" tabindex="1">
                        <div class="horizontal-bar"></div>
                        <input type="text" maxlength="4" tabindex="1">
                    </div>
                </label>
                <label class="input-horizontal">
                    <div class="input__title">생년월일</div>
                    <div class="input-horizontal__content">
                        <input class="datePicker" type="date">
                    </div>
                </label>
                <div class="input-horizontal">
                    <div class="input__title">성별</div>
                    <div class="input-horizontal__content gender">
                        <div class="gender-pick" data-gender="M">남자</div>
                        <div class="gender-pick" data-gender="F">여자</div>
                        <div class="gender-pick active" data-gender="A">선택안함</div>
                    </div>
                </div>
            </div>
            <div class="line last"></div>
        </div>


        <%--메일 발송 안내--%>
        <div id="certification" class="content">
            <i class="fas fa-envelope"></i>
            <h3 class="certification__title">메일함을 확인해주세요!</h3>
            <p class="certification__description">
                입력하신 메일주소로 인증링크를 발송했습니다. <br>
                메일을 확인하고 링크를 클릭하여 가입을 마무리 하세요! <br><br>
                <span>인증링크는 30분간 유효합니다.</span>
            </p>

            <div class="resend"><i class="fas fa-question-circle"></i><a href="#" class="resend__description">혹시 메일주소를
                잘못 입력하셨거나 메일을 받지 못하셨나요?</a></div>

        </div>

        <%--하단 버튼--%>
        <div class="button">
            <div class="button__cancel" tabindex="2">취소</div>
            <div class="button__next" tabindex="1">다음</div>
        </div>
    </div>
</section>

<%--footer--%>
<c:import url="../layout/footer.jsp"/>
</body>
</html>
