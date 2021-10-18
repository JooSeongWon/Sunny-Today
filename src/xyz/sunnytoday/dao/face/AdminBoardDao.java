package xyz.sunnytoday.dao.face;

import java.sql.Connection;

import java.util.List;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Board;

public interface AdminBoardDao {

	public List<Board> selectAll(Connection conn);

	public List<Board> selectAll(Connection conn, Paging paging);
	
	public int selectCntAll(Connection conn);

	public int boardCntAll(Connection conn);
	
	public int titleCount(Connection conn);

	public int selectCntTitle(Connection conn);
	
	public Board selectBoardByBoardno(Connection conn, Board board_no);
	
	public int insert(Connection conn, Board board);

	public int delete(Connection conn, Board board);
	
	public int update(Connection conn, Board board);



	
}

