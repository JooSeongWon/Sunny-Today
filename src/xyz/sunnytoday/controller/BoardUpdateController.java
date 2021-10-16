package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Post post_no = boardService.getPostno(req);
		
		Post updateBoard = boardService.detail(post_no);
		
		req.setAttribute("updateBoard", updateBoard );
		req.setAttribute( "nick", boardService.getNick(updateBoard) );
		
		System.out.println("updateBoard : " + updateBoard);
		
		File detailFile = boardService.detailFile(updateBoard);
		req.setAttribute("detailFile", detailFile);
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boardService.update(req);
		
		resp.sendRedirect("/board/main");
		
		
	}
	
	
}
