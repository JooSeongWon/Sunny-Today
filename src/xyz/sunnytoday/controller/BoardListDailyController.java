package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/board/list/daily")
public class BoardListDailyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Paging paging = boardService.getPaging(req);
		
		List<Map<String, Object>> list = boardService.getDailyList(req, paging);
		
//		String date = list.get(0).get("board").toString();
//		String boardTitle = boardService.getValueFromMap(date,"title");
		
		boardService.setThumFile(list);
		
//		
//		for( Map<String, Object> e : list ) {
//			System.out.println( e );
//		}

//		System.out.println("boardno : " + boardno);
		paging = boardService.getTitlePaging(req, "daily");	
		
//		System.out.println(boardTitle);
		
		req.setAttribute("list", list);
		req.setAttribute("boardTitle", "daily");
		req.setAttribute("paging", paging);
		req.setAttribute("notice", boardService.getNotices().get(Board.TYPE_NOTICE));
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardDaily.jsp").forward(req, resp);

		
		
	}
	
}
