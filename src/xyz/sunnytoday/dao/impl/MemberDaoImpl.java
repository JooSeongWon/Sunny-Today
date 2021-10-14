package xyz.sunnytoday.dao.impl;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dto.Member;

import java.sql.*;

public class MemberDaoImpl implements MemberDao {
    @Override
    public Member selectByUserNoOrNull(Connection connection, int userNo) throws SQLException {
        String sql = "select * from MEMBER where USER_NO = ?";
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
        String sql = "select * from MEMBER where ID = ?";
        ResultSet resultSet = null;

        Member member = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
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
                " values (MEMBER_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, member.getPhone());
            preparedStatement.setString(2, member.getGender());
            preparedStatement.setDate(3, new Date(member.getBirth().getTime()));
            preparedStatement.setString(4, member.getNick());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getSalt());
            preparedStatement.setString(7, member.getUserpw());
            preparedStatement.setString(8, member.getUserid());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
        }
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

        return member;
    }
}
