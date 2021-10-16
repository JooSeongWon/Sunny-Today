package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.scripts.JD;
import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.MessageDao;
import xyz.sunnytoday.dto.Message;

public class MessageDaoImpl implements MessageDao {

	@Override
	public List<Message> selectAllToMe(Connection connection, Paging paging, int userNo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, M.* FROM (";
		sql += "		SELECT";
		sql += "			message_no, title, too, fromm";
		sql += "			, content, post_date, read";
		sql += "		FROM message";
		sql += "		WHERE too = ?";
		sql += "		ORDER BY message_no DESC";
		sql += "	) M";
		sql += " ) mm";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Message> messageList = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Message message = new Message();
				
				message.setMessage_no( rs.getInt("message_no") );
				message.setTitle( rs.getString("title") );
				message.setFromm( rs.getInt("fromm") );
				message.setToo( rs.getInt("too") );
				message.setPost_date( rs.getDate("post_date") );
				message.setRead( rs.getString("read") );
				message.setRnum(rs.getInt("rnum")); 
				//이거 목록에 보여줄떄 메세지번호 message_no 말고 rnum으로 보여주세요 개인마다 1번 쪽지는 다르니까요 넵

				//리스트에 결과값 저장
				messageList.add(message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return messageList;
		
	}
	
	@Override
	public int selectCntAllToMe(Connection connection, int userNo) {
		
		String sql = "select count(*) from message where too = ?";
		
		int cnt = 0;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return cnt;
	}

	@Override
	public int delete(Connection conn, Message message) {

		ResultSet rs = null;
		
		//다음 쪽지 번호 조회 쿼리
		String sql = "";
		sql += "DELETE message";
		sql += " WHERE message_no = ?";
		
		//DB 객체
		PreparedStatement ps = null;
		
		int res = -1;
		
		try {
			//DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, message.getMessage_no());
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public void insert(Connection connection, Message message) {
		String sql = "insert into message(MESSAGE_NO, TOO, FROMM, TITLE, CONTENT) values(MESSAGE_SEQ.nextval, ?, ?, ?, ?)";
		
		try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setInt(1, message.getToo());
			pstmt.setInt(2, message.getFromm());
			pstmt.setString(3, message.getTitle());
			pstmt.setString(4, message.getContent());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Message selectMessageByMessageno(Connection conn, Message message_no) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM message";
		sql += " WHERE message_no = ?";
		
		Message viewMessage = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, message_no.getMessage_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				viewMessage = new Message();
				
				viewMessage.setMessage_no( rs.getInt("message_no") );
				viewMessage.setToo( rs.getInt("too") );
				viewMessage.setFromm( rs.getInt("fromm") );
				viewMessage.setTitle( rs.getString("title") );
				viewMessage.setContent( rs.getString("content") );
				viewMessage.setPost_date( rs.getDate("post_date") );
				viewMessage.setRead( rs.getString("read") );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return viewMessage;
	}


}
