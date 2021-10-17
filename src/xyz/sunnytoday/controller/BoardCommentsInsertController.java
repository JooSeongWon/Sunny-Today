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
//		Post post_no = boardService.getPostno(req);
//		System.out.println("postno : " + post_no.getPost_no());
//		String content = boardService.getComments(req);
//
//		HttpSession session = req.getSession();
//		int userno = (int) session.getAttribute("userno");
//
//		
//		boardService.insertComment(post_no, content, userno);
//		
//		//상태 업데이트
//		List<Map<String, Object>> comments = boardService.selectCommentPost(post_no);
//		
//		Gson gson = new Gson();
//		String rs = gson.toJson(comments);
//			
//		resp.getWriter().write(rs);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Post post_no = boardService.getPostno(req);
		System.out.println("postno : " + post_no.getPost_no());
		String content = boardService.getComments(req);

		HttpSession session = req.getSession();
		int userno = (int) session.getAttribute("userno");

		
		boardService.insertComment(post_no, content, userno);
		
		//상태 업데이트
		List<Map<String, Object>> comments = boardService.selectCommentPost(post_no);
		
		Gson gson = new Gson();
		String rs = gson.toJson(comments);
			
		resp.getWriter().write(rs);
	}

	
}
