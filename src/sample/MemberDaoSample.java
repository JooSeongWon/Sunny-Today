package sample;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//샘플이어서 간단한 주석만 남길게요222
public interface MemberDaoSample {

    //모든유저정보 select
    List<Member> selectAll(Connection connection) throws SQLException;

    //유저no로 멤버객체 얻기
    Member selectByUserNoOrNull(Connection connection, int userNo) throws SQLException;

    //아이디로 객체얻기
    Member selectByUserIdOrNull(Connection connection, String userId) throws SQLException;

    //넣기
    void insert(Connection connection, Member member) throws SQLException;

    //프로필
    File selectProfileOrNull(Connection connection, int userNo);
}
