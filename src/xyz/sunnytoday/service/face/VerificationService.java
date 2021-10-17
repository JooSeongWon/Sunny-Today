package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

/**
 * 인증 서비스 로직을 담당합니다.
 */
public interface VerificationService {

    /**
     * 회원가입을 위한 이메일 인증을 처리합니다.
     * @param request 회원가입 이메일 인증요청
     * @return 회원가입 결과
     */
    boolean verifyEmailForMemberJoin(HttpServletRequest request);
}
