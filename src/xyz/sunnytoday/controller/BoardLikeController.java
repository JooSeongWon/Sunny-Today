package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import xyz.sunnytoday.service.face.BoardService;
import xyz.sunnytoday.service.impl.BoardServiceImpl;


@WebServlet("/board/like")
public class BoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boardService.makeDefaultLike(req);
		
		
		int score = boardService.likeSum(req);
		
		
//		//상태 업데이트
//		mypageService.birthOpen(birth, member);
//		
//		String birthOpen = member.getBirth_open();
//		//json 형식으로 변환
//		Gson gson = new Gson();
//		String rs = gson.toJson(birthOpen);
//	
//		// 전송이 되는 부분
//		resp.getWriter().write(rs);
		
		
	}

}
