package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dao.impl.BoardDaoImpl;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;

public class BoardServiceImpl implements BoardService {
	
	BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public List<Post> getList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
//		JDBCTemplate.close(conn);
		return boardDao.selectMainListAll(conn, paging);
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[ERROR] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = boardDao.selectCntAll(conn);
		
		Paging paging = new Paging(totalCount, curPage);
		
//		JDBCTemplate.close(conn);
		return paging;
	}

	@Override
	public List<Map<String, Object>> getAskingList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectAskingListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getBuyList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectBuyListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getMineList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectMineListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getShareList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectShareListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getDailyList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectDailyListAll(board, conn, paging);
	}

}
