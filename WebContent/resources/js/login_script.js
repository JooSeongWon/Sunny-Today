// noinspection RegExpDuplicateCharacterInClass
'use strict'

/* 일반 회원 로그인 */

//데이터
const userId = document.querySelector('#input-id');
const userPw = document.querySelector('#input-pw');
const originBtn = document.querySelector('#buttons__origin');

//로그인 요청
originBtn.addEventListener('click', () => {
    if (isValidation()) {
        $.ajax({
            type: 'POST',
            url: contextPath + 'login',
            data: encodeAllData({type: 'origin', userId: userId.value, userPw: userPw.value}),
            dataType: 'json',
            success: originLoginCallBack,
            error: errorCallBack
        });
    } else {
        showModal('입력 오류', '잘 못 된 입력입니다.');
        userPw.value = "";
    }
});

//데이터 유효성 검사
const idRegex = /^[a-z0-9]{4,20}$/;
const pwRegex = /^(?=.*[a-zA-Z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&]).{8,20}$/;

function isValidation() {
    return !(!idRegex.test(userId.value) || !pwRegex.test(userPw.value));
}

//일반 사용자 로그인 결과 콜백
function originLoginCallBack(data) {
    if (!data.result) {
        showModal('로그인 실패', data.msg);
        userPw.value = "";
        return;
    }

    //성공
    location.href = contextPath;
}

//AJAX 실패 콜백
function errorCallBack(xhr, text) {
    showModal('연결 오류', `서버와 연결에 실패했습니다. [${text}]`);
}

//엔터 이벤트
const enterLogin = e => {
    if (e.key === 'Enter') {
        originBtn.click();
    }
};

originBtn.addEventListener('keydown', enterLogin)
userPw.addEventListener('keydown', enterLogin);


//소셜 로그인
const naverBtn = document.querySelector('#buttons__naver');
const googleBtn = document.querySelector('#buttons__google');

naverBtn.addEventListener('click', () => socialLogin('naver'));
googleBtn.addEventListener('click', () => socialLogin('google'));

function socialLogin(provider) {
    $.ajax({
        type: 'POST',
        url: contextPath + 'login',
        data: encodeAllData({type: 'social', provider: provider}),
        dataType: 'json',
        success: socialLoginCallBack,
        error: errorCallBack
    });
}

function socialLoginCallBack(data) {
    if (!data.result) {
        showModal('로그인 실패', data.msg);
        return;
    }
    //소셜 로그인 페이지로 리다이렉트
    location.href = data.msg;
}