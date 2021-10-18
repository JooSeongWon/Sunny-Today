package xyz.sunnytoday.service.impl;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.service.face.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailServiceImpl implements MailService {
    @Override
    public void postJoinVerificationMail(String secretKey, String mailAddress) throws MessagingException {
        String stringBuilder = "<div style=\"width: 400px; margin: auto; height: 400px; border-radius: 4px; background-color: #d4e1fc; box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2); display: flex; flex-direction: column; justify-content: center;\">" +
                "<div style=\"background-color: #ffffff; width: 380px; height: 380px; margin: auto; border-radius: 4px; \">" +
                "<h3 style=\"color: #aaaaff; text-align: center\">JOIN! - 오늘도 맑음</h3>" +
                "<p style=\"color: #696969; text-align: center; font-weight: bold; padding: 10px;\">오늘도 맑음 회원가입 인증메일 입니다." +
                "<br><br><span style=\"color: #ffa7a7\">본인이 아닌경우 절대 아래 링크를 클릭하지 마시고 메일을 삭제해주세요!</span>" +
                "<br><br><br><span style=\"text-decoration: underline\">아래 링크를 클릭하여 가입을 마무리 할 수 있습니다.</span>" +
                "</p><a href=\"" +
                AppConfig.getAppKeyRepository().getKey("domain", "host") +
                "/verification?join=" +
                secretKey +
                "\" style=\"display: block; margin: auto; border-radius: 50%; background-color: #97b8ff; border: 2px solid #e0f5ff; width: 100px; height: 100px; line-height: 100px; color: white; text-align: center; font-weight: bold; user-select: none; cursor: pointer; text-decoration: none\">CLICK!</a>" +
                "</div></div>";

        sendMail(mailAddress, stringBuilder);
    }

    private void sendMail(String mailAddress, String content) throws MessagingException {
        String mail_from = AppConfig.getAppKeyRepository().getKey("naver", "id");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust","smtp.naver.com");
        props.put("mail.smtp.ssl.protocols","TLSv1.2");


        Authenticator auth = new SMTPAuthenticator();

        Session sess = Session.getDefaultInstance(props, auth);

        MimeMessage msg = new MimeMessage(sess);

        msg.setFrom(new InternetAddress(mail_from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailAddress));
        msg.setSubject("오늘도 맑음 회원가입 - 이메일을 인증해주세요!", "UTF-8");
        msg.setContent(content, "text/html; charset=UTF-8");
        msg.setHeader("Content-type", "text/html; charset=UTF-8");
        Transport.send(msg);
    }


    private static class SMTPAuthenticator extends Authenticator {
        public SMTPAuthenticator() {
            super();
        }

        public PasswordAuthentication getPasswordAuthentication() {
            String username = AppConfig.getAppKeyRepository().getKey("naver", "id");
            String password = AppConfig.getAppKeyRepository().getKey("naver", "password");
            return new PasswordAuthentication(username, password);
        }
    }
}
