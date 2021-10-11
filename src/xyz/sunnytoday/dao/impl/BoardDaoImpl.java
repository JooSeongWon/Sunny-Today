package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dto.Post;

public class BoardDaoImpl implements BoardDao {
	
	@Override
	public List<Post> selectAll(Connection conn, Paging paging) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM ( ";
		sql += "        SELECT post_no, board_no, user_no, write_date, last_modify, title, content, hit";
		sql += "        FROM post";
		sql += "        ORDER BY boardno DESC";
		sql += "	   ) B";
		sql += "	) BOARD";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		List<Post> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Post post = new Post();
				
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("title") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				
				//리스트에 결과값 저장
				list.add(post);
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
	public int selectCntAll(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
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
