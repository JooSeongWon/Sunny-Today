package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.AdminMemberDao;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public class AdminMemberDaoImpl implements AdminMemberDao {
	
	@Override
	public int selectCntAll(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
			+ "SELECT count(*) FROM member";
		
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
	public int selectCntAdmin(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+ "SELECT count(*) FROM member"
				+ " WHERE Admin = 'A' ";
				
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
	public int selectCntId(Connection conn, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+ "SELECT count(*) FROM member"
				+ " WHERE id LIKE ? ";
				
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%'+search+"%");
			
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
	public List<Member> All(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
	    
		String sql = ""
	    		+"SELECT * FROM("
	    		+ " SELECT rownum rnum, B.* FROM("
	    		+ "    SELECT user_no, id, nick, email, create_date, admin"
	      		+ "   FROM member"
	      		+ "   ORDER BY user_no DESC"
	      		+ " )B "
	      		+ ") MEMBER_BOARD"
	      		+ " WHERE rnum BETWEEN ? AND ?";
	      
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
	            member.setAdmin(rs.getString("admin"));
	            list.add(member);
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
	public List<Member> searchId(String param, Paging paging, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
	    
		String sql = ""
				+"SELECT * FROM("
				+ " SELECT rownum rnum, B.* FROM("
				+ "    SELECT user_no, id, nick, email, create_date, admin"
				+ "   FROM member"
				+ "   	WHERE id LIKE ?"
				+ "   ORDER BY user_no DESC"
				+ " )B "
				+ ") MEMBER_BOARD"
				+ " WHERE rnum BETWEEN ? AND ?";
	      
	      List<Member> list = new ArrayList<>();
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, '%'+param+"%");
	         ps.setInt(2, paging.getStartNo());
	         ps.setInt(3, paging.getEndNo());
	         rs = ps.executeQuery();
	         while(rs.next()) {
	            Member member = new Member();
	            member.setUserno(rs.getInt("user_no"));
	            member.setUserid(rs.getString("id"));
	            member.setNick(rs.getString("nick"));
	            member.setEmail(rs.getString("email"));
	            member.setCreate_date(rs.getDate("create_date"));
	            member.setAdmin(rs.getString("admin"));
	            list.add(member);
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
	public List<Member> selectAdmin(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
	    
		String sql = ""
	    		+"SELECT * FROM("
	    		+ " SELECT rownum rnum, B.* FROM("
	    		+ "    SELECT user_no, id, nick, email, create_date, admin"
	      		+ "   FROM member"
	    		+ "     WHERE admin = 'A'"
	      		+ "   ORDER BY user_no DESC"
	      		+ " )B "
	      		+ ") MEMBER_BOARD"
	      		+ " WHERE rnum BETWEEN ? AND ?";
	      
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
	            member.setAdmin(rs.getString("admin"));
	            list.add(member);
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
	public int setAdmin(int userno, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
	    
		String sql = ""
	  	      	+"UPDATE MEMBER SET ADMIN = 'A'"
				+" WHERE USER_NO = ?";
				
		int result = 0;
			
	  	try {
	  		ps = conn.prepareStatement(sql);
	  	    ps.setInt(1, userno);

	  	    result = ps.executeUpdate();
	  	   
	  	    } catch (SQLException e) {
	  	    	e.printStackTrace();
	  	    }finally {
	  	    	JDBCTemplate.close(rs);
	  	        JDBCTemplate.close(ps);
	  	    }
		
		return result;
	}
	
	
	@Override
	public int delAdmin(int userno, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+"UPDATE MEMBER SET ADMIN = 'N'"
				+" WHERE USER_NO = ?";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
}
