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


}
