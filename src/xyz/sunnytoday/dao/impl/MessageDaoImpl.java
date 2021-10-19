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
		sql += "			, content, post_date";
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
				message.setRnum(rs.getInt("rnum")); 

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
	public List<Message> selectAllToOther(Connection connection, Paging paging, int userNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, M.* FROM (";
		sql += "		SELECT";
		sql += "			message_no, title, too, fromm";
		sql += "			, content, post_date";
		sql += "		FROM message";
		sql += "		WHERE fromm = ?";
		sql += "		ORDER BY message_no DESC";
		sql += "	) M";
		sql += " ) mm";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Message> sendMessageList = new ArrayList<>();
		
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
				message.setRnum(rs.getInt("rnum")); 

				//리스트에 결과값 저장
				sendMessageList.add(message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return sendMessageList;
		
	}
	
	@Override 
	public int selectCntAllToMe(Connection connection, int userNo) { 
		//나한테 온거
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
	public int selectCntAllToOther(Connection connection, int userNo) {
		//내가 보낸거
		String sql = "select count(*) from message where fromm = ?";
		
		int cnt1 = 0;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt1 = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return cnt1;	
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
		String sql = "select MESSAGE.*, M.NICK FROM_NICK, M2.NICK TO_NICK from MESSAGE inner join MEMBER M on M.USER_NO = MESSAGE.FROMM inner join MEMBER M2 on M2.USER_NO = MESSAGE.TOO where MESSAGE_NO = ?";
		
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
				viewMessage.setFromNick(rs.getString("from_nick"));
				viewMessage.setTooNick(rs.getString("to_nick"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return viewMessage;
	}

	@Override
	public int searchCnt(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	      System.out.println("searchCnt called");

	      String sql = "";
	      sql += "SELECT count(*) ";
	      sql += " FROM message";
	      
	      int res = 0;
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         
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
	public int deleteMessage(Connection conn, Message message) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		System.out.println("deleteReport called");
		String sql = "";
		sql += "DELETE FROM message";
		sql += " WHERE message_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, message.getMessage_no());
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
	public Object selectNickByUserno(Connection connection, Message viewMessage) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT fromm FROM message";
		sql += " WHERE user_no = ?";
				
		//결과 저장할 String 변수
		String fromnick = null;
				
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewMessage.getToo()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
					
			//조회 결과 처리
			while(rs.next()) {
				fromnick = rs.getString("fromNick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		//최종 결과 반환
		return fromnick;
	}





}
