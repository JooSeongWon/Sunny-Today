package xyz.sunnytoday.controller;

import java.io.IOException

;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.AdminBoardService;
import xyz.sunnytoday.service.face.AdminPostService;
import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;
import xyz.sunnytoday.service.impl.AdminPostServiceImpl;
	
@WebServlet("/admin/post/list")
public class AdminPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPostService postService = new AdminPostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Paging paging = postService.getPaging(req);		
		List<Map<String, Object>> list = postService.searchPost(req, paging);
		
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
		
		System.out.println("list:" + list);

		req.getRequestDispatcher("/WEB-INF/views/admin/post/list.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/post/list [POST]");
		
	}	
}

