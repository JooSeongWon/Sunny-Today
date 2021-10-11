package xyz.sunnytoday.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;


@WebServlet("/board/update")
public class AdminBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//BoardService 객체 생성
	private AdminBoardService boardService = new AdminBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터 얻기 - boardno
		AdminBoard boardno = boardService.getBoardno(req);
		
		req.getRequestDispatcher("/WEB-INF/admin/board/update.jsp")
		.forward(req, resp);
		
	}
	
}
