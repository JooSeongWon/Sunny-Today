package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminBoardDao;
import xyz.sunnytoday.dao.face.AdminMessageDao;
import xyz.sunnytoday.dao.face.AdminPostDao;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;

public class AdminPostDaoImpl implements AdminPostDao{
	
	public List<Map<String, Object>> selectAll(Connection conn, Paging paging) { 
		
		PreparedStatement ps = null; 
		ResultSet rs = null;
		String sql = "";
		sql += " select ROWNUM, post_list.*";
		sql += " from (select POST_NO, P.board_no, P.user_no puser_no, p.TITLE ptitle, b.TITLE btitle, WRITE_DATE, M.USER_NO muser_no, CONTENT, NICK, last_modify, hit";
		sql += "       from POST p";
		sql += "                inner join BOARD B";
		sql += "                           on p.BOARD_NO = B.BOARD_NO";
		sql += "                inner join MEMBER M";
		sql += "                           on p.USER_NO = M.USER_NO";
		sql += "       order by POST_NO desc) post_list";
		sql += " where ROWNUM between ? and ?";

	    List<Map<String, Object>> list = new ArrayList<>(); 
	    
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
//			System.out.println(paging.getStartNo());
//			System.out.println(paging.getEndNo());
			
			rs = ps.executeQuery();
			System.out.println(rs.next());
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				Post post = new Post();
				Board b = new Board();
				Member member = new Member();
				post.setPost_no( rs.getInt("post_no"));
				post.setBoard_no(rs.getInt("board_no"));
				post.setUser_no(rs.getInt("puser_no"));
				post.setTitle(rs.getString("ptitle"));
				b.setTitle(rs.getString("btitle"));
				post.setWrite_date(rs.getDate("write_date"));
				member.setUserno(rs.getInt("muser_no"));
//				member.setUserno(post.getUser_no());
				post.setContent(rs.getString("content"));
				member.setNick(rs.getString("nick"));
				post.setHit(rs.getInt("hit"));
				post.setLast_modify(rs.getDate("last_modify"));
//				
//				//리스트에 결과값 저장
				map.put("post",post);
				map.put("board",b);
				map.put("member",member);
//				
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
	public int selectNextFile_no(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT file_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextFile_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextFile_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextFile_no;
	}
	
//	@Override
//	public int changeBoardno(Connection conn, String value) {
//		
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		String sql = "";
//		sql += "SELECT board_no FROM board";
//		sql += "	WHERE title = ?";
//		
//		//결과 저장 변수
//		int board_no = 0;
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, value);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				board_no = rs.getInt(1);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		return board_no;
//	}
	
	@Override
	public int insert(Connection conn, Post post) {

		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT INTO post(post_no, board_no, user_no, title, content)";
		sql += "	VALUES (?, ?, ?, ?, ?)";
			
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, post.getPost_no() );
			ps.setInt(2, post.getBoard_no() );
			ps.setInt(3, post.getUser_no() );
			ps.setString(4, post.getTitle() );
			ps.setString(5, post.getContent() );

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
		System.out.println(file);
		
		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT INTO \"FILE\"( file_no, url, thumbnail_url, origin_name, user_no )";
		sql += " VALUES( ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, file.getFile_no());
			ps.setString(2, file.getUrl());
			ps.setString(3, file.getThumbnail_url());
			ps.setString(4, file.getOrigin_name());
			ps.setInt(5, file.getUser_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
	
	@Override
	public int insertFileInfo(Connection conn, PostFile postFile) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT INTO post_file( post_no, file_no )";
		sql += " VALUES( ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, postFile.getPost_no());
			ps.setInt(2, postFile.getFile_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public Post selectPostByPostno(Connection conn, Post post_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		String sql = "";
		sql += "SELECT * FROM post";
		sql += " WHERE post_no = ?";
		
		Post viewpost = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체			
			ps.setInt(1, post_no.getPost_no()); //조회할 게시글 번호 적용			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewpost = new Post(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewpost.setPost_no( rs.getInt("post_no") );
				viewpost.setBoard_no( rs.getInt("board_no") );
				viewpost.setUser_no( rs.getInt("user_no") );
				viewpost.setWrite_date( rs.getDate("write_date") );
				viewpost.setLast_modify( rs.getDate("last_modify") );
				viewpost.setTitle( rs.getString("title") );
				viewpost.setContent( rs.getString("content") );
				viewpost.setHit( rs.getInt("hit") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewpost;
	}
	
	@Override
	public String selectNickByUserno(Connection conn, Post viewPost) {
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 String 변수
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewPost.getUser_no()); //조회할 no 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				nick = rs.getString("nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return nick;
	}
	
	@Override
	public File selectFile(Connection conn, int fileno) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "";
		sql += "SELECT * FROM \"FILE\"";
		sql += " WHERE file_no = ?";

		File file = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, fileno);
			
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
	public int changeFileno(Connection conn, Post post_no) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT file_no FROM post_file";
		sql += "	WHERE post_no = ?";
		sql += "	ORDER BY file_no";
		
		int fileno = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post_no.getPost_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {		
				
				fileno = rs.getInt("file_no");			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return fileno;
		
	}	
	
	@Override
	public int delete(Connection conn, Post post) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
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
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public List<Map<String, Object>> searchTitle(Connection conn, String search, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += " select ROWNUM, post_list.*";
		sql += " from (select POST_NO, p.TITLE ptitle, b.TITLE btitle, WRITE_DATE, M.USER_NO, CONTENT, NICK";
		sql += "       from POST p";
		sql += "                inner join BOARD B";
		sql += "                           on p.BOARD_NO = B.BOARD_NO";
		sql += "                inner join MEMBER M";
		sql += "                           on p.USER_NO = M.USER_NO";
		sql += "       order by POST_NO desc) post_list";
		sql += " WHERE ptitle LIKE ? and ROWNUM between ? and ?";
		
	    List<Map<String, Object>> list = new ArrayList<>(); 	
	    
	    try {
			ps = conn.prepareStatement(sql);
	         ps.setString(1, '%'+search+"%");
	         ps.setInt(2, paging.getStartNo());
	         ps.setInt(3, paging.getEndNo());
	         rs = ps.executeQuery();
	        
	    while(rs.next()) {			
	        Map<String, Object> map = new HashMap<>();
			Post post = new Post();
			Board b = new Board();
			Member member = new Member();			
			
			post.setPost_no( rs.getInt("post_no"));
			post.setTitle(rs.getString("ptitle"));
			b.setTitle(rs.getString("btitle"));
			post.setWrite_date(rs.getDate("write_date"));
			post.setUser_no(rs.getInt("user_no"));
			member.setUserno(post.getUser_no());
			post.setContent(rs.getString("content"));
			member.setNick(rs.getString("nick"));
//			
//			//리스트에 결과값 저장
			map.put("post",post);
			map.put("board",b);
			map.put("member",member);
//			
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
	public List<Map<String, Object>> searchNick(Connection conn, String search, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;	
		
		String sql = "";
		sql += " select ROWNUM, post_list.*";
		sql += " from (select POST_NO, p.TITLE ptitle, b.TITLE btitle, WRITE_DATE, M.USER_NO, CONTENT, NICK";
		sql += "       from POST p";
		sql += "                inner join BOARD B";
		sql += "                           on p.BOARD_NO = B.BOARD_NO";
		sql += "                inner join MEMBER M";
		sql += "                           on p.USER_NO = M.USER_NO";
		sql += "       order by POST_NO desc) post_list";
		sql += " WHERE nick LIKE ? and ROWNUM between ? and ?";
		
	    List<Map<String, Object>> list = new ArrayList<>(); 	
	    
	    try {
			ps = conn.prepareStatement(sql);
	         ps.setString(1, '%'+search+"%");
	         ps.setInt(2, paging.getStartNo());
	         ps.setInt(3, paging.getEndNo());
	         rs = ps.executeQuery();
	        
	    while(rs.next()) {			
	        Map<String, Object> map = new HashMap<>();
			Post post = new Post();
			Board b = new Board();
			Member member = new Member();			
			
			post.setPost_no( rs.getInt("post_no"));
			post.setTitle(rs.getString("ptitle"));
			b.setTitle(rs.getString("btitle"));
			post.setWrite_date(rs.getDate("write_date"));
			post.setUser_no(rs.getInt("user_no"));
			member.setUserno(post.getUser_no());
			post.setContent(rs.getString("content"));
			member.setNick(rs.getString("nick"));
//			
//			//리스트에 결과값 저장
			map.put("post",post);
			map.put("board",b);
			map.put("member",member);
//			
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
	
//	public List<Map<String, Object>> selectPostByPostno(Connection conn, Post post_no) {
//	
//	PreparedStatement ps = null; 
//	ResultSet rs = null;
//	
//	String sql = "";
//	sql += " select ROWNUM, post_list.*";
//	sql += " from (select POST_NO, p.TITLE ptitle, b.TITLE btitle, WRITE_DATE, M.USER_NO, CONTENT, NICK";
//	sql += "       from POST p";
//	sql += "                inner join BOARD B";
//	sql += "                           on p.BOARD_NO = B.BOARD_NO";
//	sql += "                inner join MEMBER M";
//	sql += "                           on p.USER_NO = M.USER_NO";
//	sql += "       order by POST_NO desc) post_list";
//
//    List<Map<String, Object>> testlist = new ArrayList<>(); 
//    
//	try {
//		ps = conn.prepareStatement(sql);
//		rs = ps.executeQuery();
//		System.out.println(rs.next());
//		
//		while(rs.next()) {
//			Map<String, Object> map = new HashMap<>();
//			Post post = new Post();
//			Board b = new Board();
//			Member member = new Member();
//			post.setPost_no( rs.getInt("post_no"));
//			post.setTitle(rs.getString("ptitle"));
//			b.setTitle(rs.getString("btitle"));
//			post.setWrite_date(rs.getDate("write_date"));
//			post.setUser_no(rs.getInt("user_no"));
//			member.setUserno(post.getUser_no());
//			post.setContent(rs.getString("content"));
//			member.setNick(rs.getString("nick"));
////			
////			//리스트에 결과값 저장
//			map.put("post",post);
//			map.put("board",b);
//			map.put("member",member);
////			
//			testlist.add(map);
//		}
//		
//	} catch (SQLException e) {
//		e.printStackTrace();
//	} finally {
//		JDBCTemplate.close(rs);
//		JDBCTemplate.close(ps);
//	}
//	
//	return testlist;
//}
}