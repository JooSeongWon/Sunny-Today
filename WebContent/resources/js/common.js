'use strict'

/* 네비게이션바 반응형 */
const navbarList = document.querySelector('.navbar__list');
const barsBtn = document.querySelector('.navbar__bars');
barsBtn.onclick = () => {
    if (navbarList.classList.contains('active')) {
        navbarList.classList.remove('active');
    } else {
        navbarList.classList.add('active');
    }
};


/* 헤더를 통해 홈으로 이동 */
const header = document.querySelector('#header');
header.onclick = () => {
    location.href = contextPath;
};


/* top버튼 고정이벤트 */
const topBtn = document.querySelector('.top');
document.addEventListener('scroll', () => {
    if (window.scrollY >= header.getBoundingClientRect().height) {
        topBtn.classList.add('active');
    } else {
        topBtn.classList.remove('active');
    }
});

/* RSA 공개키 가져오기 */
const getRSAPublicKey = () => {
    let publicKey = window.sessionStorage.getItem('publicKey');
    //세션 스토리지에 키 없음! 요청하기!
    if(publicKey === null) {
        $.ajax({
            type: 'POST',
            url: contextPath + 'get/public/key',
            dataType: 'text',
            async: false,
            success: data => {
                window.sessionStorage.setItem('publicKey', data);
                publicKey = window.sessionStorage.getItem('publicKey');
            } ,
            error: console.log
        });
    }

    return publicKey;
};