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
import xyz.sunnytoday.dto.Report;
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
//			System.out.println("param : " + param1.getPost_no());
		}
		
		if(req.getParameter("comments_no") != null && !"".equals(req.getParameter("comments_no"))) {
			param2.setComments_no(Integer.parseInt(req.getParameter("comments_no")));
//			System.out.println("comments_no : " + param2.getComments_no());
		}
		
		list = boardService.boardDetail(param1, param2);

//		for( Map<String, Object> e : list ) {
//		System.out.println( e );
//		}
//		
		req.setAttribute("list", list);
			
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardReport.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/report [POST]");
		req.setCharacterEncoding("UTF-8");
		Report param = new Report();
		
		//신고 사유
		if(req.getParameter("report_reason") == "advertisement") {
			param.setReport_c_no(1);
		}else if(req.getParameter("report_reason") == "pornography") {
			param.setReport_c_no(2);
		}else if(req.getParameter("report_reason") == "defamation") {
			param.setReport_c_no(3);
		}else {
			param.setReport_c_no(4);
		}
		
		param.setUser_no(Integer.parseInt(req.getParameter("user_no")));
		param.setTarget_no(Integer.parseInt(req.getParameter("target_no")));
		param.setDetail(req.getParameter("report_detail"));
		
		if(req.getParameter("post_no") != null && !"".equals(req.getParameter("post_no"))) {
			param.setPost_no(Integer.parseInt(req.getParameter("post_no")));
		}
		
		if(req.getParameter("comments_no") != null && !"".equals(req.getParameter("comments_no"))) {
			param.setComments_no(Integer.parseInt(req.getParameter("comments_no")));
		}

		if(req.getParameter("report_type") == "post_type") {
			param.setReport_type("P");
		}else {
			param.setReport_type("C");
		}
		System.out.println("param : " + param);
		boardService.insertReport(param);
		
		resp.sendRedirect("/board/main");
	}
}
