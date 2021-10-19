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

@WebServlet("/admin/post/update")
public class AdminPostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminPostService postService = new AdminPostServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	Post postno = postService.getPostno(req);
	Post updatePost = postService.view(postno);
	
	req.setAttribute("updatePost", updatePost);
	req.setAttribute("nick", postService.getNick(updatePost));

	req.getRequestDispatcher("/WEB-INF/views/admin/post/update.jsp").forward(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
//	postService.update(req);
	
		resp.sendRedirect("/admin/post/list");
	}
}
