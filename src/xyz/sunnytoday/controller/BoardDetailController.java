package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;
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
		
		req.setAttribute("detailBoard", detailBoard );
		req.setAttribute( "nick", boardService.getNick(detailBoard) );
		
		File detailFile = boardService.detailFile(detailBoard);
		req.setAttribute("detailFile", detailFile);
		
		req.getRequestDispatcher("/WEB-INF/views/user/board/boardDetail.jsp").forward(req, resp);
		
	}
	
}
