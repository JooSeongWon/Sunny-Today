'use strict'

/* 현재 섹션 */
const SECTION_TERMS = 0;
const SECTION_INPUT_DATA = 1;
const SECTION_CERTIFICATION = 2;
let currentSection = SECTION_TERMS;

const sections = document.querySelectorAll('.content');
const sectionTitles = document.querySelectorAll('h1[class^="title__"]:not(.title__arrow)');


//////////////////////////////////  약관동의

/* 약관 동의 버튼 */
const CHECK_SERVICE = 0;
const CHECK_PRIVACY = 1;
const CHECK_MARKETING = 2;
const CHECK_ALL_TERMS = 3;

const isChecked = [false, false, false, false];
const checkIcons = document.querySelectorAll('.check__icon > i');
const checkTitles = document.querySelectorAll('.check__title');

/* 이벤트 설정 */
for (let i = 0; i < checkIcons.length; i++) {
    checkIcons[i].addEventListener('click', () => clickCheckHeader(i));
    checkTitles[i].addEventListener('click', () => clickCheckHeader(i));
}

function clickCheckHeader(terms) {
    if (isChecked[terms]) {
        checkIcons[terms].classList.remove('fas');
        checkIcons[terms].classList.add('far');
        if (terms !== CHECK_ALL_TERMS && isChecked[CHECK_ALL_TERMS]) {
            isChecked[CHECK_ALL_TERMS] = false;
            checkIcons[CHECK_ALL_TERMS].classList.remove('fas');
            checkIcons[CHECK_ALL_TERMS].classList.add('far');
        }
    } else {
        checkIcons[terms].classList.remove('far');
        checkIcons[terms].classList.add('fas');
    }
    isChecked[terms] = !isChecked[terms];

    if (terms === CHECK_ALL_TERMS) {
        for (let i = CHECK_SERVICE; i < CHECK_ALL_TERMS; i++) {
            if (isChecked[i] !== isChecked[CHECK_ALL_TERMS]) {
                clickCheckHeader(i);
            }
        }
    }
}


///////////////////////////// 정보입력

/* 입력 데이터 */
const INPUT_ID = 0;
const INPUT_PW = 1;
const INPUT_PW_CK = 2;
const INPUT_EMAIL = 3;
const INPUT_NICK = 4;

// 정규식
const regexList = [];
regexList[INPUT_ID] = /^[a-z0-9]{4,20}$/;
regexList[INPUT_PW] = /^(?=.*[a-zA-Z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&]).{8,20}$/;
regexList[INPUT_EMAIL] = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[0-9a-z]([-_.]?[0-9a-z])*\.[a-z]{2,3}$/;
regexList[INPUT_NICK] = /^[a-zA-Z0-9가-힣]{2,12}$/;
const regexPhone = [/^\d{3}$/, /^\d{3,4}$/, /^\d{4}$/];

const GENDER_MALE = 0;
const GENDER_FEMALE = 1;
const GENDER_NO_SELECTED = 2;

const inputBoxes = document.querySelectorAll('.input__box');
const inputNotices = document.querySelectorAll('.input__notice');
const tellInputBoxes = document.querySelectorAll('#tell input');
const genderButtons = document.querySelectorAll('.gender-pick');

const birthDatePicker = document.querySelector('.datePicker');
let currentGender = GENDER_NO_SELECTED;

/* 성별 선택 */
for (let i = 0; i < genderButtons.length; i++) {
    genderButtons[i].addEventListener('click', () => pickGender(i));
}

function pickGender(genderNum) {
    if (currentGender === genderNum) return;

    genderButtons[currentGender].classList.remove('active');
    genderButtons[genderNum].classList.add('active');
    currentGender = genderNum;
}

// test
// console.log(birthDatePicker.value === '');
// console.log(inputBoxes[0].value === '');
// const testData = 'testData123';
// console.log(testData.length);

/* 인풋박스 클라이언트 사이드 체크 */

for (let i = 0; i < inputBoxes.length; i++) {
    inputBoxes[i].addEventListener('blur', () => checkData(i));
}

function checkData(inputBoxNum) {
    const val = inputBoxes[inputBoxNum].value;

    if (val === '') {
        setNotice(false, inputBoxNum, '데이터를 입력하세요.');
        return;
    }

    switch (inputBoxNum) {
        case INPUT_ID:
            if (val.length < 4) {
                setNotice(false, inputBoxNum, '4글자 이상 입력하세요.');
            } else if (!regexList[inputBoxNum].test(val)) {
                setNotice(false, inputBoxNum, '영문 소문자와 숫자만 입력하세요.');
            } else {
                setNotice(true, inputBoxNum, '');
                checkDuplicate(inputBoxNum);
            }
            break;

        case INPUT_NICK:
            if (val.length < 2) {
                setNotice(false, inputBoxNum, '2글자 이상 입력하세요.');
            } else if (!regexList[inputBoxNum].test(val)) {
                setNotice(false, inputBoxNum, '영문자와 숫자, 한글만 입력하세요.');
            } else {
                setNotice(true, inputBoxNum, '');
                checkDuplicate(inputBoxNum);
            }
            break;

        case INPUT_EMAIL:
            if (!regexList[inputBoxNum].test(val)) {
                setNotice(false, inputBoxNum, '유효한 이메일 형식이 아닙니다.');
            } else {
                setNotice(true, inputBoxNum, '');
                checkDuplicate(inputBoxNum);
            }
            break;

        case INPUT_PW:
            if (val.length < 8) {
                setNotice(false, inputBoxNum, '8글자 이상 입력하세요.');
            } else if (!regexList[inputBoxNum].test(val)) {
                setNotice(false, inputBoxNum, '사용가능한 특수문자는 $`~!@$!%*#^?& 입니다.');
            } else {
                setNotice(true, inputBoxNum, '유효한 비밀번호 입니다.');
            }
            if (inputBoxes[INPUT_PW_CK].value.length === 0) {
                break;
            }
            inputBoxNum++;
        //일부로 떨굼, 비밀번호 확인후 비밀번호 체크 다시한번 확인!
        case INPUT_PW_CK:
            if (inputBoxes[INPUT_PW].value !== inputBoxes[INPUT_PW_CK].value) {
                setNotice(false, inputBoxNum, '비밀번호가 일치하지 않습니다.');
            } else {
                setNotice(true, inputBoxNum, '비밀번호가 일치합니다.');
            }
            break;
    }
}

/* 로딩이미지 태그 생성 */
function getLoadingAni(width, height) {

    const w = width === undefined ? '30px' : width + 'px';
    const h = height === undefined ? '30px' : height + 'px';

    const loadingAni = document.createElement('img');
    loadingAni.setAttribute('src', contextPath + 'resources/img/spinner.gif');
    loadingAni.style.width = w;
    loadingAni.style.height = h;
    loadingAni.style.display = 'inline-block';

    return loadingAni;
}


function setNotice(check, inputBoxNum, msg) {
    if (check) {
        inputNotices[inputBoxNum].classList.remove('warning');
        inputNotices[inputBoxNum].innerText = msg;
    } else {
        inputNotices[inputBoxNum].classList.add('warning');
        inputNotices[inputBoxNum].innerText = msg;
    }
}

function checkDuplicate(inputBoxNum) {
    //로딩 보여주기 - 안 없애도 어차피 innerText로 덮어서 지워짐.
    const loadingObj = getLoadingAni();
    inputNotices[inputBoxNum].appendChild(loadingObj);

    $.ajax({
        type: 'POST',
        url: contextPath + 'join',
        data: encodeAllData({
            reqType: 'checkDuplicate',
            dataType: `${inputBoxNum}`,
            data: inputBoxes[inputBoxNum].value
        }),
        dataType: 'json',
        success: data => setNotice(data.result, inputBoxNum, data.msg),
        error: () => setNotice(false, inputBoxNum, '서버 연결 오류')
    });
}


////////////////////////////// 하단버튼
const nextBtn = document.querySelector('.button__next');
const cancelBtn = document.querySelector('.button__cancel');

nextBtn.addEventListener('click', checkInput);
nextBtn.addEventListener('keydown', enterKeyDownEventBridge);

cancelBtn.addEventListener('click', askCancel);
cancelBtn.addEventListener('keydown', enterKeyDownEventBridge);

function checkInput() {
    if (currentSection === SECTION_TERMS) {
        //약관동의 확인 버튼 - 현재 마케팅 정보를 다루지 않아서 일단 데이터를 서버로 전송하지는 않음
        if (isChecked[CHECK_SERVICE] && isChecked[CHECK_PRIVACY]) {
            doNext();
        } else {
            showModal('약관동의 오류', '필수 이용약관에 모두 동의하셔야만 회원가입이 가능합니다.');
        }
        return;
    }

    //정보입력 확인 버튼
    if (currentSection === SECTION_INPUT_DATA) {
        let isEmpty = false;

        //빈칸 체크
        if (userType === 'normal') {
            for (let inputBox of inputBoxes) {
                if (isEmpty) {
                    break;
                }
                if (inputBox.value.length === 0) {
                    isEmpty = true;
                }
            }
        } else if (inputBoxes[INPUT_NICK].value.length === 0) isEmpty = true;

        for (let inputBox of tellInputBoxes) {
            if (isEmpty) {
                break;
            }
            if (inputBox.value.length === 0) {
                isEmpty = true;
            }
        }
        if (birthDatePicker.value.length === 0) isEmpty = true;


        if (isEmpty) {
            showModal('입력오류', '빈칸을 모두 채워주세요!');
            return;
        }

        //입력형식 검사
        const inputTitles = ['아이디', '비밀번호', '비밀번호확인', '이메일', '닉네임'];
        let isPass = true;
        let i = userType === 'normal' ? INPUT_ID : INPUT_NICK; //소셜 가입은 아이디, 비번, 이메일 확인안함
        while (i < inputBoxes.length) {
            const val = inputBoxes[i].value;

            if (i === INPUT_PW_CK) {
                if (inputBoxes[INPUT_PW].value !== val) {
                    isPass = false;
                    break;
                }
                i++
                continue;
            }

            if (!regexList[i].test(val)) {
                isPass = false;
                break;
            }

            i++
        }

        if (!isPass) {
            showModal("입력오류", inputTitles[i] + " 입력을 확인하세요!");
            return;
        }

        for (let i = 0; i < tellInputBoxes.length; i++) {
            if (!regexPhone[i].test(tellInputBoxes[i].value)) {
                showModal("입력오류", "핸드폰번호를 확인하세요!");
                return;
            }
        }

        //서버로 보낼 데이터
        let reqData;
        if (userType === 'normal') {
            reqData = encodeAllData({
                reqType: 'joinOriginMember',
                userId: inputBoxes[INPUT_ID].value,
                userPw: inputBoxes[INPUT_PW].value,
                email: inputBoxes[INPUT_EMAIL].value,
                nick: inputBoxes[INPUT_NICK].value,
                phone: tellInputBoxes[0].value + tellInputBoxes[1].value + tellInputBoxes[2].value,
                birth: birthDatePicker.value,
                gender: genderButtons[currentGender].getAttribute('data-gender')
            });
        } else {
            reqData = encodeAllData({
                reqType: 'joinSocialMember',
                nick: inputBoxes[INPUT_NICK].value,
                phone: tellInputBoxes[0].value + tellInputBoxes[1].value + tellInputBoxes[2].value,
                birth: birthDatePicker.value,
                gender: genderButtons[currentGender].getAttribute('data-gender')
            });
        }

        //로딩객체
        const buttonSet = document.querySelector('.button');
        buttonSet.removeChild(cancelBtn);
        buttonSet.removeChild(nextBtn);
        const loadingImg = getLoadingAni(50, 50);
        buttonSet.appendChild(loadingImg);

        //서버로 가입요청
        $.ajax({
            type: 'POST',
            url: contextPath + 'join',
            data: reqData,
            dataType: 'json',
            success: data => join(data, loadingImg),
            error: () => {
                buttonSet.removeChild(loadingImg);
                buttonSet.appendChild(cancelBtn);
                buttonSet.appendChild(nextBtn);
                showModal('연결 오류', '서버와 정상적으로 통신하지 못했습니다.')
            }
        });
    }
}

function join(data, loadingImg) {
    document.querySelector('.button').removeChild(loadingImg);
    document.querySelector('.button').appendChild(cancelBtn);
    document.querySelector('.button').appendChild(nextBtn);

    if (data.result) {
        if (userType === 'normal') {
            doNext();
            return;
        }
        //소셜가입 성공 처리
        showModal('가입성공!', '모든 서비스 이용이 가능합니다.', () => {
            location.href = contextPath;
        });
        return;
    }

    showModal('가입 오류', data.msg);
}

function doNext() {
    sections[currentSection].classList.remove('active');
    sectionTitles[currentSection++].classList.remove('active');
    sections[currentSection].classList.add('active');
    sectionTitles[currentSection].classList.add('active');
    window.scrollTo(0, 0);
}

function askCancel() {
    showModal('취소', '회원가입을 취소하고 홈으로 돌아가시겠습니까?', () => location.href = contextPath,
        () => {
        });
}