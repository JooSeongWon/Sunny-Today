package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Post post_no = boardService.getPostno(req);		
		Post detailBoard = boardService.detail(post_no);		
		File detailFile = boardService.detailFile(detailBoard);
		
		List<Map<String, Object>> comments = boardService.selectCommentPost(post_no);
		
		
		req.setAttribute("detailBoard", detailBoard );
		req.setAttribute( "nick", boardService.getNick(detailBoard) );
		req.setAttribute( "loginMember", boardService.loginMember(req));
		req.setAttribute("detailFile", detailFile);
		req.setAttribute( "comments", comments);
				
//		System.out.println("detailBoard : " + detailBoard);
//		System.out.println("detailFile : " + detailFile );
		
		
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardDetail.jsp").forward(req, resp);
		
	}
	
}
