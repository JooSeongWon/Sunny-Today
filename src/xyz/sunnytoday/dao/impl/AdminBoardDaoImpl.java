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
		}
		
		return count;	
	}
	
	@Override
	public int boardCntAll(Connection conn){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) count FROM board";
		
		//총 게시글 수
		int boardCount = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next())
				boardCount = rs.getInt("count");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return boardCount;	
	}

	@Override
	public int titleCount(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(title) count FROM board";
		
		//총 게시글 수
		int titleCount = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next())
				titleCount = rs.getInt("count");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return titleCount;	
	}

	@Override
	public AdminBoard selectBoardByBoardno(Connection conn, AdminBoard board_no) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE board_no = ?";
		
		//결과 저장할 Board객체
		AdminBoard viewBoard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, board_no.getBoard_no()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewBoard = new AdminBoard(); //결과값 저장 객체
				
				//결과값 한 행 처리
				viewBoard.setBoard_no( rs.getInt("board_no") );
				viewBoard.setComments_grant( rs.getString("comments_grant"));
				viewBoard.setIndex(rs.getInt("index"));
				viewBoard.setLike(rs.getString("like"));
				viewBoard.setList_grant(rs.getString("list_grant"));
				viewBoard.setRead_grant(rs.getString("read_grant"));
				viewBoard.setShow(rs.getString("show"));
				viewBoard.setTitle( rs.getString("title") );
				viewBoard.setTitle_length(rs.getInt("title_length"));
				viewBoard.setWrite_grant(rs.getString("write_grant"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return viewBoard;	
	}
	
	@Override
	public int insert(Connection conn, AdminBoard board) {
		
		PreparedStatement ps = null;
		
		//SQL 작성
		String sql = "";
		sql += "INSERT INTO board(board_no, comments_grant, \"LIKE\"";
		sql += " , list_grant, read_grant, \"SHOW\", \"TITLE\"";
		sql += " , title_length, write_grant";
//		sql += "FROM BOARD)";
		sql += " )";
		sql += " VALUES (board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
//			ps.setInt(1, board.getBoard_no());
			ps.setString(1, board.getComments_grant());
			ps.setString(2, board.getLike());
			ps.setString(3, board.getList_grant());
			ps.setString(4, board.getRead_grant());
			ps.setString(5, board.getShow());
			ps.setString(6, board.getTitle());
			ps.setInt(7, board.getTitle_length());
			ps.setString(8, board.getWrite_grant());
//			ps.setInt(9, board.getIndex());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;	
	
	}

	@Override
	public int delete(Connection conn, AdminBoard board) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE board";
		sql += " WHERE board_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoard_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int update(Connection conn, AdminBoard board) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE board";
		sql += " SET comments_grant = ?,";
		sql += " 	\"LIKE\" = ?,";
		sql += " 	list_grant = ?,";
		sql += " 	read_grant = ?,";
		sql += " 	\"SHOW\" = ?,";
		sql += " 	\"TITLE\" = ?,";
		sql += " 	title_length = ?,";
		sql += " 	write_grant = ?";
		sql += " WHERE board_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getComments_grant());
			ps.setString(2, board.getLike());
			ps.setString(3, board.getList_grant());
			ps.setString(4, board.getRead_grant());
			ps.setString(5, board.getShow());
			ps.setString(6, board.getTitle());
			ps.setInt(7, board.getTitle_length());
			ps.setString(8, board.getWrite_grant());
			ps.setInt(9, board.getBoard_no());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	





	
}
