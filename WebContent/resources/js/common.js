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
let publicKey;
const getRSAPublicKey = () => {
    //키가 없음! AJAX를 통해 서버에서 받아옴
    if (publicKey === undefined) {
        $.ajax({
            type: 'POST',
            url: contextPath + 'get/public/key',
            dataType: 'text',
            async: false, //동기
            success: data => publicKey = data,
            error: console.log //시간나면 예외처리 할것
        });
    }
    return publicKey;
};

/* 데이터 암호화 */
function encodeAllData(obj) {
    for (let name in obj) {
        obj[name] = encodeData(obj[name]);
    }
    return obj;
}

function encodeData(data) {
    const publicKey = getRSAPublicKey();
    const crypt = new JSEncrypt();
    crypt.setPublicKey(publicKey);

    return crypt.encrypt(data);
}

/* 버튼 엔터 키 입력 공용 이벤트 */
function enterKeyDownEventBridge(e) {
    if(e.key === 'Enter') {
        this.click();
    }
}