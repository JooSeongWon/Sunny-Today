package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;

@WebServlet("/admin/board/write")
public class AdminBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final AdminBoardService boardService = new AdminBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/views/admin/board/write.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boardService.write(req);
		
		resp.sendRedirect("/SunnyToday/admin/board/list");
	}
}
