package xyz.sunnytoday.controller;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;


@WebServlet("/admin/board/update")
public class AdminBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//BoardService 객체 생성
	private final AdminBoardService boardService = new AdminBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board boardno = boardService.getBoardno(req);

		Board updateBoard = boardService.view(boardno);
		
		req.setAttribute("updateBoard", updateBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/board/update.jsp")
		.forward(req, resp);
		
	}
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boardService.updateByAdBoard(req);
		
		resp.sendRedirect("/admin/board/list");
		
	}
		
}
