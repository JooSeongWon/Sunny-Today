package sample;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

//샘플이어서 간단한 주석만 남길게요
public interface MemberServiceSample {

    //user_no 를 통해 member dto 얻기
    Member getMemberByUserNoOrNull(int userNo);

    //userId와 pw를 통해 로그인 시도 성공시 true / 실패시 false
    boolean login(String userId, String userPw);

    //userId를 통해 member dto 얻기
    Member getMemberByUserIdOrNull(String userId);

    //회원가입 폼에서 회원가입 양식 전달
    void join(HttpServletRequest request) throws SQLException;
    
    //유저 프로필사진 객체 반환
    File getMemberProfileOrNull(int userNo);
}
