package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 조회 결과 객체
	
	@Override
	public int selectCntMemberByUseridUserpw(Connection connection, Member member) {

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE 1=1";
		sql += "	AND id = ?";
		sql += "	AND password = ?";
		
		//결과 저장할 변수
		int cnt = -1;
		
		try {
			ps = connection.prepareStatement(sql); //SQL 수행 객체
			
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public Member selectMemberByUserid(Connection connection, Member member) {

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE 1=1";
		sql += "	AND userid = ?";
		
		//조회결과를 저장할 객체
		Member result = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setString(1, member.getUserid());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new Member();
				
				result.setUserid( rs.getString("userid") );
				result.setUserpw( rs.getString("userpw") );
				result.setUserno( rs.getInt("userno") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int insert(Connection conn, Member member) {
		
		String sql = "";
		sql += "INSERT INTO member ( userid, userpw, usernick )";
		sql += " VALUES( ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getNick());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
