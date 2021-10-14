package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminPostDao;
import xyz.sunnytoday.dto.Post;

public class AdminPostDaoImpl implements AdminPostDao{

	@Override
	public List<Post> selectAll(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	    String sql ="";
	    sql += " SELECT * FROM (";
	    sql += "	SELECT rownum rnum, POST.* FROM(SELECT post_no, b.title btitle, p.title ptitle, nick, write_date";
	    sql += "		FROM board b";
	    sql += "		INNER JOIN post p";
	    sql += "		ON b.board_no = p.board_no";
	    sql += "		INNER JOIN \"member\" m";
	    sql += "		ON p.user_no = m.user_no";
	    sql += "	ORDER BY post_no DESC) POST";
	    sql += " ) BOARD";
	    sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Post> postList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Post p = new Post();
				
				p.setPost_no( rs.getInt("post_no") );
				p.setTitle(rs.getString("btitle"));
				p.setTitle(rs.getString("ptitle"));
				p.setBoard_no( rs.getInt("board_no"));
//				p.setNick(rs.getString("nick"));
				p.setWrite_date(rs.getDate("write_date"));
				
				//리스트에 결과값 저장
				postList.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return postList;
	}
	
//	@Override
//	public List<Post> selectAll(Connection conn, Paging paging) {
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//	
//		String sql = "";
//		sql += "SELECT * FROM (";
//		sql += "	SELECT rownum rnum, B.* FROM (";
//		sql += "		SELECT";
//		sql += "		*";
//		sql += "		FROM post";
//		sql += "		ORDER BY post_no DESC";
//		sql += "	) B";
//		sql += " ) POST";
//		sql += " WHERE rnum BETWEEN ? AND ?";
//		
//		//결과 저장할 List
//		List<Post> postList = new ArrayList<>(); 
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, paging.getStartNo());
//			ps.setInt(2, paging.getEndNo());
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				Post p = new Post();
//				
//				p.setPost_no( rs.getInt("post_no") );
//				p.setBoard_no( rs.getInt("board_no"));
////				p.setUser_no(rs.getInt("uesr_no"));
//				p.setWrite_date(rs.getDate("write_date"));
//				p.setLast_modify(rs.getDate("last_modify"));
//				p.setTitle(rs.getString("title"));
//				p.setContent(rs.getString("content"));
//				p.setHit(rs.getInt("hit"));
//				
//				//리스트에 결과값 저장
//				postList.add(p);
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		return postList;
//	}

	@Override
	public int selectCntAll(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM post";
		
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
}