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
     *
     * @param connection jdbc connection 객체
     * @param userNo     조회할 userNo
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByUserNoOrNull(Connection connection, int userNo) throws SQLException;

    /**
     * userId로 회원을 조회하고 Member Dto 객체를 만듭니다.
     *
     * @param connection jdbc connection 객체
     * @param userId     조회할 userId
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByUserIdOrNull(Connection connection, String userId) throws SQLException;


    /**
     * nick으로 회원을 조회하고 Member Dto 객체를 만듭니다.
     *
     * @param connection jdbc connection 객체
     * @param nick       조회할 nick
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByNickOrNull(Connection connection, String nick) throws SQLException;

    /**
     * userId와 이메일이 일치하는 회원을 조회하고 Member Dto 객체를 만듭니다.
     *
     * @param connection jdbc connection 객체
     * @param userId     조회할 userId
     * @param email      조회할 이메일
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByEmailAndIdOrNull(Connection connection, String email, String userId) throws SQLException;

    /**
     * email로 회원을 조회하고 Member Dto 객체를 만듭니다.
     *
     * @param connection jdbc connection 객체
     * @param email      조회할 email
     * @return 찾은 Member Dto
     * @throws SQLException 트랜잭션 단위 처리시 select 도 같은 connection 으로 처리하기 위해 sqlException 을 떨굽니다.
     */
    Member selectByEmailOrNull(Connection connection, String email) throws SQLException;

    /**
     * member dto의 필드값을 이용해 새로운 member를 db에 등록합니다.
     *
     * @param connection jdbc connection 객체
     * @param member     insert할 dto 객체, userNo, phone, gender, birth, nick, email, salt, userId, userPw 값을 요구합니다.
     * @throws SQLException 트랜잭션 단위 처리를 위해 SqlException의 핸들링을 미룹니다.
     */
    void insert(Connection connection, Member member) throws SQLException;

    /**
     * userId 가 존재하는지 체크합니다.
     *
     * @param connection jdbc connection 객체
     * @param userId     체크할 userId
     * @return 검색된 데이터 수
     */
    int selectCntUserId(Connection connection, String userId) throws SQLException;

    /**
     * 닉네임이 존재하는지 체크합니다.
     *
     * @param connection jdbc connection 객체
     * @param nick       체크할 닉네임
     * @return 검색된 데이터 수
     */
    int selectCntUerNick(Connection connection, String nick) throws SQLException;

    /**
     * 이메일이 존재하는지 체크합니다.
     *
     * @param connection jdbc connection 객체
     * @param email      체크할 이메일
     * @return 검색된 데이터 수
     */
    int selectCntUserEmail(Connection connection, String email) throws SQLException;


    /**
     * 유저 패스워드를 변경합니다.
     *
     * @param member 변경할 유저의 uerno, 새로운 패스워드와 솔트를 담은 유저 dto
     */
    void updatePassword(Connection connection, Member member) throws SQLException;
}
