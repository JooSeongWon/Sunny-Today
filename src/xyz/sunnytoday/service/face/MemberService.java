package xyz.sunnytoday.service.face;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

/**
 * 회원 관련 로직 수행 service
 */
public interface MemberService {

    /**
     * userNo를 통해 회원을 조회합니다.
     * 해당메서드는 패스워드와 salt를 반환하지 않습니다.
     * @param userNo 검색할 userNo
     * @return 찾은 회원 객체
     */
    Member getMemberByUserNoOrNull(int userNo);

    /**
     * RSA 암호화된 요청 정보를 토대로 로그인을 시도하고 결과를 ResponseMessage 객체로 반환합니다.
     * @param request 로그인 요청정보
     * @return 결과 {result : boolean, msg : string}
     */
    ResponseMessage login(HttpServletRequest request);


    /**
     * 로그인 api 콜백을 토대로 로그인을 시도하고 결과를 ResponseMessage 객체로 반환합니다.
     * @param request 로그인 요청정보
     * @return 결과 {result : boolean, msg : string} 미가입자는 msg = '미가입'
     */
    ResponseMessage loginSocial(HttpServletRequest request, int socialType);

    /**
     * userId를 통해 회원을 조회합니다.
     * 해당메서드는 패스워드와 salt를 반환하지 않습니다.
     * @param userId 검색할 userId
     * @return 찾은 회원 객체
     */
    Member getMemberByUserIdOrNull(String userId);


    /**
     * 회원가입 AJAX 요청 처리
     * @param params 요청 파라미터맵
     * @return 결과 메세지
     */
    ResponseMessage processUserRequest(Map<String, String[]> params, HttpServletRequest request);

    /**
     * 회원등록
     * 이미 검증이 끝난 멤버를 DB에 등록한다.
     * @param member 회원등록할 멤버 객체
     */
    void join(Member member) throws SQLException;
}
