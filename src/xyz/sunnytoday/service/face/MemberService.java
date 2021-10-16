package xyz.sunnytoday.service.face;

import xyz.sunnytoday.dto.Member;

import javax.servlet.http.HttpServletRequest;
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
     * RSA 암호화된 요청 정보를 토대로 로그인을 시도하고 결과를 Map 객체로 반환합니다.
     * @param request 로그인 요청정보
     * @return 결과 {result : boolean, msg : string}
     */
    Map<String, Object> login(HttpServletRequest request);

    /**
     * userId를 통해 회원을 조회합니다.
     * 해당메서드는 패스워드와 salt를 반환하지 않습니다.
     * @param userId 검색할 userId
     * @return 찾은 회원 객체
     */
    Member getMemberByUserIdOrNull(String userId);
}
