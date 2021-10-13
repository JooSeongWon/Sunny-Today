package sample;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Encryption;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MemberServiceSampleImpl implements MemberServiceSample {
    private final MemberDaoSample memberDao = new MemberDaoSampleImpl();

    @Override
    public Member getMemberByUserNoOrNull(int userNo) {
        Member member = null;
        try (Connection connection = JDBCTemplate.getConnection()) {
            member = memberDao.selectByUserNoOrNull(connection, userNo);

            //보안 목적 패스워드와 salt는 service에서만 사용하며 controller로 전달하지 않는다.
            member.setUserpw(null);
            member.setSalt(null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return member;
    }

    @Override
    public boolean login(String userId, String userPw) {
        boolean isMatched = false;

        //유효성검사, 실제로는 더 생각해서 많이쓰세용
        if (userId == null || userPw == null || userId.contains("-")) {
            return false;
        }

        try (Connection connection = JDBCTemplate.getConnection()) {

            Member member = memberDao.selectByUserIdOrNull(connection, userId);
            if (member == null) {
                return false;
            }

            String inputPwEnc = Encryption.encodePassword(userPw, member.getSalt());
            if (inputPwEnc.equals(member.getUserpw())) {
                isMatched = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isMatched;
    }

    @Override
    public Member getMemberByUserIdOrNull(String userId) {
        Member member = null;
        try (Connection connection = JDBCTemplate.getConnection()) {
            member = memberDao.selectByUserIdOrNull(connection, userId);

            //보안 목적 패스워드와 salt는 service에서만 사용하며 controller로 전달하지 않는다.
            member.setUserpw(null);
            member.setSalt(null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return member;
    }

    @Override
    public void join(HttpServletRequest request) throws SQLException {
        Member member = new Member();

        member.setUserid(request.getParameter("userId"));
        member.setEmail(request.getParameter("email"));
        member.setNick(request.getParameter("nick"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            member.setBirth(dateFormat.parse(request.getParameter("birth")));
        } catch (ParseException e) {
            throw new SQLException();
        }

        member.setGender(request.getParameter("gender"));
        member.setPhone(request.getParameter("phone"));

        String salt = Encryption.getSalt();
        member.setSalt(salt);
        member.setUserpw(Encryption.encodePassword(request.getParameter("userPw"), salt));

        Connection connection = JDBCTemplate.getConnection();
        memberDao.insert(connection, member);
        JDBCTemplate.close(connection);
    }


    @Override
    public File getMemberProfileOrNull(int userNo) {
        return null;
    }
}
