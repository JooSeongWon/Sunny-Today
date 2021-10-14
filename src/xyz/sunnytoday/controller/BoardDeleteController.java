package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 되어있지 않으면 경고
//		if( req.getSession().getAttribute("login") == null
//				|| !(boolean)req.getSession().getAttribute("login") ) {
//			
//			
//			return;
//		}
		
		
		
		Post post_no = boardService.getPostno(req);
		
		boardService.delete(post_no);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/main");
		
	}
	
	
}
