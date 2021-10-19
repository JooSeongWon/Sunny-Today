package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/comments/insert")
public class BoardCommentsInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Post post_no = boardService.getPostno(req);
		int postno = post_no.getPost_no();
		String content = boardService.getComments(req);
		String onlyWriter = req.getParameter("onlyWriter");
		
		HttpSession session = req.getSession();
		int userno = (int) session.getAttribute("userno");
		
		
		int res = boardService.insertComment(post_no, content, userno, onlyWriter);
		
		
		if(res>0) {
			req.setAttribute("postno", postno);
			req.setAttribute("res", true);
		} else {
			req.setAttribute("postno", postno);
			req.setAttribute("res", false);	
		}
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardCommentsInsert.jsp").forward(req, resp);
		
	}
	


	
}
