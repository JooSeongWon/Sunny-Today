	package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.AdminPostService;
import xyz.sunnytoday.service.impl.AdminPostServiceImpl;

@WebServlet("/admin/post/write")
public class AdminPostWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPostService postService = new AdminPostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/admin/post/write.jsp")
			.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		postService.write(req);
		
		resp.sendRedirect("/admin/post/list");
		
	}
	
}
