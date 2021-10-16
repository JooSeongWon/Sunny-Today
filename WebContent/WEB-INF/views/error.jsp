<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%-- 폰트 --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <%-- fontawesome --%>
    <script src="https://kit.fontawesome.com/0d232bdc2d.js" crossorigin="anonymous"></script>
    <title>오늘은흐림?! - Error</title>
    <style>
        div {
            box-sizing: border-box;
        }

        #container {
            display: flex;
            justify-content: center;
            margin: 100px auto;
        }

        .icon {
            font-size: 400px;
            color: orange;
        }

        .description {
            padding: 50px;
            width: 400px;
        }

        .title {
            text-align: center;
        }

        @media screen and (max-width: 800px) {
            #container {
                flex-flow: column;
            }

            .icon {
                margin: auto;
                font-size: 200px;
            }

            .description {
                width: 100%;
            }
        }

    </style>
</head>
<body>
<div id="container">
    <div class="icon">
        <i class="fas fa-bug"></i>
    </div>
    <div class="description">
        <h1 class="title">Service Error!!</h1>
        <p>
            <span style="color: red; font-weight: bold;">오늘은 흐림!</span><br>
            서비스에 문제가 발생했습니다. 접속 경로를 확인해주세요. <br>
            이유를 알 수 없는 문제의 경우 담당자에게 문의 부탁드립니다. 감사합니다. <br>
            <a href="${pageContext.request.contextPath}/"><button>홈 으로</button></a>
        </p>
    </div>
</div>

</body>
</html>
