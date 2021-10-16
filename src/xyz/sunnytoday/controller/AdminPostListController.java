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
//		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = postService.getPaging(req);		
		List<Map<String, Object>> allList = postService.getList(req, paging);
		
		System.out.println(allList);
		
		
//		allList.forEach(System.out::println);

		req.setAttribute("paging", paging);
		req.setAttribute("allList", allList);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/post/list.jsp").forward(req, resp);
	}
	
}

