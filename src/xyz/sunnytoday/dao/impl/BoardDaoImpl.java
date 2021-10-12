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
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Post;

public class BoardDaoImpl implements BoardDao {
	
	@Override
	public List<Post> selectMainListAll(Connection conn, Paging paging) {
		System.out.println("selectMainListAll() 호출");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, board_no, user_no, write_date, last_modify, title, content, hit";
		sql += "        FROM post P";
		sql += "        ORDER BY board_no DESC";
		sql += "	   ) POST";
		sql += "	) MAINPOST";
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

	@Override
	public List<Map<String, Object>> selectAskingListAll(Board board, Connection conn, Paging paging) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
		sql += "        FROM post P, board B";
		sql += "		WHERE P.board_no = B.board_no";
		sql += "		AND B.title = 'asking'";
		sql += "        ORDER BY P.board_no DESC";
		sql += "	   ) POST";
		sql += "	) ASKINGPOST";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo() );
			ps.setInt(2, paging.getEndNo() );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Post post = new Post();
				Board b = new Board();
								
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("ptitle") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				b.setTitle( rs.getString("btitle") );

				map.put("post", post);
				map.put("board", b);
				
				//리스트에 결과값 저장
				list.add(map);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		for( Map m : list ) {
			System.out.println(m);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectBuyListAll(Board board, Connection conn, Paging paging) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
		sql += "        FROM post P, board B";
		sql += "		WHERE P.board_no = B.board_no";
		sql += "		AND B.title = 'buy'";
		sql += "        ORDER BY P.board_no DESC";
		sql += "	   ) POST";
		sql += "	) ASKINGPOST";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo() );
			ps.setInt(2, paging.getEndNo() );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Post post = new Post();
				Board b = new Board();
								
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("ptitle") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				b.setTitle( rs.getString("btitle") );

				map.put("post", post);
				map.put("board", b);
				
				//리스트에 결과값 저장
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
	public List<Map<String, Object>> selectMineListAll(Board board, Connection conn, Paging paging) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
		sql += "        FROM post P, board B";
		sql += "		WHERE P.board_no = B.board_no";
		sql += "		AND B.title = 'mine'";
		sql += "        ORDER BY P.board_no DESC";
		sql += "	   ) POST";
		sql += "	) ASKINGPOST";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo() );
			ps.setInt(2, paging.getEndNo() );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Post post = new Post();
				Board b = new Board();
								
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("ptitle") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				b.setTitle( rs.getString("btitle") );

				map.put("post", post);
				map.put("board", b);
				
				//리스트에 결과값 저장
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
	public List<Map<String, Object>> selectShareListAll(Board board, Connection conn, Paging paging) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
		sql += "        FROM post P, board B";
		sql += "		WHERE P.board_no = B.board_no";
		sql += "		AND B.title = 'share'";
		sql += "        ORDER BY P.board_no DESC";
		sql += "	   ) POST";
		sql += "	) ASKINGPOST";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo() );
			ps.setInt(2, paging.getEndNo() );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Post post = new Post();
				Board b = new Board();
								
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("ptitle") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				b.setTitle( rs.getString("btitle") );

				map.put("post", post);
				map.put("board", b);
				
				//리스트에 결과값 저장
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		for( Map m : list ) {
			System.out.println(m);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectDailyListAll(Board board, Connection conn, Paging paging) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql ="";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, POST.* FROM ( ";
		sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
		sql += "        FROM post P, board B";
		sql += "		WHERE P.board_no = B.board_no";
		sql += "		AND B.title = 'daily'";
		sql += "        ORDER BY P.board_no DESC";
		sql += "	   ) POST";
		sql += "	) ASKINGPOST";
		sql += "	WHERE rnum BETWEEN ? AND ?";
				
		
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo() );
			ps.setInt(2, paging.getEndNo() );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				Post post = new Post();
				Board b = new Board();
								
				post.setPost_no( rs.getInt("post_no") );
				post.setBoard_no( rs.getInt("board_no") );
				post.setUser_no( rs.getInt("user_no") );
				post.setWrite_date( rs.getDate("write_date") );
				post.setLast_modify( rs.getDate("last_modify") );
				post.setTitle( rs.getString("ptitle") );
				post.setContent( rs.getString("content") );
				post.setHit( rs.getInt("hit") );
				b.setTitle( rs.getString("btitle") );

				map.put("post", post);
				map.put("board", b);
				
				//리스트에 결과값 저장
				list.add(map);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		for( Map m : list ) {
			System.out.println(m);
		}
		
		return list;
	}

}
