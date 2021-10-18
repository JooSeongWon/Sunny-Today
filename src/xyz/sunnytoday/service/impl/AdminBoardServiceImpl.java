package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminBoardDao;
import xyz.sunnytoday.dao.impl.AdminBoardDaoImpl;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.service.face.AdminBoardService;

public class AdminBoardServiceImpl implements AdminBoardService {
	
	private AdminBoardDao boardDao = new AdminBoardDaoImpl();

	@Override
	public List<Board> getList() {
		Connection conn = JDBCTemplate.getConnection();
		
		List<Board> boardList = boardDao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		return boardList;
		
	}

	@Override
	public List<Board> getList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();

		List<Board> getList = boardDao.selectAll(conn, paging);
		
		JDBCTemplate.close(conn);
		return getList;
	}	
	
	

	@Override
	public Paging getPaging(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = boardDao.selectCntAll(conn);
		
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		return paging;
	}
	
	@Override
	public int getCount(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		int boardCount = boardDao.boardCntAll(conn);
		JDBCTemplate.close(conn);

		return boardCount;
	}

	@Override
	public int getTitleCount(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		int titleCount = boardDao.titleCount(conn);
		JDBCTemplate.close(conn);

		return titleCount;

	}
	

	@Override
	public int getCntTitle(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		int postCntTitle = boardDao.selectCntTitle(conn);
		JDBCTemplate.close(conn);

		return postCntTitle;
	}

	
	@Override
	public Board getBoardno(HttpServletRequest req) {
		
		Board boardno = new Board();
		
		String param = req.getParameter("board_no");
		if(param!=null && !"".equals(param)) {
			
			boardno.setBoard_no( Integer.parseInt(param) );
		}
		
		return boardno;
	}
	
	@Override
	public Board view(Board board_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		Board board = boardDao.selectBoardByBoardno(conn, board_no); 
		
		JDBCTemplate.close(conn);
		return board;
	}
	
	@Override
	public void write(HttpServletRequest req) {

		Board bor = new Board();
			
//		bor.setBoard_no(Integer.parseInt(req.getParameter("board_no")) );
//		bor.setIndex(0);
//		bor.setLike("Y");
		bor.setComments_grant(req.getParameter("comments_grant"));
		bor.setLike(req.getParameter("like"));
		bor.setList_grant(req.getParameter("list_grant"));
		bor.setRead_grant(req.getParameter("read_grant"));
		bor.setShow(req.getParameter("show"));
		bor.setTitle( req.getParameter("title") );
		bor.setTitle_length(Integer.parseInt(req.getParameter("title_length")));
		bor.setWrite_grant(req.getParameter("write_grant"));
//		bor.setIndex(Integer.parseInt(req.getParameter("index")));
		
//		System.out.println(bor);
		
		Connection conn = JDBCTemplate.getConnection();
		if( boardDao.insert(conn, bor) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

	}

	@Override
	public void deleteByAdBoard(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( boardDao.delete(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		 
	}

	@Override
	public void updateByAdBoard(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		Board board = null;
		
		board = new Board();	
		
		board.setComments_grant(req.getParameter("comments_grant"));
		board.setLike(req.getParameter("like"));
		board.setList_grant(req.getParameter("list_grant"));
		board.setRead_grant(req.getParameter("read_grant"));
		board.setShow(req.getParameter("show"));
		board.setTitle(req.getParameter("title"));
		board.setTitle_length(Integer.parseInt(req.getParameter("title_length")));
		board.setWrite_grant(req.getParameter("write_grant"));
		board.setBoard_no(Integer.parseInt(req.getParameter("board_no")));
		
		if(board != null) {
			if( boardDao.update(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		}
		JDBCTemplate.close(conn);

	}







	
}
