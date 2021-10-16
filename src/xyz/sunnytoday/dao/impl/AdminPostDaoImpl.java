package xyz.sunnytoday.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminPostDao;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;

public class AdminPostDaoImpl implements AdminPostDao{

	public List<Map<String, Object>> selectAll(Connection conn, Paging paging) { 
		
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
		sql += " where ROWNUM between ? and ?";

	    List<Map<String, Object>> allList = new ArrayList<>(); 
	    
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
				post.setTitle(rs.getString("ptitle"));
				b.setTitle(rs.getString("btitle"));
				post.setWrite_date(rs.getDate("write_date"));
				post.setUser_no(rs.getInt("user_no"));
				member.setUserno(post.getUser_no());
				post.setContent(rs.getString("content"));
				member.setNick(rs.getString("nick"));
//				
//				//리스트에 결과값 저장
				map.put("post",post);
				map.put("board",b);
				map.put("member",member);
//				
				allList.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return allList;
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
	public Post selectPostByPostno(Connection conn, Post post_no) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM post";
		sql += " WHERE post_no = ?";
		
		//결과 저장할 Post객체
		Post viewpost = null;
//		Board board = null;
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, post_no.getPost_no()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {

				viewpost = new Post(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewpost.setPost_no(rs.getInt("post_no"));
				viewpost.setTitle(rs.getString("title"));
//				카테고리
//				닉네임
				viewpost.setContent(rs.getString("content"));
				viewpost.setWrite_date(rs.getDate("write_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewpost;	
	}	
	

	@Override
	public int insert(Connection conn, Post post) {
		// post/write에 값입력 쿼리
		
		PreparedStatement ps = null;
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO POST(POST_NO, TITLE, CONTENT)";
		sql += " VALUES (?, ?, ? )";
		
//		sql += " VALUES (post_seq.nextval, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, post.getPost_no());
			ps.setString(2, post.getTitle());
//			ps.setString(4, post.getNick());
			ps.setString(3, post.getContent());
			

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;	
	}

	@Override
	public int delete(Connection conn, Post post) {
		
		String sql = "";
		sql += "DELETE post";
		sql += " WHERE post_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
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