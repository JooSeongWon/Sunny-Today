package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.TemporaryMemberRepository;
import xyz.sunnytoday.common.util.CipherUtil;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dao.impl.MemberDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.ResponseMessage;
import xyz.sunnytoday.service.face.MailService;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.face.SocialLoginService;

@SuppressWarnings("RegExpDuplicateCharacterInClass")
public class MemberServiceImpl implements MemberService {
    public static final int INPUT_DATA_TYPE_ID = 0;
    public static final int INPUT_DATA_TYPE_EMAIL = 3;
    public static final int INPUT_DATA_TYPE_NICK = 4;

    private final MailService mailService = new MailServiceImpl();
    private final SocialLoginService socialLoginService = new SocialLoginServiceImpl();
    private final MemberDao memberDao = new MemberDaoImpl();
    private final TemporaryMemberRepository tempMemberRepo = AppConfig.getTemporaryMemberRepo();

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
    public ResponseMessage login(HttpServletRequest request) {

        //파라미터 복호화
        final Map<String, String[]> decryptParams = CipherUtil.getDecryptParams(request);

        if (decryptParams == null) { //복호화 실패
            return new ResponseMessage(false, "서버에서 데이터를 읽지 못했습니다 브라우저를 재시작 해보세요.");
        }

        //소셜로그인
        if (decryptParams.get("type")[0].equals("social")) {
            try {
                if (decryptParams.get("provider")[0].equals("naver")) {
                    return new ResponseMessage(true, socialLoginService.createNaverLoginUrl(request));
                } else { //구글..
                    return new ResponseMessage(true, socialLoginService.createGoogleLoginUrl(request));
                }
            } catch (Exception e) {
                System.out.println("[ERROR] MemberServiceImpl - 소셜로그인 요청 파싱도중 문제가 발생했습니다.");
                return new ResponseMessage(false, "서버 문제로 소셜로그인과 연결하지 못했습니다.");
            }
        }


        //아이디 패스워드
        String userId = decryptParams.get("userId")[0];
        String userPw = decryptParams.get("userPw")[0];

        //유효성 검사
        if (!isValidId(userId) || !isValidPw(userPw)) {
            return new ResponseMessage(false, "잘못된 입력 입니다.");
        }

        Member member;
        try (Connection connection = JDBCTemplate.getConnection()) {

            //존재하는 회원인지 조회
            member = memberDao.selectByUserIdOrNull(connection, userId);
            if (member == null) {
                //아이디 불일치 힌트 안주기위해 같은 메세지 출력
                return new ResponseMessage(false, "아이디와 패스워드가 일치하지 않습니다.");
            }

            //패스워드 일치 확인
            if (!CipherUtil.encodeSha256(userPw, member.getSalt()).equals(member.getUserpw())) {
                return new ResponseMessage(false, "아이디와 패스워드가 일치하지 않습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseMessage(false, "서버 문제로 로그인에 실패하였습니다 관리자에게 문의하세요.");
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("userno", member.getUserno());
        session.setAttribute("nick", member.getNick());
        session.setAttribute("admin", member.getAdmin());
        session.setAttribute("pictureThumbnail", member.getPictureThumbnail());
        session.setAttribute("hasPassword", true);

        return new ResponseMessage(true, "로그인 성공");
    }

    //요청처리
    @Override
    public ResponseMessage processUserRequest(Map<String, String[]> params, HttpServletRequest request) {
        //요청 확인
        try {
            Member member;
            String validStr;

            switch (params.get("reqType")[0]) {

                //중복확인
                case "checkDuplicate":
                    return checkDuplicate(Integer.parseInt(params.get("dataType")[0]), params.get("data")[0]);

                //일반 회원가입
                case "joinOriginMember":
                    member = new Member();
                    setMember(member, params, false);

                    //유효성 체크
                    validStr = checkValidData(member);
                    if (!(validStr == null)) {
                        return new ResponseMessage(false, validStr);
                    }

                    //최종 중복확인
                    ResponseMessage responseMessage = checkDuplicate(INPUT_DATA_TYPE_ID, member.getUserid());
                    if (!responseMessage.getResult()) {
                        return responseMessage;
                    }
                    responseMessage = checkDuplicate(INPUT_DATA_TYPE_NICK, member.getNick());
                    if (!responseMessage.getResult()) {
                        return responseMessage;
                    }
                    responseMessage = checkDuplicate(INPUT_DATA_TYPE_EMAIL, member.getEmail());
                    if (!responseMessage.getResult()) {
                        return responseMessage;
                    }


                    //임시 가입
                    member.setSalt(CipherUtil.getSalt());
                    member.setUserpw(CipherUtil.encodeSha256(member.getUserpw(), member.getSalt()));
                    String secretKey = AppConfig.getTemporaryMemberRepo().addMember(member);

                    //인증링크 발송
                    mailService.postJoinVerificationMail(secretKey, member.getEmail());
                    return new ResponseMessage(true, "인증메일 발송");

                //소셜 회원가입
                case "joinSocialMember":
                    HttpSession session = request.getSession();
                    if (session.getAttribute("socialEmail") == null) return new ResponseMessage(false, "잘못된 접근입니다.");

                    member = new Member();
                    setMember(member, params, true);
                    member.setEmail(session.getAttribute("socialEmail").toString());
                    member.setUserid("S-");

                    //유효성 체크
                    validStr = checkValidData(member);
                    if (!(validStr == null)) {
                        return new ResponseMessage(false, validStr);
                    }

                    //최종 중복확인
                    responseMessage = checkDuplicate(INPUT_DATA_TYPE_NICK, member.getNick());
                    if (!responseMessage.getResult()) {
                        return responseMessage;
                    }

                    //즉시 가입처리
                    try (Connection connection = JDBCTemplate.getConnection()) {
                        memberDao.insert(connection, member);
                    }

                    //가입성공
                    final Object keyPair = session.getAttribute("rsaKeyPair");
                    final Object publicKey = session.getAttribute("publicKey");

                    session.invalidate();
                    session = request.getSession();

                    //로그인성공
                    session.setAttribute("rsaKeyPair", keyPair);
                    session.setAttribute("publicKey", publicKey);
                    session.setAttribute("userno", member.getUserno());
                    session.setAttribute("nick", member.getNick());
                    session.setAttribute("admin", member.getAdmin());
                    session.setAttribute("pictureThumbnail", member.getPictureThumbnail());
                    session.setAttribute("hasPassword", member.getUserpw() != null);

                    return new ResponseMessage(true, "가입 및 로그인 성공");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] 회원가입 ajax 요청처리 오류");
        }

        return new ResponseMessage(false, "알수없는 요청입니다.");
    }


    @Override
    public void join(Member member) throws SQLException {
        //인증완료 일반멤버 가입
        try (Connection connection = JDBCTemplate.getConnection()) {
            memberDao.insert(connection, member);
        }
    }


    @Override
    public ResponseMessage loginSocial(HttpServletRequest request, int socialType) {
        String email = socialLoginService.getEmailOrNull(request, socialType);

        if (email == null) return new ResponseMessage(false, "소셜로그인 오류");

        Member member;

        try (Connection connection = JDBCTemplate.getConnection()) {
            member = memberDao.selectByEmailOrNull(connection, email);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseMessage(false, "소셜회원 오류");
        }

        //미가입자
        if (member == null) {
            request.setAttribute("social", socialType);
            request.getSession().setAttribute("socialEmail", email);
            return new ResponseMessage(false, "미가입");
        }

        //로그인성공
        HttpSession session = request.getSession();
        session.setAttribute("userno", member.getUserno());
        session.setAttribute("nick", member.getNick());
        session.setAttribute("admin", member.getAdmin());
        session.setAttribute("pictureThumbnail", member.getPictureThumbnail());
        session.setAttribute("hasPassword", member.getUserpw() != null);

        return new ResponseMessage(true, "로그인 성공");
    }

    //입력 데이터를 멤버 객체에 넣기
    private void setMember(Member member, Map<String, String[]> params, boolean isSocial) throws ParseException {
        if (!isSocial) {
            member.setUserid(params.get("userId")[0]);
            member.setEmail(params.get("email")[0]);
            member.setUserpw(params.get("userPw")[0]);
        }
        member.setNick(params.get("nick")[0]);
        member.setPhone(params.get("phone")[0]);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        member.setBirth(simpleDateFormat.parse(params.get("birth")[0]));
        member.setGender(params.get("gender")[0]);
    }

    //중복체크
    private ResponseMessage checkDuplicate(int dataType, String data) {

        try (Connection connection = JDBCTemplate.getConnection()) {
            switch (dataType) {
                case INPUT_DATA_TYPE_ID:
                    if (memberDao.selectCntUserId(connection, data) > 0 ||
                            tempMemberRepo.isIdDuplicated(data)) {
                        return new ResponseMessage(false, "이미 사용중인 아이디입니다.");
                    }
                    return new ResponseMessage(true, "사용 가능한 아이디입니다.");

                case INPUT_DATA_TYPE_NICK:
                    if (memberDao.selectCntUerNick(connection, data) > 0 ||
                            tempMemberRepo.isNickDuplicated(data)) {
                        return new ResponseMessage(false, "이미 사용중인 닉네임입니다.");
                    }
                    return new ResponseMessage(true, "사용 가능한 닉네임입니다.");

                case INPUT_DATA_TYPE_EMAIL:
                    if (memberDao.selectCntUserEmail(connection, data) > 0 ||
                            tempMemberRepo.isEmailDuplicated(data)) {
                        return new ResponseMessage(false, "이미 사용중인 이메일입니다.");
                    }
                    return new ResponseMessage(true, "사용 가능한 이메일입니다.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]중복처리 SQL 오류.");
            return new ResponseMessage(false, "서버문제로 요청을 처리하지 못했습니다.");
        }

        return new ResponseMessage(false, "알수없는 요청입니다.");
    }

    //null 반환시 이상없음, string 으로 문제사항 리턴
    private String checkValidData(Member member) {
        String idRegex = "^[a-z0-9]{4,20}$";
        String pwRegex = "^(?=.*[a-zA-Z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&]).{8,20}$";
        String emailRegex = "^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[0-9a-z]([-_.]?[0-9a-z])*\\.[a-z]{2,3}$";
        String phoneRegex = "^\\d{10,11}$";
        String nickRegex = "^[a-zA-Z0-9가-힣]{2,12}$";

        if (!member.isSocialMember() && !Pattern.matches(idRegex, member.getUserid())) {
            return "유효하지 않은 아이디 입니다.";
        }
        if (!member.isSocialMember() && !Pattern.matches(pwRegex, member.getUserpw())) {
            return "유효하지 않은 비밀번호 입니다.";
        }
        if (!member.isSocialMember() && !Pattern.matches(emailRegex, member.getEmail())) {
            return "유효하지 않은 이메일 입니다.";
        }
        if (!Pattern.matches(nickRegex, member.getNick())) {
            return "유효하지 않은 닉네임 입니다.";
        }
        if (!Pattern.matches(phoneRegex, member.getPhone())) {
            return "유효하지 않은 핸드폰번호 입니다.";
        }

        return null;
    }

    private boolean isValidId(String userId) {
        //소문자 + 숫자 4~20자리
        String idRegex = "^[a-z0-9]{4,20}$";

        return Pattern.matches(idRegex, userId);
    }

    private boolean isValidPw(String userPw) {
        //숫자 + 영어 소문자, 대문자 + 특수문자 8~20자리
        String pwRegex = "^(?=.*[a-zA-Z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&]).{8,20}$";

        return Pattern.matches(pwRegex, userPw);
    }
}
