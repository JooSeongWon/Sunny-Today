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
import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;
	
@WebServlet("/board/list")
public class AdminBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminBoardService boardService = new AdminBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
//		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = boardService.getPaging(req);
		
		System.out.println("BoardListController [GET] - " + paging);

		//게시글 전체 조회
//		List<Board> boardList = boardService.getList();
		
		List<AdminBoard> boardList = boardService.getList(paging);
		
		System.out.println("BoardListController [GET] - " + boardList);
		  
		//조회결과 MODEL값 전달
		req.setAttribute("boardList", boardList);

		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/admin/board/list.jsp")
		.forward(req, resp);
	}
	
}

