package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface SocialLoginService {
    /**
     * 네이버 소셜로그인 url을 만든다.
     *
     * @param request 세션을 설정할 요청정보
     * @return 로그인 url
     */
    String createNaverLoginUrl(HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * 구글 소셜로그인 url을 만든다.
     *
     * @param request 세션을 설정할 요청정보
     * @return 로그인 url
     */
    String createGoogleLoginUrl(HttpServletRequest request) throws UnsupportedEncodingException;


    /**
     * 소셜로그인 콜백을 통해 해당 회원의 이메일을 얻는다.
     *
     * @param request 콜백받은 요청
     * @return 소셜 계정의 이메일, 요청을 파싱할수 없는경우 null을 반환합니다.
     */
    String getEmailOrNull(HttpServletRequest request, int socialType);
}
