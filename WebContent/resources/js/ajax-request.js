'use strict'

function getXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }

    if (window.ActiveXObject) {
        try {
            return new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                return new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e1) {
                return null;
            }
        }
    }

    return null;
}


function sendRequest(method, url, params, callback) {
    const httpRequest = getXMLHttpRequest();

    if (httpRequest === null) {
        throw new Error("AJAX 미지원 클라이언트");
    }

    const httpMethod = method && (method === 'GET' || method === 'POST') ? method : 'GET';
    let httpUrl = url;
    let httpParams = '';

    for (let key in params) {
        if (httpParams !== '') {
            httpParams += '&';
        }
        httpParams += `${key}=${params[key]}`;
    }

    if (httpMethod === 'GET' && httpParams !== '') {
        httpUrl += `? ${httpParams}`;
    }

    httpRequest.open(httpMethod, httpUrl, true);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    httpRequest.onreadystatechange = callback;
    httpRequest.send(httpMethod === 'POST' ? httpParams : null);
}