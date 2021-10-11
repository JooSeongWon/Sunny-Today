package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dto.Member;

public class MypageDaoImpl implements MypageDao {
	
	@Override
	public Member selectMemberById(Connection conn, String loginUserId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
			+ "SELECT NICK, EMAIL, PHONE, BIRTH "
			+ "PICTURE_NO, BIRTH_OPEN, PHONE_OPEN"
			+ "FROM MEMBER"
			+ " WHERE id = ?";
		
		Member result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, loginUserId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = new Member();
				
				result.setUserid(rs.getString("userid"));
				result.setNick(rs.getString("nick"));
				result.setPhone(rs.getString("phone"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));
				result.setPictureno(rs.getInt("picture_no"));
				result.setPhone_open(rs.getString("phone_open"));
				result.setBirth_open(rs.getString("birth_open"));
				
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
	public int nickCheck(Connection conn, String nick) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = -1; //에러
		
		String sql = ""
			+ "SELECT COUNT(*) FROM MEMBER"
			+ " WHERE NICK = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, nick);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				//-1 에러
				// 0 중복없음
				// 1 중복있음
				result = rs.getInt(1);
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
	public int selectPhoneOpen(Connection conn, String phone, String loginUserId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = ""
			+ "UPDATE MEMBER SET PHONE_OPEN = '?' "
			+ " WHERE ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, phone);
			ps.setString(2, loginUserId);
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
}
