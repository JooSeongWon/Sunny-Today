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
		sql += "select * FROM(";
		sql +=	    " select rownum rnum, R.* FROM(";
		sql +=	        " select pq.question_no, pq.title, m.id, pq.write_date,"; 
		sql +=	        " from private_question pq, member m";
		sql +=			" WHERE m.user_no = pq.user_no";
		sql +=			" AND m.id LIKE ?";
		sql +=	        " ORDER BY question_no DESC";
		sql +=	    " )R";
		sql +=	" )Question";
		sql += "WHERE rnum BETWEEN ? AND ?";
		List<Question> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + param.getUserid() + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()) {
				Question question = new Question();
				question.setQuestion_no(rs.getInt("question_no"));
				question.setTitle(rs.getString("title"));
				question.setId(rs.getString("id"));
				question.setWrite_date(rs.getDate("write_date"));
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
		System.out.println("searchUserNick called");
		String sql = "";
		sql += "select * FROM(";
		sql +=	    " select rownum rnum, R.* FROM(";
		sql +=	        " select question_no, title, write_date"; 
		sql +=	        " from private_question";
		sql +=			" WHERE m.user_no = pq.user_no";
		sql +=			" AND m.nick LIKE ?";
		sql +=	        " ORDER BY question_no DESC";
		sql +=	    " )R";
		sql +=	" )Question";
		sql += "WHERE rnum BETWEEN ? AND ?";
		
		List<Question> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setQuestion_no(rs.getInt("question_no"));
				question.setTitle(rs.getString("title"));
				question.setId(rs.getString("id"));
				question.setWrite_date(rs.getDate("write_date"));
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
	public List<Question> getQuestionList(Connection conn, Paging paging) {
		System.out.println("getQuestionList called");
		String sql = "";
		sql += "select * FROM(";
		sql +=	    " select rownum rnum, R.* FROM(";
		sql +=	        " select pq.question_no, pq.title, m.id, pq.write_date,"; 
		sql +=	        " FROM private_question pq, member m";
		sql +=			" WHERE m.user_no = pq.user_no";
		sql +=	        " ORDER BY question_no DESC";
		sql +=	    " ) R";
		sql +=	" ) Question";
		sql += "WHERE rnum BETWEEN ? AND ?";
		List<Question> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			while(rs.next()) {
				Question question = new Question();
				question.setQuestion_no(rs.getInt("question_no"));
				question.setTitle(rs.getString("title"));
				question.setId(rs.getString("id"));
				question.setWrite_date(rs.getDate("write_date"));
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
}
