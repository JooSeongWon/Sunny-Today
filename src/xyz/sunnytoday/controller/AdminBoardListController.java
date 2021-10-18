package xyz.sunnytoday.controller;

import java.io.IOException


;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;
	
@WebServlet("/admin/board/list")
public class AdminBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminBoardService boardService = new AdminBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = boardService.getPaging(req);
		
//		List<Board> boardList = boardService.getList();
		
		List<Board> boardList = boardService.getList(paging);
		
		int boardCount = boardService.getCount(req);
		
		int titleCount = boardService.getTitleCount(req);
		
		int postCntTitle = boardService.getTitleCount(req);
		
		req.setAttribute("boardList", boardList);
		
		req.setAttribute("paging", paging);
		
		req.setAttribute("boardCount", boardCount);

		req.setAttribute("titleCount", titleCount);
		
		req.setAttribute("postCntTitle", postCntTitle);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/board/list.jsp")
		.forward(req, resp);
	}
	
}

