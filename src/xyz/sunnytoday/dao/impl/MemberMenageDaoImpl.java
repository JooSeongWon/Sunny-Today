package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberMenageDao;
import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Report;
import xyz.sunnytoday.util.Paging;

public class MemberMenageDaoImpl implements MemberMenageDao{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int searchCnt(Connection conn, Member param, String location) {
		System.out.println("searchCnt called");
		System.out.println("loction : " + location );
		String sql = "";
		sql += "SELECT count(*) ";
		if(location == "member") { 
			sql += " FROM member";
//			if(!"".equals(param.getUserid()) || !"".equals(param.getNick())) {
//				sql += " WHERE";
//			}
			
		}else if(location == "question") {
			sql += " FROM private_question pq";
//			if(!"".equals(param.getUserid()) || !"".equals(param.getNick())) {
//				sql += " pq, member m";
//				sql += " WHERE pq.user_no = m.user_no AND";
//			}
			
		}else if(location == "purnish") {
			sql += " FROM ban";
//			if(!"".equals(param.getUserid()) || !"".equals(param.getNick())) {	
//				sql += " b, member m";
//				sql += " WHERE b.user_no = m.user_no AND";
//			}
		}else if(location == "report") {
			sql += " FROM user_report";
//			if(!"".equals(param.getUserid()) || !"".equals(param.getNick())) {	
//				sql += " ur, member m";
//				sql += " WHERE ur.user_no = m.user_no AND";
//			}
		}
		
//		if(param.getNick() != null && !"".equals(param.getNick())) {
//			sql += " m.nick LIKE ?";
//		}else if(param.getUserid() != null && !"".equals(param.getUserid())) {
//			sql += " m.id LIKE ?";
//		}
		
		int res = 0;
		
		try {
//			int paramIdx = 1;
			ps = conn.prepareStatement(sql);

//			if(param.getUserid() != null && !"".equals(param.getUserid())) {
//				ps.setString(paramIdx++, "%" + param.getUserid() +"%");
//			}else if(param.getNick() != null && !"".equals(param.getNick())) {
//				ps.setString(paramIdx++, "%" + param.getNick() +"%");
//			}

			rs = ps.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);		
		}

		return res;
	}	
	
	@Override
	public int searchReportCnt(Connection conn, Member param1, Report param2) {
		System.out.println("searchReportCnt called");
		String sql = "";
		sql += "SELECT count(*) ";
		sql += " FROM user_report ur, member m";
		sql += " WHERE m.user_no = ur.user_no";
		if(param1.getNick() != null && !"".equals(param1.getNick())) {
			sql += " AND m.nick LIKE ?";
		}else if(param1.getUserid() != null && !"".equals(param1.getUserid())) {
			sql += " AND m.id LIKE ?";
		}
		if(param2.getReport_type() != null && !"".equals(param2.getReport_type())) {
			sql += " AND ur.report_type LIKE ?";
		}
		int res = 0;
		
		try {
			int paramIdx = 1;
			ps = conn.prepareStatement(sql);

			if(param1.getUserid() != null && !"".equals(param1.getUserid())) {
				ps.setString(paramIdx++, "%" + param1.getUserid() +"%");
			}else if(param1.getNick() != null && !"".equals(param1.getNick())) {
				ps.setString(paramIdx++, "%" + param1.getNick() +"%");
			}
			if(param2.getReport_type() != null && !"".equals(param2.getReport_type())) {
				ps.setNString(paramIdx, "%" + param2.getReport_type() + "%");
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);		
		}

		return res;	
	}

	@Override
	public List<Member> getMemberList(Connection conn, Paging paging, Member param) {
		System.out.println("getMemberList");
		//방문횟수 제외
		String sql = "";
		sql += "SELECT * FROM(";
		sql += " SELECT rownum rnum, B.* FROM(";
		sql += " 	SELECT user_no, id, nick, email, create_date"; 
		sql += "	FROM member";
		if(param.getUserid() != null && !"".equals(param.getUserid())) {
			sql +=	" WHERE id LIKE ?";
		}else if(param.getNick() != null && !"".equals(param.getNick())) {
			sql +=	" WHERE nick LIKE ?";
		}
		sql += "	ORDER BY user_no DESC";
		sql += " )B ";
		sql += " ) MEMBER_BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<Member> list = new ArrayList<>();
		try {
			int paramIdx = 1;
			
			ps = conn.prepareStatement(sql);
			if(param.getUserid() != null && !"".equals(param.getUserid())) {
				ps.setString(paramIdx++, "%" + param.getUserid() +"%");
			}else if(param.getNick() != null && !"".equals(param.getNick())) {
				ps.setString(paramIdx++, "%" + param.getNick() +"%");
			}
			ps.setInt(paramIdx++, paging.getStartNo());
			ps.setInt(paramIdx++, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				list.add(member);
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

	@Override
	public List<Map<String, Object>> searchPurnishList(Member param, Paging paging, Connection conn) {
		System.out.println("searchPurnishList called");
		String sql = "";
		sql += "SELECT * FROM(";
		sql +=    " SELECT rownum rnum, R.* FROM (";
		sql +=        " SELECT b.ban_no, m.id, m.nick, m.phone, m.email, b.ban_type,b.ban_date ,b.expiry_date, b.user_no";
		sql +=        " FROM ban b, member m";
		sql +=        " WHERE b.user_no = m.user_no";
		if(param.getUserid() != null && !"".equals(param.getUserid())) {
			sql +=	" WHERE id LIKE ?";
		}else if(param.getNick() != null && !"".equals(param.getNick())) {
			sql +=	" WHERE nick LIKE ?";
		}
		sql +=		  " ORDER BY ban_no DESC";
		sql +=    " ) R";
		sql += " ) ban_board";
		sql += " WHERE rnum BETWEEN ? AND ?";
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> map = null;
		
		try {
			int paramIdx = 1;
			ps = conn.prepareStatement(sql);
			if(param.getUserid() != null && !"".equals(param.getUserid())) {
				ps.setString(paramIdx++, "%" + param.getUserid() +"%");
			}else if(param.getNick() != null && !"".equals(param.getNick())) {
				ps.setString(paramIdx++, "%" + param.getNick() +"%");
			}
			ps.setInt(paramIdx++, paging.getStartNo());
			ps.setInt(paramIdx++, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()) {
				map = new HashMap<>();
				
				Member member = new Member();
				Ban ban = new Ban();
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				ban.setBan_no(rs.getInt("ban_no"));
				ban.setBan_type(rs.getString("ban_type"));
				ban.setExpiry_date(rs.getDate("expiry_date"));
				ban.setUser_no(rs.getInt("user_no"));
				
				map.put("m", member);
				map.put("b", ban);
				
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public int deletePurnish(Connection conn, Ban param) {
		System.out.println("deleteReport called");
		String sql = "";
		sql += "DELETE FROM ban";
		sql += " WHERE ban_no = ?";
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, param.getBan_no());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public List<Map<String, Object>> getPurnishDetailList(HttpServletRequest req, Connection conn) {
		System.out.println("getPurnishDetailList called");
		String sql = "";
		sql += "SELECT b.ban_no, m.id, m.create_date, m.nick, b.reason, b.ban_date, b.expiry_date";
		sql +=" FROM ban b, member m";
		sql += " WHERE b.user_no = m.user_no and b.ban_no = ?";
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> map = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("ban_no")));
			rs = ps.executeQuery();
			while(rs.next()) {
				map = new HashMap<>();
				Member member = new Member();
				Ban ban = new Ban();
				member.setUserid(rs.getString("id"));
				member.setCreate_date(rs.getDate("create_date"));
				member.setNick(rs.getString("nick"));
				ban.setBan_no(rs.getInt("ban_no"));
				ban.setReason(rs.getString("reason"));
				ban.setBan_date(rs.getDate("ban_date"));
				ban.setExpiry_date(rs.getDate("expiry_date"));
				map.put("m", member);
				map.put("b", ban);
				list.add(map);
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
}
