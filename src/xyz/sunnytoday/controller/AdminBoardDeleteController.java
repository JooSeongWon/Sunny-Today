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

@WebServlet("/admin/board/delete")
public class AdminBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AdminBoardService boardService = new AdminBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board board = boardService.getBoardno(req);

		boardService.deleteByAdBoard(board);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/admin/board/list");	
	}
}
