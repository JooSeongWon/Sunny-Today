package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/report")
public class BoardReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/report [GET]");
		List<Map<String, Object>> list = null;
		Post param1 = new Post();
		Comments param2 = new Comments();
			
		if(req.getParameter("postno") != null && !"".equals(req.getParameter("postno"))) {
			param1.setPost_no(Integer.parseInt(req.getParameter("postno")));
			System.out.println("param : " + param1.getPost_no());
			
		}else {
			param2.setComments_no(Integer.parseInt(req.getParameter("comments_no")));
			System.out.println("param : " + param2.getComments_no());
			
		}
		list = boardService.boardDetail(param1, param2);

			
		req.setAttribute("list", list);
			
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardReport.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/report [POST]");
		boardService.insertReport(req);
		resp.sendRedirect("/board/main");
	}
}
