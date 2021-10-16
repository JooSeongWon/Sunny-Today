package sample;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.CipherUtil;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dao.impl.MemberDaoImpl;
import xyz.sunnytoday.dto.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

@WebServlet("/member/add")
public class MemberSampleCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = JDBCTemplate.getConnection()) {
            MemberDao memberDao = new MemberDaoImpl();
            Member member = new Member();




            String userId = ""; //사용하실 유저아이디 4~20글자 사이
            String userPw = ""; //사용하실 비밀번호 8~20글자 사이
            String nick = ""; //사용하실 닉네임 중복불가!








            member.setUserid(userId);
            member.setSalt(CipherUtil.getSalt());
            member.setUserpw(CipherUtil.encodeSha256(userPw, member.getSalt()));
            member.setNick(nick);
            member.setEmail(UUID.randomUUID().toString().substring(8) + "@" + UUID.randomUUID().toString().substring(4));
            member.setPhone("00000000000");
            member.setBirth(new Date());
            member.setGender("A");

            memberDao.insert(connection, member);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.doGet(req, resp);
    }
}
