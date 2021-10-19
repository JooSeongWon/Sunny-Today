package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.QuestionDao;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.Question;

public class QuestionDaoImpl implements QuestionDao {

	@Override
	public int selectCntAll(Connection conn) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT count(*) FROM private_question";
		
		//총 게시글 수
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
		
	}

	@Override
	public List<Map<String, Object>> selectListAll(Connection conn, Paging paging) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, question.* FROM ( ";
		sql += "        SELECT question_no, user_no, write_date, answer_date, title, content, answer, admin_no, id";
		sql += "        FROM private_question";
		sql += "        ORDER BY question_no DESC";
		sql += "	   ) question";
		sql += "	) questionlist";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		
		
		try {
			ps = conn.prepareStatement(sql);		
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Question question = new Question();
				
				question.setQuestion_no( rs.getInt("question_no") );
				question.setUser_no( rs.getInt("user_no") );
				question.setWrite_date( rs.getDate("write_date") );
				question.setAnswer_date( rs.getDate("answer_date") );
				question.setTitle( rs.getString("title") );
				question.setContent( rs.getString("content") );
				question.setAnswer( rs.getString("answer") );
				question.setAdmin_no( rs.getInt("admin_no") );
				question.setId( rs.getString("id") );
				
				map.put("question", question);
				map.put("nick", selectNickByUserno(conn, question) );
				
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return list;
	}
	
	@Override
	public String selectNickByUserno(Connection conn, int userno) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, userno); //조회할 no 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return usernick;
	}
	
	@Override
	public String selectIdByUserno(Connection conn, int userno) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += "SELECT id FROM member";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String userid = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, userno); //조회할 no 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				userid = rs.getString("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return userid;
	}
	
	@Override
	public int insert(Connection conn, Question question) {
		
		PreparedStatement ps = null;	
		
		String sql = "";
		sql += "INSERT INTO private_question(question_no, user_no, title, content, id)";
		sql += " VALUES ( private_question_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, question.getUser_no());
			ps.setString(2, question.getTitle());
			ps.setString(3, question.getContent());
			ps.setString(4, question.getId() );
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close( ps );
		}
		
		return res;
	}
	
	@Override
	public String selectNickByUserno(Connection conn, Question userno) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, userno.getUser_no()); //조회할 no 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return usernick;
	}
	
	@Override
	public Question selectQuestionByquestionno(Connection conn, Question questionNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		String sql = "";
		sql += "SELECT * FROM private_question";
		sql += " WHERE question_no = ?";
		
		Question questionDetail = null;
		
		try {
			ps = conn.prepareStatement(sql); 			
			ps.setInt(1, questionNo.getQuestion_no()); 			
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				
				questionDetail = new Question();
				
				questionDetail.setQuestion_no( rs.getInt("question_no") );
				questionDetail.setUser_no( rs.getInt("user_no") );
				questionDetail.setWrite_date( rs.getDate("write_date") );
				questionDetail.setAnswer_date( rs.getDate("answer_date") );
				questionDetail.setTitle( rs.getString("title") );
				questionDetail.setContent( rs.getString("content") );
				questionDetail.setAnswer( rs.getString("answer") );
				questionDetail.setAdmin_no( rs.getInt("admin_no") );
				questionDetail.setId( rs.getString("id") );		
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return questionDetail;
	}
	
	@Override
	public int update(Connection conn, Question question) {
		
		String sql = "";
		sql += "UPDATE private_question";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE question_no = ?";
		
		PreparedStatement ps = null; 
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getContent());
			ps.setInt(3, question.getQuestion_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteQuestionByQuestionno(Connection conn, Question questionNo) {

		PreparedStatement ps = null; 

		String sql = "";
		sql += "DELETE private_question";
		sql += " WHERE question_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, questionNo.getQuestion_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
	
}
