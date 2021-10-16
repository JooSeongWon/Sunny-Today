package xyz.sunnytoday.dao.face;

import xyz.sunnytoday.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Member 관련 DB 작업을 전담합니다.
 */
public interface MemberDao {
    /**
     * userNo로 회원을 조회하고 Member Dto 객체를 만듭니다.
     * @param connection jdbc connection 객체
     * @param userNo 조회할 userNo
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByUserNoOrNull(Connection connection, int userNo) throws SQLException;

    /**
     * userId로 회원을 조회하고 Member Dto 객체를 만듭니다. 
     * @param connection jdbc connection 객체
     * @param userId 조회할 userId
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByUserIdOrNull(Connection connection, String userId) throws SQLException;

    /**
     * member dto의 필드값을 이용해 새로운 member를 db에 등록합니다.
     * @param connection jdbc connection 객체
     * @param member insert할 dto 객체, userNo, phone, gender, birth, nick, email, salt, userId, userPw 값을 요구합니다.
     * @throws SQLException 트랜잭션 단위 처리를 위해 SqlException의 핸들링을 미룹니다.
     */
    void insert(Connection connection, Member member) throws SQLException;
}
