package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/report")
public class BoardReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/report");
		if(req.getParameter("post_no") != null && !"".equals(req.getParameter("post_no"))) {
			List<Map<String, Object>> list = null;
			Post param = new Post();
			param.setPost_no(Integer.parseInt(req.getParameter("post_no")));
			list = boardService.boardDetail(param);
			
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/views/user/board/boardReport.jsp").forward(req, resp);
		}
	}
}
