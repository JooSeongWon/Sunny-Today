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
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/search")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/search [GET]");
		
		
		Paging paging = boardService.getPaging(req);
		List<Map<String, Object>> searchList = boardService.getSearchList(req, paging);
		boardService.setThumFile(searchList);
		
		for( Map<String, Object> e : searchList ) { System.out.println( e ); };
		
		
		req.setAttribute("searchList", searchList);
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardSearchList.jsp").forward(req, resp);
	}
}
