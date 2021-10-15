package xyz.sunnytoday.controller;

import java.io.IOException;

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
		Post postno = postService.getPostno(req);

		//상세보기 결과 조회
		Post viewPost = postService.view(postno);
		
//		닉네임 전달
		req.setAttribute("nick", postService.getNick(viewPost));

//		조회결과 MODEL값 전달
		req.setAttribute("viewPost", viewPost);
	
//		//첨부파일 정보 조회
//		PostFile PostFile = postService.viewFile(viewPost);
//		
//		//첨부파일 정보 MODEL값 전달
//		req.setAttribute("postFile", postFile);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/post/view.jsp").forward(req, resp);		
	}
	
}
