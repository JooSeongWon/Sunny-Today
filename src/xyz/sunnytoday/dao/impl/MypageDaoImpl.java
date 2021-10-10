package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dto.Member;

public class MypageDaoImpl implements MypageDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public Member selectMemberById(Connection conn, String loginUserId) {
		
		/*
		 * String sql = ""; // sql += "SELECT FROM MEMBER"; sql += " WHERE id = ?";
		 * 
		 * Member result = null;
		 * 
		 * try { ps = conn.prepareStatement(sql);
		 * 
		 * ps.setString(1, loginUserId);
		 * 
		 * rs = ps.executeQuery();
		 * 
		 * while(rs.next()) { result = new Member();
		 * 
		 * result.setId(rs.getNString("id")); result.set }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * return result;
		 */
		
		return null;
	}
}
