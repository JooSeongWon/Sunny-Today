package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/comments/delete")
public class BoardCommentsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int commentNo = Integer.parseInt(req.getParameter("comments_no"));
		int postno = boardService.selectPostnoByCommentsNO(commentNo);
		
		HttpSession session = req.getSession();
		int userno = (int)session.getAttribute("userno");
		
		int res = boardService.deleteComment(commentNo, userno);
		
		
		if(res>0) {
			resp.sendRedirect("/board/detail?postno="+postno);
		} else {
			req.setAttribute("postno", postno);
			req.getRequestDispatcher("/WEB-INF/views/user/board/boardCommentsDelete.jsp").forward(req, resp);
		}
		
	}

}
