package xyz.sunnytoday.dao.impl;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dto.Member;

import java.sql.*;

public class MemberDaoImpl implements MemberDao {
    @Override
    public Member selectByUserNoOrNull(Connection connection, int userNo) throws SQLException {
        String sql = "select MEMBER.*, F.URL, F.THUMBNAIL_URL  from MEMBER" +
                " left outer join \"FILE\" F on MEMBER.PICTURE_NO = F.FILE_NO" +
                " where MEMBER.USER_NO = ?";

        ResultSet resultSet = null;

        Member member = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userNo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                member = buildMember(resultSet);
            }

        } finally {
            JDBCTemplate.close(resultSet);
        }

        return member;
    }

    @Override
    public Member selectByUserIdOrNull(Connection connection, String userId) throws SQLException {
        String sql = "ID = ?";
        return selectMemberByStringVal(connection, userId, sql);
    }

    @Override
    public Member selectByEmailOrNull(Connection connection, String email) throws SQLException {
        String sql = "EMAIL = ?";
        return selectMemberByStringVal(connection, email, sql);
    }

    @Override
    public Member selectByNickOrNull(Connection connection, String nick) throws SQLException {
        String sql = "NICK = ?";
        return selectMemberByStringVal(connection, nick, sql);
    }

    @Override
    public Member selectByEmailAndIdOrNull(Connection connection, String email, String userId) throws SQLException {
        String sql = "select MEMBER.*, F.URL, F.THUMBNAIL_URL  from MEMBER" +
                " left outer join \"FILE\" F on MEMBER.PICTURE_NO = F.FILE_NO" +
                " where MEMBER.ID = ? and MEMBER.EMAIL = ?";

        ResultSet resultSet = null;

        Member member = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                member = buildMember(resultSet);
            }

        } finally {
            JDBCTemplate.close(resultSet);
        }

        return member;
    }

    @Override
    public void insert(Connection connection, Member member) throws SQLException {
        String sql = "insert into MEMBER(user_no, phone, gender, birth, nick, email, salt, password, id)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            int seq = getNextMemberSeq(connection);
            if (seq == 0) {
                System.out.println("[ERROR] 멤버시퀀스를 생성하지 못했습니다.");
                throw new SQLException();
            }

            preparedStatement.setInt(1, seq);
            preparedStatement.setString(2, member.getPhone());
            preparedStatement.setString(3, member.getGender());
            preparedStatement.setDate(4, new Date(member.getBirth().getTime()));
            preparedStatement.setString(5, member.getNick());
            preparedStatement.setString(6, member.getEmail());
            preparedStatement.setString(7, member.getSalt());
            preparedStatement.setString(8, member.getUserpw());
            if (member.isSocialMember()) {
                preparedStatement.setString(9, member.getUserid() + seq);
            } else {
                preparedStatement.setString(9, member.getUserid());
            }

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
        }
    }

    @Override
    public int selectCntUserId(Connection connection, String userId) throws SQLException {
        String sql = "ID = ?";
        return selectCntWhen(connection, sql, userId);
    }

    @Override
    public int selectCntUerNick(Connection connection, String nick) throws SQLException {
        String sql = "NICK = ?";
        return selectCntWhen(connection, sql, nick);
    }

    @Override
    public int selectCntUserEmail(Connection connection, String email) throws SQLException {
        String sql = "EMAIL = ?";
        return selectCntWhen(connection, sql, email);
    }

    private int getNextMemberSeq(Connection connection) throws SQLException {
        String sql = "select MEMBER_SEQ.nextval seq from DUAL";

        int seq = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                seq = resultSet.getInt("seq");
            }

        }
        return seq;
    }

    private Member buildMember(ResultSet resultSet) throws SQLException {
        Member member = new Member();
        member.setUserno(resultSet.getInt("user_no"));
        member.setUserid(resultSet.getString("id"));
        member.setUserpw(resultSet.getString("password"));
        member.setSalt(resultSet.getString("salt"));
        member.setCreate_date(resultSet.getDate("create_date"));
        member.setEmail(resultSet.getString("email"));
        member.setNick(resultSet.getString("nick"));
        member.setBirth(resultSet.getDate("birth"));
        member.setGender(resultSet.getString("gender"));
        member.setPhone(resultSet.getString("phone"));
        member.setAdmin(resultSet.getString("admin"));
        member.setPictureno(resultSet.getInt("picture_no"));
        member.setBirth_open(resultSet.getString("birth_open"));
        member.setPhone_open(resultSet.getString("phone_open"));
        member.setPicture(resultSet.getString("url"));
        member.setPictureThumbnail(resultSet.getString("thumbnail_url"));

        return member;
    }

    private int selectCntWhen(Connection connection, String whereContext, String data) throws SQLException {
        String sql = "select count(*) cnt from MEMBER where " + whereContext;
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("cnt");
            }
        } finally {
            JDBCTemplate.close(resultSet);
        }

        return 0;
    }

    private Member selectMemberByStringVal(Connection connection, String data, String whereContext) throws SQLException {
        String sql = "select MEMBER.*, F.URL, F.THUMBNAIL_URL  from MEMBER" +
                " left outer join \"FILE\" F on MEMBER.PICTURE_NO = F.FILE_NO where " + whereContext;

        ResultSet resultSet = null;

        Member member = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                member = buildMember(resultSet);
            }

        } finally {
            JDBCTemplate.close(resultSet);
        }

        return member;
    }
}
