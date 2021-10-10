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
import xyz.sunnytoday.util.Paging;

public class MemberMenageDaoImpl implements MemberMenageDao{
	PreparedStatement ps =null;
	ResultSet rs = null;
	
	@Override
	public List<Member> getMemberList(Connection conn, Paging paging) {
		System.out.println("getMemberList");
		//방문횟수 제외
		String sql = "";
		sql += "SELECT * FROM(";
		sql += " SELECT rownum rnum, B.* FROM(";
		sql += " 	SELECT user_no, id, nick, email, create_date"; 
		sql += "	FROM member";
		sql += "	ORDER BY user_no DESC";
		sql += " )B ";
		sql += ") MEMBER_BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Member> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
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
		String sql = "";
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

	@Override
	public Member selectUserDatail(Member param, Connection conn) {
		System.out.println("selectUserDatail called");
		String sql = "";
		sql += "SELECT id, gender, nick, birth, email FROM member";
		sql += " WHERE user_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, param.getUserno());
			rs = ps.executeQuery();
			while(rs.next()) {
				param.setUserid(rs.getString("id"));
				param.setGender(rs.getString("gender"));
				param.setNick(rs.getString("nick"));
				param.setBirth(rs.getDate("birth"));
				param.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return param;
	}

}
