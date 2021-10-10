package xyz.sunnytoday.dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Paging;
import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dto.Board;

public class BoardDaoImpl implements BoardDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Board> selectAll(Connection conn) {

		String sql = "";
		sql += "SELECT * FROM board";
		sql += " ORDER BY board_no DESC";
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board(); 
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
	public List<Board> selectAll(Connection conn, Paging paging) {
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
		List<Board> boardList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
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
	public Board selectBoardByBoardno(Connection conn, Board boardno) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";
		
		//결과 저장할 Board객체
		Board viewBoard = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getBoard_no()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				viewBoard = new Board(); //결과값 저장 객체
				
//				//결과값 한 행 처리
//				viewBoard.setBoardno( rs.getInt("boardno") );
//				viewBoard.setTitle( rs.getString("title") );
//				viewBoard.setUserid( rs.getString("userid") );
//				viewBoard.setContent( rs.getString("content") );
//				viewBoard.setHit( rs.getInt("hit") );
//				viewBoard.setWriteDate( rs.getDate("write_date") );
				
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
	
}
