package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.QuestionMenageDao;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.util.Paging;

public class QuestionMenageDaoImpl implements QuestionMenageDao{
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public int selectIdCntAll(Connection conn, Member param) {
		System.out.println("selectIdCntAll called");
		String sql = "";
		sql += "SELECT count(*) FROM private_question";
		sql += " WHERE id LIKE ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, param.getUserid());
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
	public int selectNickCntAll(Connection conn, Member param) {
		System.out.println("selectIdCntAll called");
		String sql = "";
		sql += "SELECT count(*) FROM private_question";
		sql += " WHERE nick LIKE ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, param.getNick());
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
	public int selectCntAll(Connection conn) {
		System.out.println("selectIdCntAll called");
		String sql = "";
		sql += "SELECT count(*) FROM private_question";
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
	public List<Question> searchUserId(Member param, Paging paging, Connection conn) {
		
		System.out.println("searchUserId called");
		String sql = "";
		sql += "SELECT * FROM(";
		sql += " SELECT rownum rnum, B.* FROM(";
		sql += " 	SELECT user_no, id, nick, email, create_date"; 
		sql += "	FROM member";
		sql += "	WHERE id LIKE ?";
		sql += "	ORDER BY user_no DESC";
		sql += " )B ";
		sql += ") MEMBER_BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		List<Question> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + param.getUserid() + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()) {
				Question question = new Question();
				
				list.add(question);			
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
	public List<Question> searchUserNick(Member param, Paging paging, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Question> getMemberList(Connection conn, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
}
