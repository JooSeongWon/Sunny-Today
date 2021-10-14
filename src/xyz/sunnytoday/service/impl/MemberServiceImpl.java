package xyz.sunnytoday.service.impl;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.CipherUtil;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dao.impl.MemberDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressWarnings("RegExpDuplicateCharacterInClass")
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao = new MemberDaoImpl();

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
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>(); //결과 맵

        //파라미터 복호화
        final Map<String, String[]> decryptParams = CipherUtil.getDecryptParams(request);
        resultMap.put("result", false);

        if (decryptParams == null) { //복호화 실패
            resultMap.put("msg", "요청 데이터에 이상이 발견되었습니다 새로고침을 시도해보세요.");
            return resultMap;
        }

        //아이디 패스워드
        String userId = decryptParams.get("userId")[0];
        String userPw = decryptParams.get("userPw")[0];

        //유효성 검사
        if (!isValidId(userId) || !isValidPw(userPw)) {
            resultMap.put("msg", "잘못된 입력 입니다.");
            return resultMap;
        }

        Member member;
        try (Connection connection = JDBCTemplate.getConnection()) {

            //존재하는 회원인지 조회
            member = memberDao.selectByUserIdOrNull(connection, userId);
            if (member == null) {
                //아이디 불일치 힌트 안주기위해 같은 메세지 출력
                resultMap.put("msg", "아이디와 패스워드가 일치하지 않습니다.");
                return resultMap;
            }

            //패스워드 일치 확인
            if (!CipherUtil.encodeSha256(userPw, member.getSalt()).equals(member.getUserpw())) {
                resultMap.put("msg", "아이디와 패스워드가 일치하지 않습니다.");
                return resultMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultMap.put("msg", "서버 문제로 로그인에 실패하였습니다 관리자에게 문의하세요.");
            return resultMap;
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("userno", member.getUserno());
        session.setAttribute("nick", member.getNick());

        resultMap.put("result", true);
        resultMap.put("msg", "로그인 성공");
        return resultMap;
    }

    private boolean isValidId(String userId) {
        //소문자 + 숫자 4~20자리
        String idRegex = "^[a-z0-9]{4,20}$";

        return Pattern.matches(idRegex, userId);
    }

    private boolean isValidPw(String userPw) {
        //숫자 + 영어 소문자, 대문자 + 특수문자 8~20자리
        String pwRegex = "^(?=.*[a-zA-z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&]).{8,20}$";

        return Pattern.matches(pwRegex, userPw);
    }
}
