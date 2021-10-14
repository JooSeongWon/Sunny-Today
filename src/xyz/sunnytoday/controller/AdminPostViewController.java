package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.service.face.AdminPostService;
import xyz.sunnytoday.service.impl.AdminPostServiceImpl;

@WebServlet("/admin/post/view")
public class AdminPostViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminPostService postService = new AdminPostServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달파라미터 얻기 - board_no
		AdminBoard board_no = boardService.getBoardno(req);

		//상세보기 결과 조회
		AdminBoard viewBoard = boardService.view(board_no);
		
		req.setAttribute("viewBoard", viewBoard);

		req.getRequestDispatcher("/WEB-INF/views/admin/post/view.jsp").forward(req, resp);		
	}
	
}
