'use strict'

/* 슬라이더 */
const sliderContents = document.querySelector('.slider__contents');
const scheduleContents = document.querySelector('.schedule-cards');
let pressed;
let startX;
let scrollLeft;

function scrollEnd() {
    pressed = false;
    this.classList.remove('active');
}

function scrollStart(e) {
    pressed = true;
    this.classList.add('active');
    startX = e.pageX || e.touches[0].pageX - this.offsetLeft;
    scrollLeft = this.scrollLeft;
}

function scrollMove(e) {
    if (!pressed) return;

    e.preventDefault();
    const x = e.pageX || e.touches[0].pageX - this.offsetLeft;
    const dist = (x - startX);
    this.scrollLeft = scrollLeft - dist;
}

sliderContents.addEventListener('mousedown', scrollStart);
scheduleContents.addEventListener('mousedown', scrollStart);
sliderContents.addEventListener('touchstart', scrollStart);
scheduleContents.addEventListener('touchstart', scrollStart);

sliderContents.addEventListener('mousemove', scrollMove);
scheduleContents.addEventListener('mousemove', scrollMove);
sliderContents.addEventListener('touchmove', scrollMove);
scheduleContents.addEventListener('touchmove', scrollMove);

sliderContents.addEventListener('mouseleave', scrollEnd);
scheduleContents.addEventListener('mouseleave', scrollEnd);
sliderContents.addEventListener('mouseup', scrollEnd);
scheduleContents.addEventListener('mouseup', scrollEnd);
sliderContents.addEventListener('touchend', scrollEnd);
scheduleContents.addEventListener('touchend', scrollEnd);

/* 의상 새로고침 */
const refreshBtn = document.querySelector('.costume__refresh');
refreshBtn.addEventListener('click', () => $.ajax({
    type: 'POST',
    url: contextPath + 'main',
    data: {req: 'refresh', temp: document.querySelector('.weather-card__temperatures').innerText},
    dataType: 'json',
    success: refreshClothes,
    error: () => showModal('연결 오류', `서버와 연결에 실패했습니다. [${text}]`)
}));

function refreshClothes(data) {
    const top = document.querySelector('.cloth-top');
    const pants = document.querySelector('.cloth-pants');

    top.setAttribute('src', contextPath + 'upload/' + data.topThumbNail);
    document.querySelector('.cloth-top + .clothes__description').innerText = data.topTitle;

    pants.setAttribute('src', contextPath + 'upload/' + data.pantsThumbNail);
    document.querySelector('.cloth-pants + .clothes__description').innerText = data.pantsTitle;
}

