package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MessageEventDao;
import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.util.Paging;

public class MessageEventDaoImpl implements MessageEventDao {
	
	@Override
	public int selectCntAll(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
			+ "SELECT count(*) FROM message_event";
		
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return count;
	}
	
	@Override
	public int selectCntEvent(Connection conn, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+ "SELECT count(*) FROM message_event"
				+ " WHERE title LIKE ? ";
				
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+search+"%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
	}
	
	@Override
	public List<MessageEvent> All(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = ""
	    		+"SELECT * FROM(" 
	    		+"    SELECT rownum rnum, B.* FROM("
	    		+"        SELECT MESSAGE_E_NO ,M.EVENT_NO ,TITLE ,CONTENT, e.name"  
	    		+"        FROM message_event M" 
	    		+"        INNER JOIN Event E"  
	    		+"        ON M.EVENT_NO = E.EVENT_NO"  
	    		+"        ORDER BY MESSAGE_E_NO DESC)B"  
	    		+"	)MESSAGE_BOARD "
	    		+"WHERE rnum BETWEEN ? AND ?";
	      
	      List<MessageEvent> list = new ArrayList<>();
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setInt(1, paging.getStartNo());
	         ps.setInt(2, paging.getEndNo());
	         rs = ps.executeQuery();
	         
	         while(rs.next()) {
	        	MessageEvent messageEvent = new MessageEvent();
	        	
	        	messageEvent.setMessage_e_no(rs.getInt("message_e_no"));
	        	messageEvent.setEvent_no(rs.getInt("event_no"));
	        	messageEvent.setTitle(rs.getString("title"));
	        	messageEvent.setContent(rs.getString("content"));
	        	messageEvent.setName(rs.getString("name"));
	        	
	            list.add(messageEvent);
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
	public List<MessageEvent> search(String param, Paging paging, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = ""
	    		+"SELECT * FROM(" 
	    		+"    SELECT rownum rnum, B.* FROM("
	    		+"        SELECT MESSAGE_E_NO ,M.EVENT_NO ,TITLE ,CONTENT, e.name"  
	    		+"        FROM message_event M" 
	    		+"        INNER JOIN Event E"  
	    		+"        ON M.EVENT_NO = E.EVENT_NO" 
	    		+"		  WHERE title LIKE ? "
	    		+"        ORDER BY MESSAGE_E_NO DESC)B"  
	    		+"	)MESSAGE_BOARD "
	    		+"WHERE rnum BETWEEN ? AND ?";
	      
	      List<MessageEvent> list = new ArrayList<>();
	      
	      System.out.println(param);
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, "%"+param+"%");
	         ps.setInt(2, paging.getStartNo());
	         ps.setInt(3, paging.getEndNo());
	         rs = ps.executeQuery();
	         
	         while(rs.next()) {
	        	MessageEvent messageEvent = new MessageEvent();
	        	
	        	messageEvent.setMessage_e_no(rs.getInt("message_e_no"));
	        	messageEvent.setEvent_no(rs.getInt("event_no"));
	        	messageEvent.setTitle(rs.getString("title"));
	        	messageEvent.setContent(rs.getString("content"));
	        	messageEvent.setName(rs.getString("name"));
	        	
	            list.add(messageEvent);
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
	public List<Event> selectEvent(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+"SELECT EVENT_NO, NAME FROM EVENT";
		
		List<Event> event = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Event e = new Event();
				
				e.setEvent_no(rs.getInt("event_no"));
				e.setName(rs.getString("name"));
				
				event.add(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return event;
	}
	
	@Override
	public int insertMessage(Connection conn, MessageEvent messageEvent) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = ""
				+ "INSERT INTO MESSAGE_EVENT "
				+ " (MESSAGE_E_NO ,EVENT_NO ,TITLE ,CONTENT)"
				+ " VALUES (Message_event_seq.nextval, ? , ?, ?)";

		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageEvent.getEvent_no());
			ps.setString(2, messageEvent.getTitle());
			ps.setString(3, messageEvent.getContent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
