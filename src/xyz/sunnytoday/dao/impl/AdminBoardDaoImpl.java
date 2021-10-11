package xyz.sunnytoday.dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminBoardDao;
import xyz.sunnytoday.dto.AdminBoard;

public class AdminBoardDaoImpl implements AdminBoardDao {

	@Override
	public List<AdminBoard> selectAll(Connection conn) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " ORDER BY board_no DESC";
		
		List<AdminBoard> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				AdminBoard b = new AdminBoard(); 
				b.setBoard_no( rs.getInt("board_no") );
				b.setComments_grant( rs.getString("comments_grant"));
				b.setIndex(rs.getInt("index"));
				b.setLike(rs.getString("like"));
				b.setList_grant(rs.getString("list_grant"));
				b.setRead_grant(rs.getString("read_grant"));
				b.setShow(rs.getString("show"));
				b.setTitle( rs.getString("title") );
				b.setTitle_length(rs.getInt("title_length"));
				b.setWrite_grant(rs.getString("write_grant"));
				
				boardList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return boardList;
	}

	@Override
	public List<AdminBoard> selectAll(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += "		SELECT";
		sql += "		*";
//		sql += "			board_no, comments_grant, \"index\"";
//		sql += "			, \"like\", list_grant";
//      sql += "            , read_grant, \"show\"";
//      sql += "            , \"title\", title_length";
//      sql += "			, write_grant";
		sql += "		FROM board";
		sql += "		ORDER BY board_no DESC";
		sql += "	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<AdminBoard> boardList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				AdminBoard b = new AdminBoard();
				
				b.setBoard_no( rs.getInt("board_no") );
				b.setComments_grant( rs.getString("comments_grant"));
				b.setIndex(rs.getInt("index"));
				b.setLike(rs.getString("like"));
				b.setList_grant(rs.getString("list_grant"));
				b.setRead_grant(rs.getString("read_grant"));
				b.setShow(rs.getString("show"));
				b.setTitle( rs.getString("title") );
				b.setTitle_length(rs.getInt("title_length"));
				b.setWrite_grant(rs.getString("write_grant"));
				
				//리스트에 결과값 저장
				boardList.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			JDBCTemplate.close(conn);

			
		}
		
		return boardList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board";
		
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
			JDBCTemplate.close(conn);
		}
		
		return count;	
	}

	@Override
	public int insert(Connection conn, AdminBoard board) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//게시판 작성
		String sql = "";
		sql += "INSERT INTO board";
		sql += " VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		int res = 0;
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getBoard_no());
			ps.setString(2, board.getComments_grant());
			ps.setInt(3, board.getIndex());
			ps.setString(4, board.getLike());
			ps.setString(5, board.getList_grant());
			ps.setString(6, board.getRead_grant());
			ps.setString(7, board.getShow());
			ps.setString(8, board.getTitle());
			ps.setInt(9, board.getTitle_length());
			ps.setString(10, board.getWrite_grant());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;	
		}

	
}
