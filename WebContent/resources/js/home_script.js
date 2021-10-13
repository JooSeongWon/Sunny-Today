'use strict'

/* 날씨 슬라이더 */
const sliderContents = document.querySelector('.slider__contents');
let pressed;
let startX;
let scrollLeft;

const end = () => {
    pressed = false;
    sliderContents.classList.remove('active');
}

const start = e => {
    pressed = true;
    sliderContents.classList.add('active');
    startX = e.pageX || e.touches[0].pageX - sliderContents.offsetLeft;
    scrollLeft = sliderContents.scrollLeft;
}

const move = e => {
    if (!pressed) return;

    e.preventDefault();
    const x = e.pageX || e.touches[0].pageX - sliderContents.offsetLeft;
    const dist = (x - startX);
    sliderContents.scrollLeft = scrollLeft - dist;
}

sliderContents.addEventListener('mousedown', start);
sliderContents.addEventListener('touchstart', start);

sliderContents.addEventListener('mousemove', move);
sliderContents.addEventListener('touchmove', move);

sliderContents.addEventListener('mouseleave', end);
sliderContents.addEventListener('mouseup', end);
sliderContents.addEventListener('touchend', end);