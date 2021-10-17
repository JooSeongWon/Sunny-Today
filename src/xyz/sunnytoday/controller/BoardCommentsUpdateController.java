package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/comments/update")
public class BoardCommentsUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int commentNo = Integer.parseInt(req.getParameter("comments_no"));
		String content = req.getParameter("newComments");
		
//		System.out.println("commentNo : " + commentNo);
//		System.out.println("content : " + content);
		
		HttpSession session = req.getSession();
		int userno = (int)session.getAttribute("userno");
		
		int res = boardService.updateComments(commentNo, content, userno);
		
		req.setAttribute("postno", boardService.selectPostnoByCommentsNO(commentNo));
		
		if(res>0) {
			req.setAttribute("res", true );
		} else {
			req.setAttribute("res", false );			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardCommentsUpdate.jsp").forward(req, resp);
		
	}
}
