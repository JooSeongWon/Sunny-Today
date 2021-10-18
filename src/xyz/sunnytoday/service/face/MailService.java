package xyz.sunnytoday.service.face;

import javax.mail.MessagingException;

/**
 * 메일 전송 서비스
 */
public interface MailService {
    /**
     * 회원가입 인증 메일을 발송합니다.
     *
     * @param secretKey   회원 인증키
     * @param mailAddress 회원 이메일 주소
     */
    void postJoinVerificationMail(String secretKey, String mailAddress) throws MessagingException;

    /**
     * 아이디 찾기 결과메일을 발송합니다.
     *
     * @param userId   회원 아이디
     * @param mailAddress 회원 이메일 주소
     */
    void postFindUserIdResult(String userId, String mailAddress) throws MessagingException;

    /**
     * 임시비밀번호 발급메일을 발송합니다.
     *
     * @param userPw   임시비밀번호
     * @param mailAddress 회원 이메일 주소
     */
    void postFindUserPwResult(String userPw, String mailAddress) throws MessagingException;


}
