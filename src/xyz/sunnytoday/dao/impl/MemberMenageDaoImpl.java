package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberMenageDao;
import xyz.sunnytoday.dto.Member;

public class MemberMenageDaoImpl implements MemberMenageDao{
	PreparedStatement ps =null;
	ResultSet rs = null;
	
	@Override
	public List<Member> getMemberList(Connection conn) {
		System.out.println("getMemberList");
		String sql = "";
		sql += "SELECT user_no, id, nick, email,";
//		sql += " (SELECT [회원의 방문횟수] FROM visitor),";
		sql += " create_date FROM member";
		List<Member> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				list.add(member);
				System.out.println(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		String sql ="";
		sql += "SELECT count(*) FROM member";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
