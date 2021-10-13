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
import xyz.sunnytoday.dto.File;
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
		sql += "        FROM post";
		sql += "        ORDER BY post_no DESC";
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
		sql += "        ORDER BY post_no DESC";
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
		sql += "        ORDER BY post_no DESC";
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
		sql += "        ORDER BY post_no DESC";
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
		sql += "        ORDER BY post_no DESC";
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
		sql += "        ORDER BY post_no DESC";
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
	public int selectNextPost_no(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT post_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextPost_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextPost_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextPost_no;
	}
	
	@Override
	public int selectNextBaord_no(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextBoard_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextBoard_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextBoard_no;
		
	}
	
	@Override
	public int selectNextUser_no(Connection conn) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT member_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextUser_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextUser_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextUser_no;
	}

	@Override
	public int insert(Connection conn, Post post, Board board) {

		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT ALL";
		sql += "	INTO post(post_no, board_no, user_no, title, content)";
		sql += "	VALUES (?, ?, ?, ?, ?)";
		sql += "	INTO board";
		sql += "	VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		sql += "	SELECT * FROM DUAL";
			
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, post.getPost_no() );
			ps.setInt(2, board.getBoard_no() );
			ps.setInt(3, post.getUser_no() );
			ps.setString(4, post.getTitle() );
			ps.setString(5, post.getContent() );
			
			ps.setInt(6, board.getBoard_no() );
			ps.setInt(7, 1 );
			ps.setString(8, board.getTitle() );		
			ps.setString(9, "Y");
			ps.setString(10, "M" );
			ps.setString(11, "M" );
			ps.setString(12, "M" );
			ps.setString(13, "M" );
			ps.setString(14, "Y" );
			ps.setInt(15, 10 );

			res = ps.executeUpdate();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int insertFile(Connection conn, File file) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT INTO \"FILE\"( file_no, url, thumbnail_url, origin_name, user_no )";
		sql += " VALUES( file_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, file.getUrl());
			ps.setString(2, "아직");
			ps.setString(3, file.getOrigin_name());
			ps.setInt(4, 5);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int updateHit(Connection conn, Post post_no) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		sql += "UPDATE post";
		sql += " SET hit = hit + 1";
		sql += " WHERE post_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체			
			ps.setInt(1, post_no.getPost_no()); //조회할 게시글 번호 적용			
			res = ps.executeUpdate(); //SQL 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public Post selectBoardByPostno(Connection conn, Post post_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		String sql = "";
		sql += "SELECT * FROM post";
		sql += " WHERE post_no = ?";
		
		Post detailPost = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체			
			ps.setInt(1, post_no.getPost_no()); //조회할 게시글 번호 적용			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				detailPost = new Post(); //결과값 저장 객체
				
				//결과값 한 행 처리
				detailPost.setPost_no( rs.getInt("post_no") );
				detailPost.setBoard_no( rs.getInt("board_no") );
				detailPost.setUser_no( rs.getInt("user_no") );
				detailPost.setWrite_date( rs.getDate("write_date") );
				detailPost.setLast_modify( rs.getDate("last_modify") );
				detailPost.setTitle( rs.getString("title") );
				detailPost.setContent( rs.getString("content") );
				detailPost.setHit( rs.getInt("hit") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return detailPost;
	}

	@Override
	public String selectNickByUserno(Connection conn, Post detailBoard) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += "SELECT usernick FROM member";
		sql += " WHERE userid = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, detailBoard.getUser_no()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("usernick");
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
	public File selectFile(Connection conn, Post detailBoard) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "";
		sql += "SELECT * FROM \"FILE\"";
		sql += " WHERE user_no = ?";
		sql += " ORDER BY file_no";

		File file = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, detailBoard.getUser_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				file = new File();
				
				file.setFile_no( rs.getInt("file_no") );
				file.setUrl( rs.getString("url") );
				file.setThumbnail_url( rs.getString("thumbnail_url") );
				file.setOrigin_name( rs.getString("origin_name") );
				file.setUser_no( rs.getInt("user_no") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return file;
		
	}

	@Override
	public int delete(Connection conn, Post post) {
		
		PreparedStatement ps = null; 

		String sql = "";
		sql += "DELETE post";
		sql += " WHERE post_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPost_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteFile(Connection conn, Post post) {
		
		PreparedStatement ps = null; 
		
		String sql = "";
		sql += "DELETE file";
		sql += " WHERE boardno = ?";
				
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getPost_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	

}
