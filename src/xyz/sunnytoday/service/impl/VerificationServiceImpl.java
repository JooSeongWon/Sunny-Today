package xyz.sunnytoday.service.impl;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.face.VerificationService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class VerificationServiceImpl implements VerificationService {
    private final MemberService memberService = new MemberServiceImpl();


    @Override
    public boolean verifyEmailForMemberJoin(HttpServletRequest request) {
        String secretKey = request.getParameter("join");
        final Member member = AppConfig.getTemporaryMemberRepo().getMemberOrNull(secretKey);

        if (member == null) { //없거나 만료된 인증세션
            return false;
        }

        try {
            memberService.join(member);
        } catch (SQLException e) {
            System.out.println("[ERROR] 회원가입, SQLException! 가입을 무효 처리합니다.");
            return false;
        } finally {
            AppConfig.getTemporaryMemberRepo().deleteMember(secretKey);
        }

        return true;
    }
}
