package xyz.sunnytoday.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;


@WebServlet("/board/update")
public class AdminBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//BoardService 객체 생성
	private final AdminBoardService boardService = new AdminBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/admin/board/update.jsp")
		.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		작성글 삽입
//		boardService.write(req);
		
		resp.sendRedirect("/SunnyToday/board/list");
		
	}
}
