'use strict'

/* 버튼 이벤트 연결 */
const btnId = document.querySelector('.button-id');
const btnPw = document.querySelector('.button-pw');

const email = document.querySelector('.email');
const userId = document.querySelector('.userid');

const TYPE_ID = 0;
const TYPE_PW = 1;

btnPw.addEventListener('click',() => request(TYPE_PW));
btnId.addEventListener('click',() => request(TYPE_ID));


function request(type) {
    if (!check(type)) {
        return;
    }

    let reqData;
    if (type === TYPE_ID) {
        reqData = encodeAllData({
            type: "id",
            email: email.value
        });
    } else {
        reqData = encodeAllData({
            type: "pw",
            email: email.value,
            userId: userId.value
        });
    }

    $.ajax({
        type: 'POST',
        url: contextPath + 'find',
        data: reqData,
        dataType: 'json',
        success: data => {
            if (data.result) {
                const completeSection = document.querySelector('.complete');
                const findSection = document.querySelector('#find');

                findSection.classList.remove('active');
                completeSection.classList.add('active');
            } else {
                showModal('서버 오류', data.msg);
            }
        },
        error: () => {
            showModal('연결 오류', '서버와 정상적으로 통신하지 못했습니다.');
        }
    });
}

/* 유효성 검사 */
function check(type) {
    const regexId = /^[a-z0-9]{4,20}$/;
    const regexEmail = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[0-9a-z]([-_.]?[0-9a-z])*\.[a-z]{2,3}$/;
    if (type === TYPE_ID) {
        let isPass = regexEmail.test(email.value);
        if(!isPass) {
            showModal('입력오류', '이메일형식에 맞지 않습니다!');
        }
        return isPass;
    } else {
        let isPass = regexEmail.test(email.value);
        if(!isPass) {
            showModal('입력오류', '이메일 형식에 맞지 않습니다!');
        } else if(!regexId.test(userId.value)) {
            showModal('입력오류', '아이디 형식에 맞지 않습니다!');
            isPass = false;
        }
        return isPass;
    }
}