package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.AdminPostService;
import xyz.sunnytoday.service.impl.AdminPostServiceImpl;

@WebServlet("/admin/post/view")
public class AdminPostViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminPostService postService = new AdminPostServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전달파라미터 얻기 - post_no
		Post post_no = postService.getPostno(req);
		System.out.println("getPostno : " + post_no);
		
		//상세보기 결과 조회
		Post viewPost = postService.view(post_no);
		System.out.println("viewPost : " + viewPost);

		req.setAttribute("viewPost", viewPost);
	
		req.getRequestDispatcher("/WEB-INF/views/admin/post/view.jsp").forward(req, resp);		
	}
}

