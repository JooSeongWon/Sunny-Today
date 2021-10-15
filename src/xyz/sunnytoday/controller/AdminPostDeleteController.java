package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.AdminPostService;
import xyz.sunnytoday.service.impl.AdminPostServiceImpl;

@WebServlet("/admin/post/delete")
public class AdminPostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminPostService postService = new AdminPostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Post postno = postService.getPostno(req);

		postService.deletePost(postno);
		
		resp.sendRedirect("/SunnyToday/admin/post/list");	


	}

}
